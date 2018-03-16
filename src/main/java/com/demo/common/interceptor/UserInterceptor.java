package com.demo.common.interceptor;

import com.demo.common.model.Device;
import com.demo.login.LoginService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Redis;

/**
 * 全局请求拦截，根据token判断是否登录
 */
public class UserInterceptor implements Interceptor {
    @Override
    /**
     * 判断页面获取的token是否存在，若不存在则跳转
     */
    public void intercept(Invocation inv) {
        Controller c = inv.getController();
        String token = c.getCookie(LoginService.SESSION_ID_NAME);
        Device device = token == null ? null : (Device) Redis.use().get(token);
        if (device == null) {
            c.redirect("/login");
            return;
        }
        c.setAttr("user", device);
        inv.invoke();
    }
}
