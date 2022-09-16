package com.beneway.basic.common.system.entity.sys_user.fo;

import com.beneway.basic.common.system.entity.sys_user.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserFo extends SysUser {

    /**
     * 角色
     */
    private List<Integer> roleList;

    /**
     * 标签id列表
     */
    private List<Integer> tagList;

}
