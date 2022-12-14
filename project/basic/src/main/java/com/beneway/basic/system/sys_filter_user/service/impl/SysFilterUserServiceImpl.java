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

package com.beneway.basic.system.sys_filter_user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.beneway.basic.exception.RRException;
import com.beneway.basic.exception.ResultException;
import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitModeEnum;
import com.beneway.basic.system.sys_filter_unit.service.SysFilterUnitService;
import com.beneway.basic.system.sys_filter_user.dao.SysFilterUserDao;
import com.beneway.basic.system.sys_filter_user.enums.SysFilterUserKeyEnum;
import com.beneway.basic.system.sys_filter_user.enums.SysFilterUserModeEnum;
import com.beneway.basic.system.sys_filter_user.enums.SysFilterUserTypeEnum;
import com.beneway.basic.system.sys_filter_user.fo.SysFilterUserFo;
import com.beneway.basic.system.sys_filter_user.po.SysFilterUser;
import com.beneway.basic.system.sys_filter_user.service.SysFilterUserService;
import com.beneway.basic.system.sys_filter_user.vo.SysFilterUserVo;
import com.beneway.basic.system.sys_tag.po.SysTag;
import com.beneway.basic.system.sys_tag.service.SysTagMapService;
import com.beneway.basic.system.sys_tag.service.SysTagService;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_unit.service.SysUnitService;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.utils.ClassUtil;
import com.restful.Result;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 用户筛选配置
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-10 17:54:45
 */
@Service("sysFilterUserService")
public class SysFilterUserServiceImpl implements SysFilterUserService {

    @Resource
    private SysFilterUserDao sysFilterUserDao;

    @Resource
    private SysFilterUnitService sysFilterUnitService;

    @Resource
    private SysTagService sysTagService;

    @Resource
    private SysTagMapService sysTagMapService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUnitService sysUnitService;

    /**
     * 获取列表
     * @return
     */
    @Override
    public List<SysFilterUserVo> getList(){
        SysFilterUserKeyEnum[] keyEnums = SysFilterUserKeyEnum.values();
        List<SysFilterUser> sysFilterUserList = sysFilterUserDao.selectList(new LambdaQueryWrapper<SysFilterUser>()
                .in(SysFilterUser::getKey, keyEnums));
        List<SysFilterUserVo> voList = ClassUtil.toClassList(sysFilterUserList, SysFilterUserVo.class);

        Consumer<SysFilterUserVo> getUnitData = sysFilterUserVo -> {
            SysFilterUnitModeEnum unitMode = sysFilterUserVo.getUnitMode();
            String unitData = sysFilterUserVo.getUnitData();

            List<Integer> collect = Arrays.stream((String[])StrUtil.split(unitData, ",").toArray())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            if (SysFilterUnitModeEnum.UNIT_TAG.equals(unitMode)) {
                if (CollUtil.isNotEmpty(collect)) {
                    List<SysTag> sysTagList = sysTagService.listByIds(collect);
                    sysFilterUserVo.setUnitDataList(sysTagList);
                }
            } else if (SysFilterUnitModeEnum.UNIT_LIST.equals(unitMode)) {
                sysFilterUserVo.setUnitDataList(collect);
            } else if (SysFilterUnitModeEnum.UNIT_AREA.equals(unitMode)) {
                sysFilterUserVo.setUnitDataList(collect);
            }

            if (unitMode != null) {
                sysFilterUserVo.setUnitModeDesc(unitMode.getDesc());
            }
        };

        Consumer<SysFilterUserVo> getUserData = sysFilterUserVo -> {
            SysFilterUserModeEnum userMode = sysFilterUserVo.getUserMode();
            String userData = sysFilterUserVo.getUserData();

            List<Integer> collect = Arrays.stream((String[])StrUtil.split(userData, ",").toArray())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            if (SysFilterUserModeEnum.USER_TAG.equals(userMode)) {
                if (CollUtil.isNotEmpty(collect)) {
                    List<SysTag> sysTagList = sysTagService.listByIds(collect);
                    sysFilterUserVo.setUserDataList(sysTagList);
                }
            } else if (SysFilterUserModeEnum.USER_LIST.equals(userMode)) {
                sysFilterUserVo.setUserDataList(collect);
            }

            if (userMode != null) {
                sysFilterUserVo.setUserModeDesc(userMode.getDesc());
            }
        };

        for (SysFilterUserVo sysFilterUserVo : voList) {
            SysFilterUserTypeEnum type = sysFilterUserVo.getType();
            if (SysFilterUserTypeEnum.ALL.equals(type)) {
                getUnitData.accept(sysFilterUserVo);
                getUserData.accept(sysFilterUserVo);
            } else if (SysFilterUserTypeEnum.UNIT.equals(type)) {
                getUnitData.accept(sysFilterUserVo);
            } else if (SysFilterUserTypeEnum.USER.equals(type)) {
                getUserData.accept(sysFilterUserVo);
            }
        }

        return voList;
    }

