package com.spy.spyojcodesandbox;


import com.spy.spyojcodesandbox.model.ExecuteCodeRequest;
import com.spy.spyojcodesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
