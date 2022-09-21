package com.beneway.web.controller;

import com.beneway.web.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/11 14:47
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 获取登录验证图片
     *
     * @return
     */
    @GetMapping("/getLoginVerifyImg")
    public void getLoginVerifyImg(HttpServletResponse response) {
        loginService.getLoginVerifyImg(response);
    }

}
