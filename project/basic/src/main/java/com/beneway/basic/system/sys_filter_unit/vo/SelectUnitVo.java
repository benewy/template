package com.beneway.basic.system.sys_filter_unit.vo;

import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SelectUnitVo implements Serializable {

  /**
   * 区域选择树
   */
  private List<SysUnitVo> sysUnitTree;

  /**
   * 单位列表
   */
  private List<SysUnitVo> sysUnitList;

}
