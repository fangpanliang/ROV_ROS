package com.demo.login;

import com.demo.common.model.Device;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.redis.Redis;

/**
 * 登录页面的相关操作
 */
public class LoginService {

    public static final LoginService me = new LoginService();
    public static final String SESSION_ID_NAME = "loginToken";
    private static final Device deviceDao = new Device().dao();

    /**
     *验证用户名密码是否匹配并设置token
     * @return 包含登录状态及token的json
     */
    public Ret login(String rov_ip, String cam_ip) {
        Device device = deviceDao.findFirst("select * from device where rov_ip=?", rov_ip);
        if (null == device) {
            return Ret.by("status", false).set("message", "登录错误!");
        }

        String token = StrKit.getRandomUUID();
        //device.setPwd(null).setSalt(null);
        Redis.use().set(token, device);
        return Ret.by("status", true).set("token", token);
    }
}
