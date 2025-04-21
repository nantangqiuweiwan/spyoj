package com.spy.spyoj.judge;

import com.spy.spyoj.judge.strategy.DefaultJudgeStrategy;
import com.spy.spyoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.spy.spyoj.judge.strategy.JudgeContext;
import com.spy.spyoj.judge.strategy.JudgeStrategy;
import com.spy.spyoj.model.dto.questionsubmit.JudgeInfo;
import com.spy.spyoj.model.entity.Question;
import com.spy.spyoj.model.entity.QuestionSubmit;
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
