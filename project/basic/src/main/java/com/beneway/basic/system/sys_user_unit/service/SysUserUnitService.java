package com.beneway.basic.system.sys_user_unit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.basic.system.sys_user_unit.entity.po.SysUserUnit;

import java.util.List;

/**
 * 用户单位关联表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-28 11:14:35
 */
public interface SysUserUnitService extends IService<SysUserUnit> {

    /**
     * 获取用户单位id列表
     * @param userId
     * @return
     */
    List<Integer> getUserUnitIdList(String userId);

    /**
     * 获取用户第一个单位id
     * @param userId
     * @return
     */
    Integer getUserUnitId(String userId);

    /**
     * 通过单位ID获取在该单位工作的用户集合
     * @param unitId 单位ID
     * @return 用户集合
     */
    List<SysUserUnit> getUsersByUnitId(Integer unitId);

}

