package com.beneway.basic.system.sys_user_unit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_user_unit.dao.SysUserUnitDao;
import com.beneway.basic.system.sys_user_unit.entity.po.SysUserUnit;
import com.beneway.basic.system.sys_user_unit.service.SysUserUnitService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户单位关联表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-28 11:14:35
 */
@Service("sysUserUnitService")
public class SysUserUnitServiceImpl extends ServiceImpl<SysUserUnitDao, SysUserUnit> implements SysUserUnitService {

    /**
     * 获取用户单位id列表
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getUserUnitIdList(String userId){
        List<SysUserUnit> userUnitList = this.list(new LambdaQueryWrapper<SysUserUnit>()
                .select(SysUserUnit::getSysUnitId)
                .eq(SysUserUnit::getSysUserId, userId)
                .orderByAsc(SysUserUnit::getSysUnitId));

        List<Integer> unitIdList = userUnitList.stream()
                .map(SysUserUnit::getSysUnitId)
                .collect(Collectors.toList());

        return unitIdList;
    }

    /**
     * 获取用户第一个单位id
     * @param userId
     * @return
     */
    @Override
    public Integer getUserUnitId(String userId) {
        SysUserUnit one = this.getOne(new LambdaQueryWrapper<SysUserUnit>()
                .select(SysUserUnit::getSysUnitId)
                .eq(SysUserUnit::getSysUserId, userId)
                .orderByAsc(SysUserUnit::getSysUnitId)
                .last("limit 1"));

        if (one != null) {
            return one.getSysUnitId();
        } else {
            return null;
        }
    }

    @Override
    public List<SysUserUnit> getUsersByUnitId(Integer unitId) {
        List<SysUserUnit> data;
        LambdaQueryWrapper<SysUserUnit> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserUnit::getSysUnitId, unitId);
        data = this.list(lambdaQueryWrapper) == null ? new ArrayList<>() : this.list(lambdaQueryWrapper);
        return data;
    }

}
