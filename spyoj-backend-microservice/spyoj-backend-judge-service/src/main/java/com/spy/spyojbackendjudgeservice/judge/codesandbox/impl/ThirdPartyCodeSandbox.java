package com.spy.spyojbackendjudgeservice.judge.codesandbox.impl;

import com.spy.spyojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.spy.spyojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.spy.spyojbackendmodel.codesandbox.ExecuteCodeResponse;

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
