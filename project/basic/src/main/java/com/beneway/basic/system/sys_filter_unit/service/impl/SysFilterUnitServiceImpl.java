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

package com.beneway.basic.system.sys_filter_unit.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.beneway.basic.system.sys_filter_unit.dao.SysFilterUnitDao;
import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitKeyEnum;
import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitModeEnum;
import com.beneway.basic.system.sys_filter_unit.fo.SysFilterUnitFo;
import com.beneway.basic.system.sys_filter_unit.po.SysFilterUnit;
import com.beneway.basic.system.sys_filter_unit.service.SysFilterUnitService;
import com.beneway.basic.system.sys_filter_unit.vo.SelectUnitVo;
import com.beneway.basic.system.sys_filter_unit.vo.SysFilterUnitVo;
import com.beneway.basic.system.sys_tag.service.SysTagMapService;
import com.beneway.basic.system.sys_tag.service.SysTagService;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_unit.enums.SysUnitTypeEnum;
import com.beneway.basic.system.sys_unit.service.SysUnitService;
import com.beneway.basic.utils.ClassUtil;
import com.beneway.basic.utils.login_user.LoginUserUtils;
import com.restful.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户筛选配置
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-10 20:14:11
 */
@Service("sysFilterUnitService")
public class SysFilterUnitServiceImpl implements SysFilterUnitService {

    @Resource
    private SysFilterUnitDao sysFilterUnitDao;

    @Resource
    private SysUnitService sysUnitService;

    @Resource
    private SysTagService sysTagService;

    @Resource
    private SysTagMapService sysTagMapService;

    @Override
    public List<SysFilterUnitVo> getList() {
        SysFilterUnitKeyEnum[] keyEnums = SysFilterUnitKeyEnum.values();
        List<SysFilterUnit> sysFilterUnitList = sysFilterUnitDao.selectList(new LambdaQueryWrapper<SysFilterUnit>()
                .in(SysFilterUnit::getKey, keyEnums));
        List<SysFilterUnitVo> voList = ClassUtil.toClassList(sysFilterUnitList, SysFilterUnitVo.class);
        for (SysFilterUnitVo sysFilterUnitVo : voList) {
            SysFilterUnitModeEnum unitMode = sysFilterUnitVo.getUnitMode();
            sysFilterUnitVo.setUnitModeDesc(unitMode.getDesc());

            // 封装数据
            String unitData = sysFilterUnitVo.getUnitData();
            List<Integer> idList = Arrays.stream((String[])StrUtil.split(unitData, ",").toArray())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
          if (SysFilterUnitModeEnum.UNIT_LIST.equals(unitMode)
                || SysFilterUnitModeEnum.UNIT_AREA.equals(unitMode)) {
                sysFilterUnitVo.setUnitIdList(idList);
            } else if (SysFilterUnitModeEnum.UNIT_TAG.equals(unitMode)) {
                if (CollUtil.isNotEmpty(idList)) {
                    sysFilterUnitVo.setTagList(idList);
                }
            }
        }
        return voList;
    }

    @Override
    public Result edit(SysFilterUnitFo sysFilterUnitFo) {
        SysFilterUnitKeyEnum key = sysFilterUnitFo.getKey();
        SysFilterUnit sysFilterUnit = sysFilterUnitDao.selectOne(new LambdaQueryWrapper<SysFilterUnit>().eq(SysFilterUnit::getKey, key));
        SysFilterUnitModeEnum unitMode = sysFilterUnit.getUnitMode();

        String remark = sysFilterUnitFo.getRemark();
        LambdaUpdateWrapper<SysFilterUnit> wrapper = new LambdaUpdateWrapper<SysFilterUnit>()
                .set(SysFilterUnit::getRemark, remark)
                .eq(SysFilterUnit::getKey, key);

        if (SysFilterUnitModeEnum.UNIT_TAG.equals(unitMode)
                || SysFilterUnitModeEnum.UNIT_LIST.equals(unitMode)) {
            List<Integer> idList = sysFilterUnitFo.getIdList();
            String unitData = idList.stream().map(String::valueOf).collect(Collectors.joining(","));
            wrapper.set(SysFilterUnit::getUnitData, unitData);
            sysFilterUnitDao.update(null, wrapper);
        } else if (SysFilterUnitModeEnum.UNIT_AREA.equals(unitMode)) {
            String unitData = sysFilterUnitFo.getUnitData();
            wrapper.set(SysFilterUnit::getUnitData, unitData);
            sysFilterUnitDao.update(null, wrapper);
        }

        return Result.success();
    }

    @Override
    public Set<Integer> getUnitIdSet(SysFilterUnitKeyEnum keyEnum) {
        SysFilterUnit sysFilterUnit = sysFilterUnitDao.selectOne(new LambdaQueryWrapper<SysFilterUnit>().eq(SysFilterUnit::getKey, keyEnum));
        return getUnitIdSet(sysFilterUnit.getUnitMode(), sysFilterUnit.getUnitData());
    }

