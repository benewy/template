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

package com.beneway.basic.system.sys_agency_user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_agency_user.dao.SysAgencyUserDao;
import com.beneway.basic.system.sys_agency_user.fo.SysAgencyUserFo;
import com.beneway.basic.system.sys_agency_user.fo.SysAgencyUserQueryFo;
import com.beneway.basic.system.sys_agency_user.po.SysAgencyUser;
import com.beneway.basic.system.sys_agency_user.service.SysAgencyUserService;
import com.beneway.basic.system.sys_agency_user.vo.SysAgencyUserVo;
import com.beneway.basic.utils.page.PageUtils;
import com.restful.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 18:27:25
 */
@Service("sysAgencyUserService")
public class SysAgencyUserServiceImpl extends ServiceImpl<SysAgencyUserDao, SysAgencyUser> implements SysAgencyUserService {

    @Autowired
    private SysAgencyUserDao sysAgencyUserDao;

    @Override
    public Result queryPage(SysAgencyUserQueryFo sysAgencyUserQueryFo) {
        Page page = PageUtils.getPage(sysAgencyUserQueryFo);
        Page<SysAgencyUserVo> voPage = sysAgencyUserDao.queryPage(page, sysAgencyUserQueryFo);
        return Result.success(voPage);
    }

    @Override
    public Result insert(SysAgencyUserFo sysAgencyUserFo) {
        return Result.success(save(sysAgencyUserFo));
    }
}
