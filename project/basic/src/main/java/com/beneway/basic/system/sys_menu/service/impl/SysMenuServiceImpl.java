package com.beneway.basic.system.sys_menu.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_menu.dao.SysMenuDao;
import com.beneway.basic.system.sys_menu.fo.SysMenuFo;
import com.beneway.basic.system.sys_menu.po.SysMenu;
import com.beneway.basic.system.sys_menu.po.SysMenuPermission;
import com.beneway.basic.system.sys_menu.service.SysMenuPermissionService;
import com.beneway.basic.system.sys_menu.service.SysMenuService;
import com.beneway.basic.system.sys_menu.vo.SysMenuVo;
import com.beneway.basic.system.sys_role.service.SysRoleService;
import com.beneway.basic.utils.ClassUtil;
import com.beneway.basic.utils.login_user.LoginUserUtils;
import com.restful.Result;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-02-23 11:44:57
 */
@EnableAspectJAutoProxy( proxyTargetClass = true , exposeProxy = true )
@CacheConfig(cacheNames = "sysMenu")
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Resource
    private SysMenuPermissionService sysMenuPermissionService;

    @Resource
    private SysRoleService sysRoleService;

    private SysMenuService getCurrThis(){
        SysMenuService currentProxy = (SysMenuService) AopContext.currentProxy();
        return currentProxy;
    }

    @Cacheable(key = "'list'")
    @Override
    public List<SysMenu> list() {
        List<SysMenu> list = this.list(new LambdaQueryWrapper<SysMenu>().orderByAsc(SysMenu::getSortNum));
        return list;
    }

    @Override
    public List<SysMenu> listByIds(Collection<? extends Serializable> idList) {
        List<SysMenu> list = getCurrThis().list();
        List<SysMenu> menuList = list.stream()
                .filter(sysMenu -> idList.contains(sysMenu.getId()))
                .collect(Collectors.toList());
        return menuList;
    }

    @CacheEvict(key = "'list'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(SysMenuFo sysMenuFo) {
        this.save(sysMenuFo);
        Integer id = sysMenuFo.getId();
        List<String> permissionList = sysMenuFo.getPermissionList();
        sysMenuPermissionService.addPermission(id, permissionList);
        return Result.success();
    }

    @CacheEvict(key = "'list'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result edit(SysMenuFo sysMenuFo) {
        Integer sysMenuId = sysMenuFo.getId();
        this.updateById(sysMenuFo);

        sysMenuPermissionService.removePermission(sysMenuId);

        List<String> permissionList = sysMenuFo.getPermissionList();
        sysMenuPermissionService.addPermission(sysMenuId, permissionList);

        return Result.success();
    }

    @CacheEvict(key = "'list'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result del(Integer id) {
        this.removeById(id);
        sysMenuPermissionService.removePermission(id);
        return Result.success();
    }

    @Override
    public List<SysMenuVo> getTreeList() {
        List<SysMenu> sysMenuList = getCurrThis().list();
        List<SysMenuVo> sysMenuVoList = new ArrayList<>(sysMenuList.size());
        for (SysMenu sysMenu : sysMenuList) {
            SysMenuVo sysMenuVo = ClassUtil.toClass(sysMenu, SysMenuVo.class);
            sysMenuVoList.add(sysMenuVo);
        }
        sysMenuList.clear();

        List<SysMenuPermission> sysMenuPermissionList = sysMenuPermissionService.list();

        List<SysMenuVo> fSysMenuList = sysMenuVoList.stream().filter(sysMenuVo -> sysMenuVo.getPid().equals(0)).collect(Collectors.toList());
        for (SysMenuVo sysMenuVo : fSysMenuList) {
            children(sysMenuVo, sysMenuVoList, sysMenuPermissionList);
        }

        return fSysMenuList;
    }

    @Override
    public List<SysMenuVo> getUserMenuList() {
        // 获取用户对应菜单
        List<SysMenu> sysMenuList;
        if (LoginUserUtils.isAdmin()) {
            sysMenuList = getCurrThis().list();
        }else{
            List<Integer> menuIdList = sysRoleService.getLoginUserOfMenuIdList();
            sysMenuList = getCurrThis().listByIds(menuIdList);
        }

        List<SysMenuVo> sysMenuVoList = new ArrayList<>(sysMenuList.size());
        for (SysMenu sysMenu : sysMenuList) {
            SysMenuVo sysMenuVo = ClassUtil.toClass(sysMenu, SysMenuVo.class);
            sysMenuVoList.add(sysMenuVo);
        }
        sysMenuList.clear();

        List<SysMenuVo> fSysMenuList = sysMenuVoList.stream().filter(sysMenuVo -> sysMenuVo.getPid().equals(0)).collect(Collectors.toList());
        for (SysMenuVo sysMenuVo : fSysMenuList) {
            children(sysMenuVo, sysMenuVoList, null);
        }

        return fSysMenuList;
    }

    private void children(SysMenuVo sysMenuVo, List<SysMenuVo> sysMenuVoList, List<SysMenuPermission> sysMenuPermissionList){
        Function<Integer, List<String>> getPermissionListFun = sysMenuId -> {
            List<String> list = sysMenuPermissionList
                    .stream()
                    .filter(sysMenuPermission -> sysMenuId.equals(sysMenuPermission.getSysMenuId()))
                    .map(SysMenuPermission::getPermission)
                    .collect(Collectors.toList());
            return list;
        };

        Function<Integer, List<SysMenuVo>> getSysMenuListByPidFun = sysMenuPid -> {
            List<SysMenuVo> list = sysMenuVoList.stream().filter(sysMenu -> sysMenuPid.equals(sysMenu.getPid())).collect(Collectors.toList());
            return list;
        };

        Integer id = sysMenuVo.getId();
        Integer type = sysMenuVo.getType();
        if (type == 0){
            List<SysMenuVo> menuList = getSysMenuListByPidFun.apply(id);
            if (CollUtil.isNotEmpty(menuList)){
                sysMenuVo.setChildren(menuList);
            }
            for (SysMenuVo vo : menuList) {
                children(vo, sysMenuVoList, sysMenuPermissionList);
            }
        }else{
            if (sysMenuPermissionList != null){
                List<String> permissionList = getPermissionListFun.apply(id);
                sysMenuVo.setPermissionList(permissionList);
            }
        }
    }

}
