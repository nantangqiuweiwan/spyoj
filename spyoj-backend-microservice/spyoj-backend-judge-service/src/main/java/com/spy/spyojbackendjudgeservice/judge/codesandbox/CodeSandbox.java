package com.spy.spyojbackendjudgeservice.judge.codesandbox;

import com.spy.spyojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.spy.spyojbackendmodel.codesandbox.ExecuteCodeResponse;

public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
