package com.beneway.basic.common.system.entity.sys_unit.vo;

import com.beneway.basic.common.system.entity.sys_unit.SysUnit;
import lombok.Data;

import java.util.List;

/**
 * Create by zhy on 2022/3/10 9:53
 * 获取前端统一组件的单位信息
 */
@Data
public class SysUnitComVo {


    private List<SysUnit> unitList;

}
