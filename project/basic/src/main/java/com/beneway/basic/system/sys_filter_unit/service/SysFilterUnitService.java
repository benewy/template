package com.beneway.basic.system.sys_filter_unit.service;

import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitKeyEnum;
import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitModeEnum;
import com.beneway.basic.system.sys_filter_unit.fo.SysFilterUnitFo;
import com.beneway.basic.system.sys_filter_unit.vo.SelectUnitVo;
import com.beneway.basic.system.sys_filter_unit.vo.SysFilterUnitVo;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.restful.Result;

import java.util.List;
import java.util.Set;

/**
 * 用户筛选配置
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-10 20:14:11
 */
public interface SysFilterUnitService {

    /**
     * 获取列表
     * @return
     */
    List<SysFilterUnitVo> getList();

    /**
     * 单位过滤配置修改
     * @param sysFilterUnitFo
     * @return
     */
    Result edit(SysFilterUnitFo sysFilterUnitFo);

    /**
     * 获取单位id列表
     * @param keyEnum
     * @return
     */
    Set<Integer> getUnitIdSet(SysFilterUnitKeyEnum keyEnum);

    Set<Integer> getUnitIdSet(SysFilterUnitModeEnum unitModeEnum, String unitData);

    /**
     * 获取前端选择单位公共组件数据
     * @return
     */
    SelectUnitVo getSelectUnitData(SysFilterUnitKeyEnum keyEnum);

    /**
     * 获取子集列表
     * @param pid 父id
     * @param include 是否包含父
     * @return
     */
    List<SysUnit> getChildren(Integer pid, boolean include);

    /**
     * 根据id获取一直到第一级的单位列表
     * @param id
     * @return
     */
    List<SysUnit> getTopLineList(Integer id);

}

