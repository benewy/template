/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

package com.beneway.basic.system.sys_user.service.implement;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.exception.RRException;
import com.beneway.basic.exception.ResultException;
import com.beneway.basic.system.sys_filter_unit.service.SysFilterUnitService;
import com.beneway.basic.system.sys_menu.service.SysMenuPermissionService;
import com.beneway.basic.system.sys_role.service.SysRoleService;
import com.beneway.basic.system.sys_tag.enums.SysTagTypeEnum;
import com.beneway.basic.system.sys_tag.service.SysTagMapService;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_unit.service.SysUnitService;
import com.beneway.basic.system.sys_user.dao.SysUserDao;
import com.beneway.basic.system.sys_user.entity.fo.SysUserFo;
import com.beneway.basic.system.sys_user.entity.fo.SysUserQueryFo;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.entity.vo.LoginUserInfo;
import com.beneway.basic.system.sys_user.entity.vo.SysUserComVo;
import com.beneway.basic.system.sys_user.entity.vo.SysUserVo;
import com.beneway.basic.system.sys_user.service.SysUserRoleService;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.system.sys_user_unit.service.SysUserUnitService;
import com.beneway.basic.utils.ClassUtil;
import com.beneway.basic.utils.login_user.LoginUserUtils;
import com.beneway.basic.utils.page.PageUtils;
import com.restful.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@EnableAspectJAutoProxy( proxyTargetClass = true , exposeProxy = true )
@CacheConfig(cacheNames = "sysUser")
@Service("sysUserService")
public class SysUserServiceImplement extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUnitService sysUnitService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysTagMapService sysTagMapService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysMenuPermissionService sysMenuPermissionService;

    @Resource
    private SysUserUnitService sysUserUnitService;

    @Resource
    private SysFilterUnitService sysFilterUnitService;

    private SysUserService getCurrThis(){
        SysUserService currentProxy = (SysUserService) AopContext.currentProxy();
        return currentProxy;
    }

    /**
     * 密码加密
     * @param password
     * @return
     */
    @Override
    public String passEncr(String password) {
        if (StrUtil.isNotEmpty(password)){
            return DigestUtil.md5Hex(password);
        }else{
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(SysUserFo sysUserFo) {
        sysUserFo.setPassword(passEncr(sysUserFo.getPassword()));
        sysUserFo.setCreateTime(new Date());
        this.save(sysUserFo);

        List<Integer> roleList = sysUserFo.getRoleList();
        sysUserRoleService.addUserRole(sysUserFo.getId(), roleList);

        List<Integer> tagList = sysUserFo.getTagList();
        sysTagMapService.addTagMap(sysUserFo.getId(), tagList, SysTagTypeEnum.USER);

        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result edit(SysUserFo sysUserFo) {
        getCurrThis().clearLoginUserInfoCacheable(sysUserFo.getId());

        sysUserFo.setPassword(passEncr(sysUserFo.getPassword()));
        this.updateById(sysUserFo);

        List<Integer> roleList = sysUserFo.getRoleList();
        sysUserRoleService.updateUserRole(sysUserFo.getId(), roleList);

        List<Integer> tagList = sysUserFo.getTagList();
        sysTagMapService.updateTagMap(sysUserFo.getId(), tagList, SysTagTypeEnum.USER);


        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result del(String userId) {
        getCurrThis().clearLoginUserInfoCacheable(userId);

        this.removeById(userId);
        sysUserRoleService.removeUserRole(userId);
        sysTagMapService.removeTagMap(userId, SysTagTypeEnum.USER);
        return Result.success();
    }

    /**
     * 获取登录用户信息
     * @param userId
     * @return
     */
    @Cacheable(key = "'getLoginUserInfo:' + #userId")
    @Override
    public LoginUserInfo getLoginUserInfo(String userId) {
        SysUser sysUser = this.getById(userId);
        // 获取用户单位列表
        List<Integer> userUnitIdList = sysUserUnitService.getUserUnitIdList(userId);

        LoginUserInfo loginUserInfo = ClassUtil.toClass(sysUser, LoginUserInfo.class);

        loginUserInfo.setUnitIdList(userUnitIdList);

        Integer currentUnitIndex = sysUser.getCurrentUnitIndex();
        Integer userUnitId = userUnitIdList.get(currentUnitIndex);
        loginUserInfo.setCurrentUnitId(userUnitId);

        List<SysUnit> unitList = sysUnitService.getInList(userUnitId);
        loginUserInfo.setCurrentUnitInList(unitList);

        List<Integer> tagIdList = sysTagMapService.getIdListByAssId(userId, SysTagTypeEnum.USER);
        loginUserInfo.setTagIdList(tagIdList);

        return loginUserInfo;
    }

    /**
     * 切换用户当前单位
     * @param userId
     * @param unitId
     */
    @Override
    public void switchCurrentUnit(String userId, Integer unitId) {
        getCurrThis().clearLoginUserInfoCacheable(userId);

        List<Integer> unitIdList = sysUserUnitService.getUserUnitIdList(userId);
        int unitIndex = unitIdList.indexOf(unitId);

        if (unitIndex < 0) {
          throw new ResultException(HttpStatus.NOT_FOUND,"用户不存在该单位");
        } else {
            this.update(new LambdaUpdateWrapper<SysUser>()
                    .set(SysUser::getCurrentUnitIndex, unitIndex)
                    .eq(SysUser::getId, userId));
        }
    }

    /**
     * 清空获取登录用户信息缓存
     * @param userId
     */
    @CacheEvict(key = "'getLoginUserInfo:' + #userId")
    @Override
    public void clearLoginUserInfoCacheable(String userId) {

    }

    @Override
    public boolean isLoginUserPermission(String permission) {
        List<Integer> menuIdList = sysRoleService.getLoginUserOfMenuIdList();
        List<String> permissionList = sysMenuPermissionService.getPermissionByMenuIdList(menuIdList);
        return permissionList.contains(permission);
    }

    /**
     * 获取用户管理分页查询 单位列表
     * @return
     */
    @Override
    public List<SysUnit> getQueryPageUnitList() {
        Integer sysUnitPid = 1;
        if (!LoginUserUtils.isAdmin()) {
            // 不是超级管理员
            sysUnitPid = LoginUserUtils.getCurrentAgencyId();
        }
        return sysFilterUnitService.getChildren(sysUnitPid, true);
    }

    /**
     * 用户管理分页查询
     * @param sysUserPageQueryFo
     * @return
     */
    @Override
    public Page<SysUserVo> queryPage(SysUserQueryFo sysUserPageQueryFo) {
        Page page = PageUtils.getPage(sysUserPageQueryFo);

        Integer unitId = sysUserPageQueryFo.getUnitId();
        Set<Integer> unitIdList = sysUnitService.getIdListByPid(unitId);
        sysUserPageQueryFo.setUnitIdList(unitIdList);

        Page<SysUserVo> voPage = sysUserDao.queryPage(page, sysUserPageQueryFo);
        List<SysUserVo> records = voPage.getRecords();
        for (SysUserVo record : records) {
            packVo(record);
        }

        return voPage;
    }

    @Override
    public SysUserVo getUserInfo(String userId) {
        SysUser sysUser = this.getById(userId);
        return packVo(sysUser);
    }

    private SysUserVo packVo(SysUser sysUser){
        SysUserVo sysUserVo = ClassUtil.toClass(sysUser, SysUserVo.class);
        return packVo(sysUserVo);
    }

    private SysUserVo packVo(SysUserVo sysUserVo) {
        String userId = sysUserVo.getId();

        List<Integer> unitIdList = sysUserUnitService.getUserUnitIdList(userId);
        sysUserVo.setUnitList(unitIdList);

        List<Integer> roleList = sysRoleService.getIdListByUserId(userId);
        sysUserVo.setRoleList(roleList);

        List<Integer> tagIdList = sysTagMapService.getIdListByAssId(userId, SysTagTypeEnum.USER);
        sysUserVo.setTagList(tagIdList);

        return sysUserVo;
    }

    /**
     * 获取前端统一组件的用户信息
     * @return
     */
    @Override
    public SysUserComVo getComUserInfo(String userId) {
        SysUser sysUser = this.getOne(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getUsername)
                .eq(SysUser::getId, userId));
        SysUserComVo sysUserComVo = ClassUtil.toClass(sysUser, SysUserComVo.class);
        return sysUserComVo;
    }

    /**
     * 获取前端统一组件的用户信息
     * @return
     */
    @Override
    public List<SysUser> getComUserInfoList(String userIds) {
        if (StringUtils.isNoneBlank(userIds)){
            List<String> ids = Arrays.asList(userIds.split(","));
            List<SysUser> list = this.list(new LambdaQueryWrapper<SysUser>()
                    .select(SysUser::getUsername)
                    .in(SysUser::getId, ids));
            return list;
        }
        return null;
    }

    /**
     * 查询用户列表
     * @param unitIdList
     * @param tagIdList
     * @param userIdList
     * @return
     */
    @Override
    public List<SysUser> queryList(Collection<Integer> unitIdList, Collection<Integer> tagIdList, Collection<String> userIdList) {
        List<SysUser> sysUserList = sysUserDao.queryList(unitIdList, tagIdList, userIdList);
        return sysUserList;
    }
}
