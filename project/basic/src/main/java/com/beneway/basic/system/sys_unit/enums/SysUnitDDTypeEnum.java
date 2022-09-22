/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

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
