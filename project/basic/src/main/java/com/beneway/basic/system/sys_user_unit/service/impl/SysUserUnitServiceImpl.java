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
