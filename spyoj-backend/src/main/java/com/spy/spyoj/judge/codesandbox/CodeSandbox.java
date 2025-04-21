package com.spy.spyoj.judge.codesandbox;

import com.spy.spyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.spy.spyoj.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
