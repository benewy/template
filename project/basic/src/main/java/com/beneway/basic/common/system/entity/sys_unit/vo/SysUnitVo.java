package com.beneway.basic.common.system.entity.sys_unit.vo;

import com.beneway.basic.common.system.entity.sys_unit.SysUnit;
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
