package com.example.shiro;

import com.example.shiro.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ：zhanglei
 * @date ：Created in 2020/10/20 10:19
 * @description：  shiro测试类
 * @modified By：
 * @version: 1.0
 */
public class AuthenticationTest {

    MyRealm myRealm = new MyRealm();

//    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    // 在方法开始之前添加一个用户
//    @Before
//    public void addUser() {
//        simpleAccountRealm.addAccount("test", "123", "admin", "user");
//    }

    @Test
    public void testAuthentication() {

        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(myRealm);

        // 2.主体提交认证请求

        // 设置SecurityManager
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 获取当前主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("test", "123456");

        // 登录
        subject.login(usernamePasswordToken);

        boolean loginFlag = subject.isAuthenticated();
        System.out.println("登录后---用户是否认证成功 : " + loginFlag);

        System.out.println("校验权限...");
        subject.checkRoles("admin", "user");

        subject.logout();

        System.out.println("登出后---用户是否认证成功 : " + loginFlag);



    }

}
