package com.beneway.basic.common.system.entity.sys_unit.fo;

import com.beneway.basic.common.system.entity.sys_unit.SysUnit;
import lombok.Data;

import java.util.List;

@Data
public class SysUnitFo extends SysUnit {

    /**
     * 标签id列表
     */
    private List<Integer> tagList;

}
