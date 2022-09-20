package com.beneway.core.config;

import com.beneway.web.interceptor.service.LoginUserService;
import com.beneway.web.interceptor.service.implement.LoginUserServiceImplements;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/11 18:10
 */
@Configuration
public class AppWebConfig {

    @Bean
    public LoginUserService getLoginUserService() {
        return new LoginUserServiceImplements();
    }

}
