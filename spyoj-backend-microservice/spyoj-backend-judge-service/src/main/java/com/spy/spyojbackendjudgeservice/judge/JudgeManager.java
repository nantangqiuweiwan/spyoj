package com.spy.spyojbackendjudgeservice.judge;

import com.spy.spyojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.spy.spyojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.spy.spyojbackendjudgeservice.judge.strategy.JudgeContext;
import com.spy.spyojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.spy.spyojbackendmodel.codesandbox.JudgeInfo;
import com.spy.spyojbackendmodel.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
              judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
