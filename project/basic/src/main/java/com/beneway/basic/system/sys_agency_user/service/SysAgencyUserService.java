package com.beneway.basic.system.sys_agency_user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.basic.system.sys_agency_user.fo.SysAgencyUserFo;
import com.beneway.basic.system.sys_agency_user.po.SysAgencyUser;
import com.beneway.basic.system.sys_agency_user.fo.SysAgencyUserQueryFo;
import com.restful.Result;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 18:27:25
 */
public interface SysAgencyUserService extends IService<SysAgencyUser> {

    Result queryPage(SysAgencyUserQueryFo sysAgencyUserQueryFo);

    Result insert(SysAgencyUserFo sysAgencyUserFo);

}

