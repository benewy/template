package com.beneway.basic.system.sys_menu.fo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.beneway.basic.system.sys_menu.po.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class SysMenuFo extends SysMenu {

    @TableField(exist = false)
    private List<String> permissionList;

}
