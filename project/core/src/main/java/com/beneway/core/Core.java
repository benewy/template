package com.beneway.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true) // aop切面
@MapperScan(value = {"com.beneway.**.dao"})
@SpringBootApplication(scanBasePackages = {"com.beneway.*"})
public class Core {

    public static void main(String[] args) {
        SpringApplication.run(Core.class, args);
    }

}
