package com.beneway.basic.common.system.entity.sys_user.vo;

import com.beneway.basic.common.system.entity.sys_unit.vo.SysUnitVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Create by zhy on 2022/3/5 13:22
 */
@Data
public class SelectUserVo implements Serializable {

    /**
     * 单位列表
     */
    private List<SysUnitVo> sysUnitList;

    /**
     * 用户列表
     */
    private List<SysUserVo> sysUserList;

}
