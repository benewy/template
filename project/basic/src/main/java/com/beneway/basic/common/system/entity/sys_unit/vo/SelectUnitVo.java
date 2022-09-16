package com.beneway.basic.common.system.entity.sys_unit.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Create by zhy on 2022/3/8 11:54
 */
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
