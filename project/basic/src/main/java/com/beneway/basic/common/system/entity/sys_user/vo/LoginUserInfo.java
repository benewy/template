package com.beneway.basic.common.system.entity.sys_user.vo;

import com.beneway.basic.common.system.entity.sys_unit.SysUnit;
import com.beneway.basic.common.system.entity.sys_user.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class LoginUserInfo extends SysUser {

    /**
     * 单位id列表
     */
    private List<Integer> unitIdList;

    /**
     * 当前单位线单位列表
     */
    private List<SysUnit> currentUnitInList;

    /**
     * 当前单位id
     */
    private Integer currentUnitId;

    /**
     * 用户标签id列表
     */
    private List<Integer> tagIdList;

}
