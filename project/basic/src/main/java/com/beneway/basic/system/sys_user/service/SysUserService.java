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

package com.beneway.basic.system.sys_user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.mybatisplus.MyIService;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_user.entity.fo.SysUserFo;
import com.beneway.basic.system.sys_user.entity.fo.SysUserQueryFo;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.entity.vo.LoginUserInfo;
import com.beneway.basic.system.sys_user.entity.vo.SysUserComVo;
import com.beneway.basic.system.sys_user.entity.vo.SysUserVo;
import com.restful.Result;

import java.util.Collection;
import java.util.List;

public interface SysUserService extends MyIService<SysUser> {

  /**
   * 密码加密
   * @param password
   * @return
   */
  String passEncr(String password);

  /**
   * 用户新增
   * @param sysUserFo
   * @return
   */
  Result add(SysUserFo sysUserFo);

  /**
   * 用户编辑
   * @param sysUserFo
   * @return
   */
  Result edit(SysUserFo sysUserFo);

  /**
   * 用户删除
   * @param userId
   * @return
   */
  Result del(String userId);

  /**
   * 获取登录用户信息
   * @param userId
   * @return
   */
  LoginUserInfo getLoginUserInfo(String userId);

  /**
   * 切换用户当前单位
   * @param userId
   * @param unitId
   */
  void switchCurrentUnit(String userId, Integer unitId);

  /**
   * 清空获取登录用户信息缓存
   * @param userId
   */
  void clearLoginUserInfoCacheable(String userId);

  /**
   * 判断登录用户是否有权限
   * @param permission
   * @return
   */
  boolean isLoginUserPermission(String permission);

  /**
   * 获取用户管理分页查询 单位列表
   * @return
   */
  List<SysUnit> getQueryPageUnitList();

  /**
   * 用户管理分页查询
   * @param sysUserPageQueryFo
   * @return
   */
  Page<SysUserVo> queryPage(SysUserQueryFo sysUserPageQueryFo);

  /**
   * 获取用户信息
   * @param userId
   * @return
   */
  SysUserVo getUserInfo(String userId);

  /**
   * 获取前端统一组件的用户信息
   * @return
   */
  SysUserComVo getComUserInfo(String userId);

  /**
   * 查询用户列表
   * @param unitIdList
   * @param tagIdList
   * @param userIdList
   * @return
   */
  List<SysUser> queryList(Collection<Integer> unitIdList, Collection<Integer> tagIdList, Collection<String> userIdList);

  List<SysUser> getComUserInfoList(String userIds);
}
