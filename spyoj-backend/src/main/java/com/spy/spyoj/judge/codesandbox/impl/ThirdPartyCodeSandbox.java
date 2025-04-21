package com.spy.spyoj.judge.codesandbox.impl;

import com.spy.spyoj.judge.codesandbox.CodeSandbox;
import com.spy.spyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.spy.spyoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现成的沙箱）
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");

        return null;
    }
}
