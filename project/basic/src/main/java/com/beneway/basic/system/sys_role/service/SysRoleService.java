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

package com.beneway.basic.system.sys_role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.mybatisplus.MyIService;
import com.beneway.basic.system.sys_role.enums.SysRoleTypeEnum;
import com.beneway.basic.system.sys_role.fo.SysRoleFo;
import com.beneway.basic.system.sys_role.fo.SysRolePageQueryFo;
import com.beneway.basic.system.sys_role.po.SysRole;
import com.beneway.basic.system.sys_role.vo.SysRoleVo;
import com.restful.Result;

import java.util.List;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-01 14:59:40
 */
public interface SysRoleService extends MyIService<SysRole> {

    Result add(SysRoleFo sysRoleFo);

    Result edit(SysRoleFo SysRoleFo);

    Result del(Integer sysRoleId);

    Page<SysRoleVo> queryPage(SysRolePageQueryFo sysRolePageQueryFo);

    /**
     * 获取角色列表
     * @param type
     * @return
     */
    List<SysRole> getList(SysRoleTypeEnum type);

    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     */
    List<SysRole> getListByUserId(String userId);

    /**
     * 根据用户id获取角色id列表
     * @param userId
     * @return
     */
    List<Integer> getIdListByUserId(String userId);


    /**
     * 根据用户id获取菜单id列表
     * @param userId
     * @return
     */
    List<Integer> getMenuIdListByUserId(String userId);

    /**
     * 获取登录用户的菜单id列表
     * @return
     */
    List<Integer> getLoginUserOfMenuIdList();

}

