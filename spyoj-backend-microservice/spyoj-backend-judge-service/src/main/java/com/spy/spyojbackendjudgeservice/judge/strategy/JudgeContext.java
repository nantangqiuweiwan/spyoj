package com.spy.spyojbackendjudgeservice.judge.strategy;

import com.spy.spyojbackendmodel.codesandbox.JudgeInfo;
import com.spy.spyojbackendmodel.dto.question.JudgeCase;
import com.spy.spyojbackendmodel.entity.Question;
import com.spy.spyojbackendmodel.entity.QuestionSubmit;
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
