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

package com.beneway.basic.system.sys_unit.service;

import com.beneway.basic.mybatisplus.MyIService;
import com.beneway.basic.system.sys_unit.entity.fo.SysUnitFo;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitComVo;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_unit.entity.vo.TreeUnit;
import com.beneway.basic.system.sys_unit.enums.SysUnitTypeEnum;
import com.restful.Result;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SysUnitService extends MyIService<SysUnit> {

  Result add(SysUnitFo sysUnitFo);

  Result edit(SysUnitFo sysUnitFo);

  Result del(Integer id);

  /**
   * 根据父级id获取单位树列表
   * @param pid
   * @return
   */
  List<SysUnitVo> getTreeListByPid(Integer pid);

  List<SysUnitVo> getTreeListByPid(List<SysUnit> sysUnitList, Integer pid);

  /**
   * 根据父级id获取下级所有单位id列表
   * @return
   */
  Set<Integer> getIdListByPid(Integer pid);

  Set<Integer> getIdListByPid(Collection<SysUnit> sysUnitList, Integer pid);

  /**
   * 获取区域下的下级所有单位id列表
   * @param areaId
   * @return
   */
  Set<Integer> getAreaUnderIdList(Integer areaId);

  /**
   * 获取单位从大到小的列表
   * @param unitId
   * @return
   */
  List<SysUnit> getInList(Integer unitId);

  /**
   * 获取单位从大到小的列表
   * @param sysUnitList 单位列表
   * @param unitId
   * @return
   */
  List<SysUnit> getInList(List<SysUnit> sysUnitList, Integer unitId);

  /**
   * 根据类型获取单位线中的最小单位对象
   * @param sysUnitList
   * @param typeEnum
   * @return
   */
  SysUnit getInListOfType(List<SysUnit> sysUnitList, SysUnitTypeEnum typeEnum);

  Integer getIdInListOfType(List<SysUnit> sysUnitList, SysUnitTypeEnum typeEnum);

  /**
   * 将单位列表转为tree
   * @param list
   * @return
   */
  List<SysUnitVo> toTreeVo(List<SysUnitVo> list);

  List<SysUnitVo> toTree(List<SysUnit> list);

  /**
   * 设置所有子单位id
   * @param unitIdList
   */
  void setChildrenId(Collection<Integer> unitIdList);

  /**
   * 获取前端统一组件的单位信息
   * @return
   */
  SysUnitComVo getComUnitInfo(Integer unitId);

  /**
   * 获取单位区域树集合
   * @return
   */
  List<SysUnitVo> getAreaTree();

  List<SysUnitVo> getAreaTree(List<SysUnit> sysUnitList);

  /**
   * 通过主键ID获取到单位对象和他们的附属对象
   * @param id 单位主键ID
   * @return 单位对象和他们的附属对象
   */
  TreeUnit getTreeListById(Integer id);
}
