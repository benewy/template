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

package com.beneway.basic.system.sys_agency.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.basic.system.sys_agency.fo.SysAgencyQueryFo;
import com.beneway.basic.system.sys_agency.po.SysAgency;
import com.beneway.basic.system.sys_agency.vo.SysAgencyVo;
import com.restful.Result;

import java.util.List;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 15:30:50
 */
public interface SysAgencyService extends IService<SysAgency> {

    Result queryPage(SysAgencyQueryFo sysAgencyQueryFo);

    Result insert(SysAgency sysAgency);

    List<SysAgencyVo> getTreeListByPid(Integer pid);

    List<SysAgency> getByUser(String userID);

    /**
     * 寻找父机构
     * @param id
     * @return
     */
    SysAgency getFather(String id);


    String getAgency(String agencyId);
}

