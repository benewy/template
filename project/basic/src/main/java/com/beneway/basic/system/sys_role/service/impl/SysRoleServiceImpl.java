package com.beneway.basic.system.sys_role.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_role.dao.SysRoleDao;
import com.beneway.basic.system.sys_role.enums.SysRoleTypeEnum;
import com.beneway.basic.system.sys_role.fo.SysRoleFo;
import com.beneway.basic.system.sys_role.fo.SysRolePageQueryFo;
import com.beneway.basic.system.sys_role.po.SysRole;
import com.beneway.basic.system.sys_role.po.SysRoleMenu;
import com.beneway.basic.system.sys_role.service.SysRoleMenuService;
import com.beneway.basic.system.sys_role.service.SysRoleService;
import com.beneway.basic.system.sys_role.vo.SysRoleVo;
import com.beneway.basic.system.sys_tag.enums.SysTagTypeEnum;
import com.beneway.basic.system.sys_tag.service.SysTagMapService;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_user.entity.po.SysUserRole;
import com.beneway.basic.system.sys_user.entity.vo.LoginUserInfo;
import com.beneway.basic.system.sys_user.service.SysUserRoleService;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.utils.login_user.LoginUserUtils;
import com.beneway.basic.utils.page.PageUtils;
import com.restful.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-01 14:59:40
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysTagMapService sysTagMapService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(SysRoleFo sysRoleFo) {
        this.save(sysRoleFo);
        List<Integer> sysMenuIdList = sysRoleFo.getSysMenuIdList();
        sysRoleMenuService.addRoleMenu(sysRoleFo.getId(), sysMenuIdList);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result edit(SysRoleFo sysRoleFo) {
        this.updateById(sysRoleFo);
        List<Integer> sysMenuIdList = sysRoleFo.getSysMenuIdList();
        sysRoleMenuService.updateRoleMenu(sysRoleFo.getId(), sysMenuIdList);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result del(Integer sysRoleId) {
        this.removeById(sysRoleId);
        sysRoleMenuService.removeRoleMenu(sysRoleId);
        return Result.success();
    }

    @Override
    public Page<SysRoleVo> queryPage(SysRolePageQueryFo sysRolePageQueryFo) {
        Page page = PageUtils.getPage(sysRolePageQueryFo);
        Page<SysRoleVo> voPage = sysRoleDao.queryPage(page, sysRolePageQueryFo);
        List<SysRoleVo> records = voPage.getRecords();
        for (SysRoleVo record : records) {
            List<Integer> menuIdList = sysRoleMenuService.getMenuIdList(record.getId());
            record.setSysMenuIdList(menuIdList);
        }
        return voPage;
    }

    @Override
    public List<SysRole> getList(SysRoleTypeEnum type) {
        List<SysRole> list = this.list(new LambdaQueryWrapper<SysRole>().eq(type != null, SysRole::getType, type));
        return list;
    }

    @Override
    public List<SysRole> getListByUserId(String userId) {
        List<Integer> roleIdList = getIdListByUserId(userId);
        if (CollUtil.isNotEmpty(roleIdList)){
            List<SysRole> roleList = this.listByIds(roleIdList);
            return roleList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<Integer> getIdListByUserId(String userId) {
        List<SysUserRole> list = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .select(SysUserRole::getSysRoleId)
                .eq(SysUserRole::getSysUserId, userId));
        List<Integer> roleIdList = list.stream().map(SysUserRole::getSysRoleId).collect(Collectors.toList());
        return roleIdList;
    }

    @Override
    public List<Integer> getMenuIdListByUserId(String userId) {
        List<Integer> roleIdList = getIdListByUserId(userId);
        List<Integer> menuIdList = new LinkedList<>();
        for (Integer roleId : roleIdList) {
            List<Integer> list = sysRoleMenuService.getMenuIdList(roleId);
            menuIdList.addAll(list);
        }
        menuIdList = menuIdList.stream().distinct().collect(Collectors.toList());
        return menuIdList;
    }


    @Override
    public List<Integer> getLoginUserOfMenuIdList() {
        LoginUserInfo loginUserInfo = LoginUserUtils.getLoginUserInfo();
        List<SysUnit> currentUnitInList = loginUserInfo.getCurrentUnitInList();
        List<String> unitIdList = currentUnitInList.stream()
                .map(sysUnit -> String.valueOf(sysUnit.getId()))
                .collect(Collectors.toList());
        List<Integer> unitTagIdList = sysTagMapService.getIdListByAssIdList(unitIdList, SysTagTypeEnum.AGENCY);
        List<Integer> userTagIdList = loginUserInfo.getTagIdList();

        BiFunction<List<Integer>, String, Boolean> matchFun = (tagIdList, tags) -> {
            if (StrUtil.isNotEmpty(tags)) {
                return Arrays.stream(tags.split(",")).map(Integer::parseInt).anyMatch(tagId -> tagIdList.contains(tagId));
            } else {
                return true;
            }
        };

        Set<Integer> sysRoleIdSet = new HashSet<>();
        List<SysRole> sysRoleList = this.list(new LambdaQueryWrapper<SysRole>().eq(SysRole::getType, SysRoleTypeEnum.SCOPE));
        for (SysRole sysRole : sysRoleList) {
            boolean userB = matchFun.apply(userTagIdList, sysRole.getUserTags());
            boolean unitB = matchFun.apply(unitTagIdList, sysRole.getUnitTags());
            if (userB && unitB) {
                sysRoleIdSet.add(sysRole.getId());
            }
        }

        sysRoleIdSet.addAll(this.getIdListByUserId(loginUserInfo.getId()));

        if (CollUtil.isNotEmpty(sysRoleIdSet)) {
            // 获取菜单id列表
            List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list(new LambdaQueryWrapper<SysRoleMenu>()
                    .select(SysRoleMenu::getSysMenuId)
                    .in(SysRoleMenu::getSysRoleId, sysRoleIdSet));

            return sysRoleMenuList.stream().map(SysRoleMenu::getSysMenuId).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

}
