package com.beneway.basic.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口说明
 * @author 24340
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqApi {

    /**
     * 接口说明
     * @return
     */
    String value();

    /**
     * 权限key
     */
    String permission() default "";

}
