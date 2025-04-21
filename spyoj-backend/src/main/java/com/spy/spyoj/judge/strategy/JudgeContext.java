package com.spy.spyoj.judge.strategy;

import com.spy.spyoj.model.dto.question.JudgeCase;
import com.spy.spyoj.judge.codesandbox.model.JudgeInfo;
import com.spy.spyoj.model.entity.Question;
import com.spy.spyoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 用于定义在策略中传递的参数
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