    /**
     * 单位过滤配置修改
     * @param sysFilterUserFo
     * @return
     */
    @Override
    public Result edit(SysFilterUserFo sysFilterUserFo){
        SysFilterUserKeyEnum key = sysFilterUserFo.getKey();
        String userData = sysFilterUserFo.getUserData();
        String unitData = sysFilterUserFo.getUnitData();

        sysFilterUserDao.update(null, new LambdaUpdateWrapper<SysFilterUser>()
                .set(SysFilterUser::getUserData, userData)
                .set(SysFilterUser::getUnitData, unitData)
                .eq(SysFilterUser::getKey, key));

        return Result.success();
    }

    @Override
    public Set<String> getUserIdSet(SysFilterUserKeyEnum key) {
        SysFilterUser sysFilterUser = sysFilterUserDao.selectOne(new LambdaQueryWrapper<SysFilterUser>().eq(SysFilterUser::getKey, key));

        SysFilterUnitModeEnum unitMode = sysFilterUser.getUnitMode();
        if (SysFilterUnitModeEnum.UNIT_ID_IN.equals(unitMode)) {
            throw new RuntimeException("unit_id_in 必须传入unitId");
        }

        SysFilterUserTypeEnum type = sysFilterUser.getType();
        SysFilterUserModeEnum userMode = sysFilterUser.getUserMode();

        if (SysFilterUserTypeEnum.USER.equals(type)) {
            String userData = sysFilterUser.getUserData();
            if (SysFilterUserModeEnum.USER_TAG.equals(userMode)) {
                List<Integer> tagIdList = Arrays.stream((String[])StrUtil.split(userData, ",").toArray())
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
                Set<String> userIdList = sysTagMapService.getAssIdSetByTagIdList(tagIdList);
                return userIdList;
            } else {
                Set<String> userIdList = Arrays.stream((String[])StrUtil.split(userData, ",").toArray())
                        .collect(Collectors.toSet());
                return userIdList;
            }
        } else {
            String unitData = sysFilterUser.getUnitData();
            Set<Integer> unitIdList = sysFilterUnitService.getUnitIdSet(unitMode, unitData);
            if (SysFilterUserTypeEnum.UNIT.equals(type)) {
                List<SysUser> sysUserList = sysUserService.queryList(unitIdList, null, null);
                Set<String> collect = sysUserList.stream().map(SysUser::getId).collect(Collectors.toSet());
                return collect;
            } else {
                String userData = sysFilterUser.getUserData();
                List<Integer> tagIdList = Arrays.stream((String[])StrUtil.split(userData, ",").toArray())
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
                Set<String> userIdList = sysTagMapService.getAssIdSetByTagIdList(tagIdList);

                List<SysUser> sysUserList = sysUserService.queryList(unitIdList, null, userIdList);
                Set<String> collect = sysUserList.stream().map(SysUser::getId).collect(Collectors.toSet());

                return collect;
            }
        }
    }

    @Override
    public Set<String> getUserIdSet(SysFilterUserKeyEnum key, Integer sysUnitId) {
        List<Integer> sysUnitIdList = new ArrayList<>(1);
        sysUnitIdList.add(sysUnitId);
        return getUserIdSet(key, sysUnitIdList);
    }

    @Override
    public Set<String> getUserIdSet(SysFilterUserKeyEnum key, List<Integer> sysUnitIdList) {
        SysFilterUser sysFilterUser = sysFilterUserDao.selectOne(new LambdaQueryWrapper<SysFilterUser>().eq(SysFilterUser::getKey, key));

        SysFilterUnitModeEnum unitMode = sysFilterUser.getUnitMode();
        SysFilterUserModeEnum userMode = sysFilterUser.getUserMode();
        if (!(SysFilterUnitModeEnum.UNIT_ID_IN.equals(unitMode)
                && SysFilterUserModeEnum.USER_TAG.equals(userMode))) {
            throw new RuntimeException("unitMode必须为UNIT_ID_IN, userMode必须为USER_TAG");
        }

        String userData = sysFilterUser.getUserData();
        List<Integer> tagIdList = Arrays.stream((String[])StrUtil.split(userData, ",").toArray())
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        Set<String> userIdList = sysTagMapService.getAssIdSetByTagIdList(tagIdList);

        sysUnitService.setChildrenId(sysUnitIdList);

        List<SysUser> sysUserList = sysUserService.queryList(sysUnitIdList, null, userIdList);
        Set<String> collect = sysUserList.stream().map(SysUser::getId).collect(Collectors.toSet());

        return collect;
    }

