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
