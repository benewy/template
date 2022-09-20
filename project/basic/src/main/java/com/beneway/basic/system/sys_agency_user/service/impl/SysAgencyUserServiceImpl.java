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
