package com.beneway.basic.utils.dd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 服务启动时执行的方法
 */
@Slf4j
@Component
public class StartService implements ApplicationRunner {

    @Resource
    private ZWDDQrcodeUtils zwddQrcodeUtils;

    @Resource
    private ZWDDPhoneUtils zwddPhoneUtils;

    /**
     * 进行小程序token的初次获取
     */
    @Override
    public void run(ApplicationArguments args) {
//        zwddQrcodeUtils.flushToken();
//        zwddPhoneUtils.flushToken();
    }
}
