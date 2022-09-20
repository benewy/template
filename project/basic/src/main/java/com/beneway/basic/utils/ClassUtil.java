package com.beneway.basic.utils;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ClassUtil {
  // jackson转换工具
  private static final ObjectMapper objectMapper = getObjMapper();

  private static ObjectMapper getObjMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  }

  public static <T> T toClass(Object object, Class<T> tClass){
    try {
      String s = objectMapper.writeValueAsString(object);
      T t = objectMapper.readValue(s, tClass);
      return t;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <T> List<T> toClassList(List<? super T> objects, Class<T> tClass){
    try {
      List<T> list = new LinkedList<>();
      for (Object object : objects) {
        String s = objectMapper.writeValueAsString(object);
        T t = objectMapper.readValue(s, tClass);
        list.add(t);
      }
      return list;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String toJson(Object obj) {
    try {
      String s = objectMapper.writeValueAsString(obj);
      return s;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String toStr(Object param){
    System.out.println("param.getClass() = " + param.getClass());
    if (param == null) {
      return "null";
    }
    if (param instanceof Number) {
      return param.toString();
    }
    String value = null;
    if (param instanceof String) {
      value = param.toString();
    } else if (param instanceof Timestamp) {
      Timestamp timestamp = (Timestamp) param;
      LocalDateTime localDateTime = timestamp.toLocalDateTime();
      value = DateUtil.format(localDateTime, "yyyy-MM-dd HH:mm:ss");
    } else if (param instanceof Date) {
      value = DateUtil.format((Date) param, "yyyy-MM-dd HH:mm:ss");
    } else if (param instanceof IEnum) {
      value = String.valueOf(((IEnum) param).getValue());
    } else {
      value = param.toString();
    }
    return value;
  }
}
