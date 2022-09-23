/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

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
