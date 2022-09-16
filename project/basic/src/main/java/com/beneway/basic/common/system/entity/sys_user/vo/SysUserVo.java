package com.beneway.basic.common.system.entity.sys_user.vo;

import com.beneway.basic.common.system.entity.sys_user.SysUser;
import lombok.Data;

import java.util.List;

/**
 * Create by zhy on 2022/2/28 17:41
 */
@Data
public class SysUserVo extends SysUser {

    private List<Integer> unitList;

    private List<Integer> roleList;

    private List<Integer> tagList;

}
