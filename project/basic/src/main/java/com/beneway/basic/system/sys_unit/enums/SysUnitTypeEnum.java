package com.beneway.basic.system.sys_unit.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("all")
public enum SysUnitTypeEnum {

  AREA("A", "区域"),

  NODE("N", "节点"),

  UNIT("U", "单位"),

  DEPA("D", "部门"),

  OFFICE("O", "科室");

  /**
   * 类型
   */
  @EnumValue
  @JsonValue
  private final String type;

  private final String desc;

  SysUnitTypeEnum(String type, String desc){
    this.type = type;
    this.desc = desc;
  }

  public String getType() {
    return type;
  }

  public String getDesc() {
    return desc;
  }
}
