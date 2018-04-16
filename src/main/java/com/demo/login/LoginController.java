package com.demo.login;

import com.demo.common.interceptor.UserInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.kit.Ret;

/**
 * 登录页面controller
 */
@Before({NoUrlPara.class})
public class LoginController extends Controller {
    private static final LoginService srv = LoginService.me;

    /**
     * 渲染登录页面主页
     */
    @Clear({UserInterceptor.class})
    @Before({LoginInterceptor.class,GET.class})
    public void index() {
        render("index.html");
    }

    /**
     * 登录页面的登录服务
     */
    @Clear({UserInterceptor.class})
    public void doLogin() {
        String username = getPara("username");
        String password = getPara("password");
        Ret ret = srv.login(username, password);
        if (ret.getBoolean("status")) {
            setCookie(LoginService.SESSION_ID_NAME, ret.getStr("token"), 60 * 60);
        }
        renderJson(ret);
    }

    /**
     * 登录页面的验证码获取服务
     */
    @Clear({UserInterceptor.class})
    @Before({GET.class})
    public void captcha() {
        renderCaptcha();
    }

    /**
     * 登出服务
     */
    @ActionKey("/logout")
    public void logout() {
        removeCookie(LoginService.SESSION_ID_NAME);
        redirect("/login");
    }
}
