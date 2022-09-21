package com.beneway.web.service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.date.DateUnit;
import com.beneway.basic.utils.IPUtil;
import com.beneway.basic.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/11 14:40
 *
 * 登录
 */
@Service
public class LoginService {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 获取登录验证图片
     *
     * @return
     */
    public void getLoginVerifyImg(HttpServletResponse response) {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(180, 60, 4, 100);
        //图形验证码写出，可以写出到文件，也可以写出到流
        try {
            lineCaptcha.write(response.getOutputStream());
            // 添加到redis缓存中
            String code = lineCaptcha.getCode();
            String key = getLoginVerifyRedisCodeKey();
            redisUtils.set(key, code, DateUnit.MINUTE.getMillis() * 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取验证码
     * @return
     */
    public String getLoginVerifyCode() {
        String key = getLoginVerifyRedisCodeKey();
        String code = redisUtils.get(key, 0);
        return code;
    }

    private String getLoginVerifyRedisCodeKey() {
        String ip = IPUtil.getIpAddr();
        String key = "loginVerifyCode" + ip;
        return key;
    }

}
