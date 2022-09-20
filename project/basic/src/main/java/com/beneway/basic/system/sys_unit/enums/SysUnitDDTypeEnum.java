package com.beneway.basic.system.sys_unit.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SysUnitDDTypeEnum {

  GOV_HOLLOW_DIVISION_NODE("D", "行政区划虚节点", "GOV_HOLLOW_DIVISION_NODE"),

  GOV_HOLLOW_STRIP_NODE("S", "条线虚节点", "GOV_HOLLOW_STRIP_NODE"),

  GOV_UNIT("U", "单位", "GOV_UNIT"),

  GOV_VIRTUAL("V", "虚拟组织", "GOV_VIRTUAL"),

  GOV_INTERNAL_INSTITUTION("I", "内设机构", "GOV_INTERNAL_INSTITUTION"),

  GOV_TEMPORARY("T", "临时组织", "GOV_TEMPORARY");

  @EnumValue
  @JsonValue
  private final String type;

  private final String typeName;

  private final String typeCode;

  SysUnitDDTypeEnum(String type, String typeName, String typeCode) {
    this.type = type;
    this.typeName = typeName;
    this.typeCode = typeCode;
  }

  public String getType() {
    return type;
  }

  public String getTypeName() {
    return typeName;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public static SysUnitDDTypeEnum getByType(String type) {
    SysUnitDDTypeEnum[] values = values();
    for (SysUnitDDTypeEnum value : values) {
      if (value.getType().equals(type)) {
        return value;
      }
    }
    return null;
  }

  public static SysUnitDDTypeEnum getByTypeCode(String typeCode) {
    SysUnitDDTypeEnum[] values = values();
    for (SysUnitDDTypeEnum value : values) {
      if (value.getTypeCode().equals(typeCode)) {
        return value;
      }
    }
    return null;
  }

}
