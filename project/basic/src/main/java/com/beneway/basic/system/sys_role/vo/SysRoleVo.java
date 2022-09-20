package com.beneway.basic.system.sys_role.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.beneway.basic.system.sys_role.po.SysRole;
import lombok.Data;

import java.util.List;

/**
 * Create by zhy on 2022/3/1 15:06
 */
@Data
public class SysRoleVo extends SysRole {

    @TableField(exist = false)
    private List<Integer> sysMenuIdList;

}
