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

package com.beneway.basic.system.sys_unit.service.implement;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_tag.enums.SysTagTypeEnum;
import com.beneway.basic.system.sys_tag.po.SysTag;
import com.beneway.basic.system.sys_tag.po.SysTagMap;
import com.beneway.basic.system.sys_tag.service.SysTagMapService;
import com.beneway.basic.system.sys_tag.service.SysTagService;
import com.beneway.basic.system.sys_unit.dao.SysUnitDao;
import com.beneway.basic.system.sys_unit.entity.fo.SysUnitFo;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitComVo;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_unit.entity.vo.TreeUnit;
import com.beneway.basic.system.sys_unit.enums.SysUnitTypeEnum;
import com.beneway.basic.system.sys_unit.service.SysUnitService;
import com.beneway.basic.system.sys_user_unit.entity.po.SysUserUnit;
import com.beneway.basic.system.sys_user_unit.service.SysUserUnitService;
import com.beneway.basic.utils.ClassUtil;
import com.restful.Result;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@EnableAspectJAutoProxy( proxyTargetClass = true , exposeProxy = true )
@CacheConfig(cacheNames = "sysUnit")
@Service("sysUnitService")
public class SysUnitServiceImplement extends ServiceImpl<SysUnitDao, SysUnit> implements SysUnitService {

  @Resource
  private SysTagService sysTagService;

  @Resource
  private SysTagMapService sysTagMapService;

  @Resource
  private SysUserUnitService sysUserUnitService;

  private SysUnitService getCurrThis(){
    SysUnitService currentProxy = (SysUnitService) AopContext.currentProxy();
    return currentProxy;
  }

  @Cacheable(key = "'list'")
  @Override
  public List<SysUnit> list() {
    return super.list(new LambdaQueryWrapper<SysUnit>().orderByAsc(SysUnit::getSortNum));
  }

  @CacheEvict(key = "'list'")
  @Transactional(rollbackFor = Exception.class)
  @Override
  public Result add(SysUnitFo sysUnitFo) {
    this.save(sysUnitFo);

    List<Integer> tagList = sysUnitFo.getTagList();
    sysTagMapService.addTagMap(String.valueOf(sysUnitFo.getId()), tagList, SysTagTypeEnum.AGENCY);

    return Result.success();
  }

  @CacheEvict(key = "'list'")
  @Transactional(rollbackFor = Exception.class)
  @Override
  public Result edit(SysUnitFo sysUnitFo) {
    this.updateById(sysUnitFo);

    List<Integer> tagList = sysUnitFo.getTagList();
    sysTagMapService.updateTagMap(String.valueOf(sysUnitFo.getId()), tagList, SysTagTypeEnum.AGENCY);

    return Result.success();
  }

  @CacheEvict(key = "'list'")
  @Transactional(rollbackFor = Exception.class)
  @Override
  public Result del(Integer id) {
    this.removeById(id);
    sysTagMapService.removeTagMap(String.valueOf(id), SysTagTypeEnum.AGENCY);
    return Result.success();
  }

  @Override
  public List<SysUnitVo> getTreeListByPid(Integer pid) {
    List<SysUnit> list = getCurrThis().list();
    return getTreeListByPid(list, pid);
  }

  @Override
  public List<SysUnitVo> getTreeListByPid(List<SysUnit> list, Integer pid) {
    List<SysTag> sysTagList = sysTagService.list();
    List<SysTagMap> sysTagMapList = sysTagMapService.list(new LambdaQueryWrapper<SysTagMap>()
      .eq(SysTagMap::getType, SysTagTypeEnum.AGENCY));
    Function<Integer, List<Integer>> getTagListFun = assId -> {
      List<SysTagMap> collect = sysTagMapList.stream()
        .filter(sysTagMap -> sysTagMap.getAssId().equals(String.valueOf(assId)))
        .collect(Collectors.toList());

      List<Integer> sysTagIdList = collect.stream()
        .map(SysTagMap::getSysTagId)
        .collect(Collectors.toList());

      return sysTagIdList;
    };

    List<SysUnitVo> sysUnitVoList = new ArrayList<>(list.size());
    for (SysUnit sysUnit : list) {
      SysUnitVo sysUnitVo = ClassUtil.toClass(sysUnit, SysUnitVo.class);
      // 封装标签
      List<Integer> tagIdList = getTagListFun.apply(sysUnitVo.getId());
      sysUnitVo.setTagList(tagIdList);

      sysUnitVoList.add(sysUnitVo);
    }

    List<SysUnitVo> fList = sysUnitVoList.stream().filter(sysUnitVo -> pid.equals(sysUnitVo.getPid())).collect(Collectors.toList());
    children(fList, sysUnitVoList);
    return fList;
  }

