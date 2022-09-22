package com.beneway.basic.system.sys_user.entity.vo;

import com.beneway.basic.system.sys_user.entity.po.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserVo extends SysUser {

  private List<Integer> unitList;

  private List<Integer> roleList;

  private List<Integer> tagList;

}

