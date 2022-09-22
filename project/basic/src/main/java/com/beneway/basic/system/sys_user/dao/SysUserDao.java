package com.beneway.basic.system.sys_user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.system.sys_user.entity.fo.SysUserQueryFo;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.entity.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface SysUserDao extends BaseMapper<SysUser> {
  Page<SysUserVo> queryPage(Page page, @Param("params") SysUserQueryFo sysUserPageQueryFo);

  /**
   * 查询用户列表
   * @param unitIdList
   * @param tagIdList
   * @param userIdList
   * @return
   */
  List<SysUser> queryList(@Param("unitIdList") Collection<Integer> unitIdList,
                          @Param("tagIdList") Collection<Integer> tagIdList,
                          @Param("userIdList") Collection<String> userIdList);
}
