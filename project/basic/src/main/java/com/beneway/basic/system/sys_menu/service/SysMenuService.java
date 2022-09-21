package com.beneway.basic.system.sys_menu.service;

import com.beneway.basic.mybatisplus.MyIService;
import com.beneway.basic.system.sys_menu.fo.SysMenuFo;
import com.beneway.basic.system.sys_menu.po.SysMenu;
import com.beneway.basic.system.sys_menu.vo.SysMenuVo;
import com.restful.Result;

import java.util.List;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-02-23 11:44:57
 */
public interface SysMenuService extends MyIService<SysMenu> {

    Result add(SysMenuFo sysMenuFo);

    Result edit(SysMenuFo sysMenuFo);

    Result del(Integer id);

    List<SysMenuVo> getTreeList();

    List<SysMenuVo> getUserMenuList();

}

