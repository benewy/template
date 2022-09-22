package com.beneway.basic.system.sys_unit.entity.vo;

import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import lombok.Data;

import java.util.List;

@Data
public class SysUnitVo extends SysUnit {

  /**
   * 子集
   */
  private List<SysUnitVo> children;
  /**
   * 父级列表
   */
  private List<SysUnit> parentList;
  /**
   * 标签
   */
  private List<Integer> tagList;

}
