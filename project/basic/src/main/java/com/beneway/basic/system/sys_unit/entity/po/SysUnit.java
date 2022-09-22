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

package com.beneway.basic.system.sys_unit.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.system.sys_unit.enums.SysUnitDDTypeEnum;
import com.beneway.basic.system.sys_unit.enums.SysUnitTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_unit")
public class SysUnit implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   *
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   *
   */
  private Integer pid;
  /**
   * 单位名称
   */
  private String unitName;
  /**
   * 序号
   */
  private Float sortNum;
  /**
   * 类型
   */
  private SysUnitTypeEnum type;
  /**
   * 钉钉类型
   */
  private SysUnitDDTypeEnum ddType;
  /**
   * 组织code
   */
  private String organizationCode;
  /**
   * 统一社会信用代码
   */
  private String unifiedSocialCreditCode;
  /**
   * 是否最后
   */
  private Boolean isLast;
  /**
   * 单位编码
   */
  private String unitCode;
  /**
   * 逻辑删除
   */
  @TableLogic
  private Integer deleted;

}
