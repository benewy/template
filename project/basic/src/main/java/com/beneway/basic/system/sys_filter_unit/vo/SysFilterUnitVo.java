package com.beneway.basic.system.sys_filter_unit.vo;

import com.beneway.basic.system.sys_filter_unit.po.SysFilterUnit;
import lombok.Data;

import java.util.List;

/**
 * Create by zhy on 2022/3/11 16:16
 */
@Data
public class SysFilterUnitVo extends SysFilterUnit {

    private String unitModeDesc;

    private List<Integer> unitIdList;

    private List<Integer> tagList;

}