  private void children(List<SysUnitVo> fList, List<SysUnitVo> list){
    for (SysUnitVo sysUnitVo : fList) {
      Integer id = sysUnitVo.getId();
      List<SysUnitVo> children = list.stream().filter(sysUnit -> sysUnit.getPid().equals(id)).collect(Collectors.toList());
      if (CollUtil.isNotEmpty(children)){
        sysUnitVo.setChildren(children);
        children(children, list);
      }
    }
  }

  /**
   * 根据父级id获取下级所有单位id列表
   * @return
   */
  @Override
  public Set<Integer> getIdListByPid(Integer pid) {
    List<SysUnit> list = getCurrThis().list();
    return getIdListByPid(list, pid);
  }

  @Override
  public Set<Integer> getIdListByPid(Collection<SysUnit> list, Integer pid) {
    Set<Integer> unitIdSet = new HashSet<>();
    unitIdSet.add(pid);
    Set<Integer> pidSet = new HashSet<>();
    pidSet.add(pid);
    while (CollUtil.isNotEmpty(pidSet)){
      Set<Integer> set = new HashSet<>();
      for (SysUnit sysUnit : list) {
        if (pidSet.contains(sysUnit.getPid())){
          set.add(sysUnit.getId());
        }
      }
      unitIdSet.addAll(set);
      pidSet = new HashSet<>(set);
    }
    return unitIdSet;
  }

  /**
   * 获取区域下的下级所有单位id列表
   * @param areaId
   * @return
   */
  @Override
  public Set<Integer> getAreaUnderIdList(Integer areaId) {
    List<SysUnit> list = getCurrThis().list();

    Set<Integer> unitIdSet = new HashSet<>();
    unitIdSet.add(areaId);
    Set<Integer> pidSet = new HashSet<>();
    pidSet.add(areaId);
    while (CollUtil.isNotEmpty(pidSet)){
      Set<Integer> set = new HashSet<>();
      for (SysUnit sysUnit : list) {
        if (pidSet.contains(sysUnit.getPid())
          && !sysUnit.getType().equals(SysUnitTypeEnum.AREA)){
          set.add(sysUnit.getId());
        }
      }
      unitIdSet.addAll(set);
      pidSet = new HashSet<>(set);
    }

    return unitIdSet;
  }

  /**
   * 获取单位从大到小的列表
   * @param unitId
   * @return
   */
  @Override
  public List<SysUnit> getInList(Integer unitId) {
    List<SysUnit> list = getCurrThis().list();
    return getInList(list, unitId);
  }

  @Override
  public List<SysUnit> getInList(List<SysUnit> sysUnitList, Integer unitId) {
    List<SysUnit> list = new LinkedList<>();
    while (unitId != null && unitId != 0) {
      for (SysUnit sysUnit : sysUnitList) {
        if (unitId.equals(sysUnit.getId())) {
          list.add(sysUnit);
          unitId = sysUnit.getPid();
          break;
        }
      }
    }
    Collections.reverse(list);
    return list;
  }

  /**
   * 根据类型获取单位线中的最小单位对象
   * @param sysUnitList
   * @param typeEnum
   * @return
   */
  @Override
  public SysUnit getInListOfType(List<SysUnit> sysUnitList, SysUnitTypeEnum typeEnum) {
    List<SysUnit> collect = sysUnitList
      .stream()
      .filter(sysUnit -> sysUnit.getType().equals(typeEnum))
      .collect(Collectors.toList());
    SysUnit sysUnit = collect.get(collect.size() - 1);
    return sysUnit;
  }

  @Override
  public Integer getIdInListOfType(List<SysUnit> sysUnitList, SysUnitTypeEnum typeEnum) {
    SysUnit sysUnit = getInListOfType(sysUnitList, typeEnum);
    return sysUnit.getId();
  }

