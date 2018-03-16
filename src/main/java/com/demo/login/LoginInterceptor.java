package com.demo.login;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录页面的请求拦截器
 */
public class LoginInterceptor implements Interceptor {
    /**
     拦截所有发往login的post请求并重定向
     * @param inv 当前执行的操作
     */
    @Override
    public void intercept(Invocation inv) {
        Controller c = inv.getController();
        HttpServletRequest request = c.getRequest();
        String method = request.getMethod();
        if ("POST".equalsIgnoreCase(method)) {
            c.forwardAction("/login/doLogin");
        } else {
            inv.invoke();
        }
    }
}
