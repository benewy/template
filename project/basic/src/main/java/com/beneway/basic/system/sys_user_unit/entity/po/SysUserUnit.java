package com.beneway.basic.system.sys_user_unit.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sys_user_unit")
public class SysUserUnit implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   *
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   * 用户id
   */
  private String sysUserId;
  /**
   * 单位id
   */
  private Integer sysUnitId;


}
