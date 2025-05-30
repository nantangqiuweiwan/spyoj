package com.spy.spyoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spy.spyoj.model.dto.question.QuestionQueryRequest;
import com.spy.spyoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.spy.spyoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.spy.spyoj.model.entity.Question;
import com.spy.spyoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spy.spyoj.model.entity.User;
import com.spy.spyoj.model.vo.QuestionSubmitVO;
import com.spy.spyoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 20699
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2025-04-20 09:42:55
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
