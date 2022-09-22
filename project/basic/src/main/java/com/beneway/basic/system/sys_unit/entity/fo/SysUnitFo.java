package com.beneway.basic.system.sys_unit.entity.fo;

import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import lombok.Data;

import java.util.List;

@Data
public class SysUnitFo extends SysUnit {

  /**
   * 标签id列表
   */
  private List<Integer> tagList;

}
