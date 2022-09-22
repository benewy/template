package com.beneway.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
