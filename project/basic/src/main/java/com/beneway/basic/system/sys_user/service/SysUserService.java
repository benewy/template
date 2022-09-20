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
