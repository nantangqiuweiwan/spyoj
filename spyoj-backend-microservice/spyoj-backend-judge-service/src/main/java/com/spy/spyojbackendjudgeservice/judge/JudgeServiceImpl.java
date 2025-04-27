package com.spy.spyojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.spy.spyojbackendcommon.common.ErrorCode;
import com.spy.spyojbackendcommon.exception.BusinessException;
import com.spy.spyojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.spy.spyojbackendjudgeservice.judge.codesandbox.CodeSandboxFactory;
import com.spy.spyojbackendjudgeservice.judge.codesandbox.CodeSandboxProxy;
import com.spy.spyojbackendjudgeservice.judge.strategy.JudgeContext;
import com.spy.spyojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.spy.spyojbackendmodel.codesandbox.ExecuteCodeResponse;
import com.spy.spyojbackendmodel.codesandbox.JudgeInfo;
import com.spy.spyojbackendmodel.dto.question.JudgeCase;
import com.spy.spyojbackendmodel.entity.Question;
import com.spy.spyojbackendmodel.entity.QuestionSubmit;
import com.spy.spyojbackendmodel.enums.QuestionSubmitStatusEnum;
import com.spy.spyojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {

        QuestionSubmit questionsubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionsubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionsubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        if (!QuestionSubmitStatusEnum.WAITING.getValue().equals(questionsubmit.getStatus())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }

        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        CodeSandbox codeSandbox = new CodeSandboxProxy(CodeSandboxFactory.newInstance(type));
        String code = questionsubmit.getCode();
        String language = questionsubmit.getLanguage();

        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList = judgeCaseList.stream()
                .map(JudgeCase::getInput)
                .collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();

        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionsubmit);

        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        if ("成功".equals(judgeInfo.getMessage())) {
            questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
            question.setAcceptedNum(question.getAcceptedNum() + 1);
        } else {
            questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.FAILED.getValue());
        }
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        // 更新题目提交数
        question.setSubmitNum(question.getSubmitNum() + 1);
        update = questionFeignClient.updateQuestionById(question);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionId);
        return questionSubmitResult;
    }
}