  /**
   * 将单位列表转为tree
   * @param list
   * @return
   */
  @Override
  public List<SysUnitVo> toTreeVo(List<SysUnitVo> list) {
    Set<Integer> isChildrenList = new HashSet<>();
    for (SysUnitVo sysUnitVo : list) {
      Integer id = sysUnitVo.getId();
      for (SysUnitVo unitVo : list) {
        if (id.equals(unitVo.getPid())) {
          List<SysUnitVo> children = sysUnitVo.getChildren();
          if (children == null) {
            children = new ArrayList<>();
            sysUnitVo.setChildren(children);
          }
          children.add(unitVo);
          isChildrenList.add(unitVo.getId());
        }
      }
    }

    return list.stream()
      .filter(sysUnitVo -> !isChildrenList.contains(sysUnitVo.getId()))
      .collect(Collectors.toList());
  }

  @Override
  public List<SysUnitVo> toTree(List<SysUnit> list) {
    List<SysUnitVo> voList = ClassUtil.toClassList(list, SysUnitVo.class);
    return toTreeVo(voList);
  }

  @Override
  public void setChildrenId(Collection<Integer> unitIdList) {
    // 根据父级单位id列表获取所有子集单位id集合
    List<SysUnit> list = getCurrThis().list();
    boolean b;
    do {
      b = false;
      for (SysUnit sysUnit : list) {
        if (unitIdList.contains(sysUnit.getPid()) && !unitIdList.contains(sysUnit.getId())) {
          unitIdList.add(sysUnit.getId());
          b = true;
        }
      }
    } while (b);
  }

  /**
   * 获取前端统一组件的单位信息
   * @return
   */
  @Override
  public SysUnitComVo getComUnitInfo(Integer unitId) {
    List<SysUnit> inList = getCurrThis().getInList(unitId);
    List<SysUnit> unitList = new ArrayList<>();
    for (SysUnit sysUnit : inList) {
      SysUnit unit = new SysUnit();
      unit.setUnitName(sysUnit.getUnitName());
      unit.setType(sysUnit.getType());
      unitList.add(unit);
    }
    SysUnitComVo sysUnitComVo = new SysUnitComVo();
    sysUnitComVo.setUnitList(unitList);
    return sysUnitComVo;
  }

  /**
   * 获取单位区域树集合
   * @return
   */
  @Override
  public List<SysUnitVo> getAreaTree() {
    List<SysUnit> list = getCurrThis().list();
    return getAreaTree(list);
  }

  @Override
  public List<SysUnitVo> getAreaTree(List<SysUnit> list) {
    list = list.stream()
      .filter(sysUnit -> SysUnitTypeEnum.AREA.equals(sysUnit.getType())
        || SysUnitTypeEnum.NODE.equals(sysUnit.getType()))
      .collect(Collectors.toList());
    List<SysUnitVo> voList = toTree(list);
    return voList;
  }

  @Override
  public TreeUnit getTreeListById(Integer id) {
    TreeUnit treeUnit = new TreeUnit();
    //通过id获取核心单位 => 后期可以修改成为通过名字进行查找
    SysUnit unit = this.getById(id);
    //判断单位是否存在
    if (unit == null) { return treeUnit; }
    //将需要的数据放入treeUnit
    treeUnit.setId(unit.getId());
    treeUnit.setName(unit.getUnitName());
    //获取在单位中工作的人员
    List<SysUserUnit> users = sysUserUnitService.getUsersByUnitId(treeUnit.getId());
    treeUnit.setPeopleIds(treeUnit.listToString(users, ","));
    treeUnit.setChildren(getTreeList(unit.getId()));
    return treeUnit;
  }

  private List<TreeUnit> getTreeList(Integer pid) {
    List<TreeUnit> treeUnitList = new ArrayList<>();
    LambdaQueryWrapper<SysUnit> unitQueryWrapper = new LambdaQueryWrapper<>();
    unitQueryWrapper.eq(SysUnit::getPid, pid);
    List<SysUnit> list = this.list(unitQueryWrapper);
    list.forEach(t -> {
      TreeUnit treeUnit = new TreeUnit();
      treeUnit.setId(t.getId());
      treeUnit.setName(t.getUnitName());
      treeUnit.setChildren(getTreeList(t.getId()));
      //获取在单位中工作的人员
      List<SysUserUnit> users = sysUserUnitService.getUsersByUnitId(treeUnit.getId());
      treeUnit.setPeopleIds(treeUnit.listToString(users, ","));
      treeUnit.setChildren(getTreeList(t.getId()));
      treeUnitList.add(treeUnit);
    });
    return treeUnitList;
  }
}