    @Override
    public Set<Integer> getUnitIdSet(SysFilterUnitModeEnum unitModeEnum, String unitData) {
        Set<Integer> parentUnitIdList = new HashSet<>();
        if (SysFilterUnitModeEnum.UNIT_TAG.equals(unitModeEnum)) {
            List<Integer> tagIdList = Arrays.stream((String[])StrUtil.split(unitData, ",").toArray())
                   .map(Integer::valueOf)
                   .collect(Collectors.toList());
            Set<String> idList = sysTagMapService.getAssIdSetByTagIdList(tagIdList);
            Set<Integer> collect = idList.stream().map(Integer::valueOf).collect(Collectors.toSet());
            return collect;
        } else if (SysFilterUnitModeEnum.UNIT_LIST.equals(unitModeEnum)) {
            Set<Integer> unitIdList = Arrays.stream((String[])StrUtil.split(unitData, ",").toArray())
                   .map(Integer::valueOf)
                   .collect(Collectors.toSet());
            parentUnitIdList.addAll(unitIdList);
        } else if (SysFilterUnitModeEnum.UNIT_LOGIN.equals(unitModeEnum)) {
            Integer unitId = LoginUserUtils.getCurrentAgencyId();
            parentUnitIdList.add(unitId);
        } else if (SysFilterUnitModeEnum.UNIT_AREA.equals(unitModeEnum)) {
            Integer areaUnitId = Integer.parseInt(unitData);
//            parentUnitIdList.add(areaUnitId);
            return sysUnitService.getAreaUnderIdList(areaUnitId);
        } else if (SysFilterUnitModeEnum.UNIT_AREA_LOGIN.equals(unitModeEnum)) {
            Integer areaUnitId = LoginUserUtils.getCurrentAreaId();
//            parentUnitIdList.add(areaUnitId);
            return sysUnitService.getAreaUnderIdList(areaUnitId);
        } else if (SysFilterUnitModeEnum.UNIT_AREA_LOGIN_TAG.equals(unitModeEnum)) {
            Integer areaUnitId = LoginUserUtils.getCurrentAreaId();
            Set<Integer> unitIdSet = sysUnitService.getAreaUnderIdList(areaUnitId);

            List<Integer> tagIdList = Arrays.stream((String[])StrUtil.split(unitData, ",").toArray())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            Set<String> tagUnitidSet = sysTagMapService.getAssIdSetByTagIdList(tagIdList);

            Set<Integer> agencyIdSet = unitIdSet.stream()
                    .filter(unitId -> tagUnitidSet.contains(String.valueOf(unitId)))
                    .collect(Collectors.toSet());

            parentUnitIdList.addAll(agencyIdSet);
        } else {
            throw new RuntimeException("mode错误");
        }

        // 根据父级单位id列表获取所有子集单位id集合
        sysUnitService.setChildrenId(parentUnitIdList);

        return parentUnitIdList;
    }

    /**
     * 获取前端选择单位公共组件数据
     * @return
     */
    @Override
    public SelectUnitVo getSelectUnitData(SysFilterUnitKeyEnum keyEnum) {
        List<SysUnit> list = sysUnitService.list();
        if (keyEnum != null) {
            Set<Integer> set = getUnitIdSet(keyEnum);
            list = list.stream().filter(sysUnit -> set.contains(sysUnit.getId())).collect(Collectors.toList());
        }
        // 获取区域选择树
        List<SysUnitVo> areaUnitTree = sysUnitService.getAreaTree(list);
        // 获取单位列表
        List<SysUnitVo> unitList = getUnitList(list);

        SelectUnitVo selectUnitVo = new SelectUnitVo();
        selectUnitVo.setSysUnitTree(areaUnitTree);
        selectUnitVo.setSysUnitList(unitList);

        return selectUnitVo;
    }

    private List<SysUnitVo> getUnitList(List<SysUnit> sysUnitList) {
        List<SysUnit> agencyList = sysUnitList.stream()
                .filter(sysUnit -> SysUnitTypeEnum.UNIT.equals(sysUnit.getType()))
                .collect(Collectors.toList());

        List<SysUnitVo> agencyVoList = ClassUtil.toClassList(agencyList, SysUnitVo.class);

        for (SysUnitVo sysUnitVo : agencyVoList) {
            List<SysUnit> inList = sysUnitService.getInList(sysUnitList, sysUnitVo.getId());
            inList = inList.subList(0, inList.size() - 1);
            sysUnitVo.setParentList(inList);
        }

        return agencyVoList;
    }

    /**
     * 获取子集列表
     * @param pid 父id
     * @param include 是否包含父
     * @return
     */
    @Override
    public List<SysUnit> getChildren(Integer pid, boolean include) {
        List<SysUnit> list = sysUnitService.list(new LambdaQueryWrapper<SysUnit>()
                .eq(SysUnit::getPid, pid)
                .or()
                .eq(include, SysUnit::getId, pid));
        return list;
    }

    /**
     * 根据id获取一直到第一级的单位列表
     * @param id
     * @return
     */
    @Override
    public List<SysUnit> getTopLineList(Integer id) {
        List<SysUnit> list = sysUnitService.list();
        List<SysUnit> sysUnitList = new LinkedList<>();
        Function<Integer, Integer> getPid = uid -> {
            for (SysUnit sysUnit : list) {
                if (sysUnit.getId().equals(uid)) {
                    return sysUnit.getPid();
                }
            }
            return null;
        };
        while (true) {
            Integer pid = getPid.apply(id);
            if (pid != null) {
                List<SysUnit> collect = list.stream().filter(sysUnit -> sysUnit.getPid().equals(pid)).collect(Collectors.toList());
                sysUnitList.addAll(collect);
            } else {
                break;
            }
            id = pid;
        }
        return sysUnitList;
    }

}