    /**
     * 判断用户是否存在于该模式匹配下
     * @param key
     * @param sysUnitId
     * @param sysUserId
     * @return
     */
    @Override
    public boolean isExistUserId(SysFilterUserKeyEnum key, Integer sysUnitId, String sysUserId) {
        Set<String> userIdSet = this.getUserIdSet(key, sysUnitId);
        return userIdSet.contains(sysUserId);
    }

    /**
     * 根据key获取配置信息
     * @param key
     * @return
     */
    @Override
    public SysFilterUser getByKey(SysFilterUserKeyEnum key) {
        SysFilterUser sysFilterUser = sysFilterUserDao.selectOne(new LambdaQueryWrapper<SysFilterUser>().eq(SysFilterUser::getKey, key));
        return sysFilterUser;
    }

    /**
     * 获取前端选择用户的单位信息数据
     * @param key
     * @return
     */
    @Override
    public List<SysUnitVo> getUnitData(SysFilterUserKeyEnum key) {
        SysFilterUser sysFilterUser = sysFilterUserDao.selectOne(new LambdaQueryWrapper<SysFilterUser>()
                .eq(SysFilterUser::getKey, key));

        SysFilterUnitModeEnum unitMode = sysFilterUser.getUnitMode();
        if (SysFilterUnitModeEnum.UNIT_ID_IN.equals(unitMode)) {
            throw new RuntimeException("unit_id_in 必须传入unitId");
        }

        String unitData = sysFilterUser.getUnitData();
        Set<Integer> unitIdSet = sysFilterUnitService.getUnitIdSet(unitMode, unitData);

        List<SysUnit> sysUnitList = sysUnitService.listByIds(unitIdSet);
        List<SysUnitVo> sysUnitVoList = sysUnitService.toTree(sysUnitList);

        return sysUnitVoList;
    }

    /**
     * 获取前端选择用户的用户信息数据
     * @param key
     * @param unitId
     * @return
     */
    @Override
    public List<SysUser> getUserData(SysFilterUserKeyEnum key, Integer unitId) {
        SysFilterUser sysFilterUser = sysFilterUserDao.selectOne(new LambdaQueryWrapper<SysFilterUser>()
                .eq(SysFilterUser::getKey, key));

        // 获取用户信息
        List<Integer> userTagIdList = null;
        List<String> userIdList = null;
        SysFilterUserModeEnum userMode = sysFilterUser.getUserMode();
        String userData = sysFilterUser.getUserData();
        if (StrUtil.isNotEmpty(userData)) {
            if (SysFilterUserModeEnum.USER_TAG.equals(userMode)) {
                userTagIdList = Arrays.stream((String[])StrUtil.split(userData, ",").toArray())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } else if (SysFilterUserModeEnum.USER_LIST.equals(userMode)) {
                userIdList = Arrays.stream((String[])StrUtil.split(userData, ",").toArray())
                        .collect(Collectors.toList());
            }
        }

        // 获取单位id列表
        Set<Integer> unitIdList = null;
        SysFilterUserTypeEnum type = sysFilterUser.getType();
        if (SysFilterUserTypeEnum.ALL.equals(type)
           || SysFilterUserTypeEnum.UNIT.equals(type)) {
            if (unitId == null) {
              throw new ResultException(HttpStatus.BAD_REQUEST,"单位id不能为空!");
            } else {
                unitIdList = sysUnitService.getIdListByPid(unitId);
            }
        }

        List<SysUser> sysUserList = sysUserService.queryList(unitIdList, userTagIdList, userIdList);

        return sysUserList;
    }

    /**
     * 根据用户id串获取用户信息
     * @param userIds
     * @return
     */
    @Override
    public List<SysUser> getUserList(String userIds) {
        if (StrUtil.isNotEmpty(userIds)) {
            List<String> userIdList = StrUtil.split(userIds, ",");
            List<SysUser> userList = sysUserService.queryList(null, null, userIdList);
            return userList;
        } else {
            return new ArrayList<>();
        }
    }

}
