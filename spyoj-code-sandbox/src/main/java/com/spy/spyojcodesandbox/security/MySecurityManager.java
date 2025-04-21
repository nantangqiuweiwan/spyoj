package com.spy.spyojcodesandbox.security;

import java.security.Permission;

/**
 * 默认安全管理器
 */
public class MySecurityManager extends SecurityManager {

    //检查所有权限
    @Override
    public void checkPermission(Permission perm) {
//        super.checkPermission(perm);
    }

    @Override
    public void checkConnect(String host, int port) {
        throw new SecurityException("checkConnect 权限异常" + host + port);
    }

    @Override
    public void checkDelete(String file) {
        throw new SecurityException("checkDelete 权限异常" + file);
    }

    @Override
    public void checkWrite(String file) {
        throw new SecurityException("checkWrite 权限异常" + file);
    }

    @Override
    public void checkRead(String file) {
        if (file.contains("hutool")) {
            return;
        }
        throw new SecurityException("checkRead 权限异常" + file);
    }

    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("checkExec 权限异常" + cmd);
    }


}
