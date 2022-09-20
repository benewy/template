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

