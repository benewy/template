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
