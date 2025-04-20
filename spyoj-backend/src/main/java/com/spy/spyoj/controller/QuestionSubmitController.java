package com.spy.spyoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spy.spyoj.annotation.AuthCheck;
import com.spy.spyoj.common.BaseResponse;
import com.spy.spyoj.common.ErrorCode;
import com.spy.spyoj.common.ResultUtils;
import com.spy.spyoj.constant.UserConstant;
import com.spy.spyoj.exception.BusinessException;
import com.spy.spyoj.model.dto.question.QuestionQueryRequest;
import com.spy.spyoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.spy.spyoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.spy.spyoj.model.entity.Question;
import com.spy.spyoj.model.entity.QuestionSubmit;
import com.spy.spyoj.model.entity.User;
import com.spy.spyoj.model.vo.QuestionSubmitVO;
import com.spy.spyoj.service.QuestionService;
import com.spy.spyoj.service.QuestionSubmitService;
import com.spy.spyoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author
 * @from
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录的 id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }

}
