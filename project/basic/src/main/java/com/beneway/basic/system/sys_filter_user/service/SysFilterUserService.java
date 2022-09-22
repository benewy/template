package com.beneway.basic.system.sys_filter_user.service;

import com.beneway.basic.system.sys_filter_user.enums.SysFilterUserKeyEnum;
import com.beneway.basic.system.sys_filter_user.fo.SysFilterUserFo;
import com.beneway.basic.system.sys_filter_user.po.SysFilterUser;
import com.beneway.basic.system.sys_filter_user.vo.SysFilterUserVo;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.restful.Result;

import java.util.List;
import java.util.Set;

/**
 * 用户筛选配置
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-10 17:54:45
 */
public interface SysFilterUserService {

    /**
     * 获取列表
     * @return
     */
    List<SysFilterUserVo> getList();

    /**
     * 单位过滤配置修改
     * @param sysFilterUserFo
     * @return
     */
    Result edit(SysFilterUserFo sysFilterUserFo);

    /**
     * 获取用户id列表
     * @param key
     * @return
     */
    Set<String> getUserIdSet(SysFilterUserKeyEnum key);

    Set<String> getUserIdSet(SysFilterUserKeyEnum key, Integer sysUnitId);

    Set<String> getUserIdSet(SysFilterUserKeyEnum key, List<Integer> sysUnitIdList);

    /**
     * 判断用户是否存在于该模式匹配下
     * @param key
     * @param sysUnitId
     * @param sysUserId
     * @return
     */
    boolean isExistUserId(SysFilterUserKeyEnum key, Integer sysUnitId, String sysUserId);

    /**
     * 根据key获取配置信息
     * @param key
     * @return
     */
     SysFilterUser getByKey(SysFilterUserKeyEnum key);

    /**
     * 获取前端选择用户的单位信息数据
     * @param key
     * @return
     */
    List<SysUnitVo> getUnitData(SysFilterUserKeyEnum key);

    /**
     * 获取前端选择用户的用户信息数据
     * @param key
     * @param unitId
     * @return
     */
    List<SysUser> getUserData(SysFilterUserKeyEnum key, Integer unitId);

    /**
     * 根据用户id串获取用户信息
     * @param userIds
     * @return
     */
    List<SysUser> getUserList(String userIds);

}

