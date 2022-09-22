package com.beneway.basic.system.sys_menu.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_menu.dao.SysMenuPermissionDao;
import com.beneway.basic.system.sys_menu.po.SysMenuPermission;
import com.beneway.basic.system.sys_menu.service.SysMenuPermissionService;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-02-23 11:44:57
 */
@EnableAspectJAutoProxy( proxyTargetClass = true , exposeProxy = true )
@CacheConfig(cacheNames = "sysMenuPermission")
@Service("sysMenuPermissionService")
public class SysMenuPermissionServiceImpl extends ServiceImpl<SysMenuPermissionDao, SysMenuPermission> implements SysMenuPermissionService {

    private SysMenuPermissionService getCurrThis(){
        SysMenuPermissionService currentProxy = (SysMenuPermissionService) AopContext.currentProxy();
        return currentProxy;
    }

    @Cacheable(key = "'list'")
    @Override
    public List<SysMenuPermission> list() {
        return super.list();
    }

    @CacheEvict(key = "'list'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addPermission(Integer menuId, List<String> permissionList) {
        if (CollUtil.isNotEmpty(permissionList)){
            List<SysMenuPermission> sysMenuPermissionList = new ArrayList<>(permissionList.size());
            for (String p : permissionList) {
                SysMenuPermission sysMenuPermission = new SysMenuPermission();
                sysMenuPermission.setSysMenuId(menuId);
                sysMenuPermission.setPermission(p);
                sysMenuPermissionList.add(sysMenuPermission);
            }
            this.saveBatch(sysMenuPermissionList);
        }
    }

    @CacheEvict(key = "'list'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removePermission(Integer menuId) {
        this.remove(new LambdaQueryWrapper<SysMenuPermission>().eq(SysMenuPermission::getSysMenuId, menuId));
    }

    @Override
    public List<String> getPermissionByMenuIdList(List<Integer> menuIdList) {
        List<SysMenuPermission> list = getCurrThis().list();
        List<String> permissionList = list.stream()
                .filter(sysMenuPermission -> menuIdList.contains(sysMenuPermission.getSysMenuId()))
                .map(SysMenuPermission::getPermission)
                .collect(Collectors.toList());
        return permissionList;
    }

}
