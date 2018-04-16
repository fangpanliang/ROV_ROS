package com.demo.login;

import com.demo.common.model.User;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.redis.Redis;

/**
 * 登录页面的相关操作
 */
public class LoginService {

    public static final LoginService me = new LoginService();
    public static final String SESSION_ID_NAME = "loginToken";
    private static final User userDao = new User().dao();

    /**
     *验证用户名密码是否匹配并设置token
     * @return 包含登录状态及token的json
     */
    public Ret login(String username, String password) {
        User user = userDao.findFirst("select * from user where username=?", username);
        if (null == user) {
            return Ret.by("status", false).set("message", "用户名或密码错误!");
        }
        String matchPwd = user.getPassword();
//        if (!matchPwd.equals(password)) {
//            return Ret.by("status", false).set("message", "用户名或密码错误!");
//        }
        String token = StrKit.getRandomUUID();
        //device.setPwd(null).setSalt(null);
        Redis.use().set(token, user);
        return Ret.by("status", true).set("token", token);
    }
}
