package com.beneway.basic.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 是与否枚举
 */
public enum LogicalEnum {

  /**
   * 是
   */
  YES("Y"),

  /**
   * 否
   */
  NO("N");

  /**
   * 逻辑值
   */
  @EnumValue
  @JsonValue
  private final String code;

  LogicalEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public static LogicalEnum getByCode(String code) {
    for (LogicalEnum value : values()) {
      if (value.getCode().equals(code)) {
        return value;
      }
    }
    return null;
  }
}
