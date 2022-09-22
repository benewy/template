package com.beneway.basic.system.sys_user.entity.fo;

import com.beneway.basic.system.sys_user.entity.po.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserFo extends SysUser {

  /**
   * 角色
   */
  private List<Integer> roleList;

  /**
   * 标签id列表
   */
  private List<Integer> tagList;
}
