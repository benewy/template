package com.beneway.web.controller.system;

import com.beneway.basic.system.sys_agency_user.po.SysAgencyUser;
import com.beneway.basic.system.sys_agency_user.fo.SysAgencyUserFo;
import com.beneway.basic.system.sys_agency_user.fo.SysAgencyUserQueryFo;
import com.beneway.basic.system.sys_agency_user.service.SysAgencyUserService;
import com.beneway.basic.system.sys_agency_user.vo.SysAgencyUserVo;
import com.beneway.basic.utils.ClassUtil;
import com.restful.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 18:27:25
 */
@CrossOrigin
@RestController
@RequestMapping("/sys_agency_user")
public class SysAgencyUserController {

    @Resource
    private SysAgencyUserService sysAgencyUserService;

    /**
     * 查询
     */
    @GetMapping("/")
    public Result find(@RequestParam("id") String id){
        SysAgencyUser sysAgencyUser = sysAgencyUserService.getById(id);
        SysAgencyUserVo vo = ClassUtil.toClass(sysAgencyUser, SysAgencyUserVo.class);
        return Result.success(vo);
    }

    /**
     * 分页查询
     */
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody SysAgencyUserQueryFo sysAgencyUserQueryFo){
        return sysAgencyUserService.queryPage(sysAgencyUserQueryFo);
    }


    /**
     * 插入
     */
    @PostMapping("/")
    public Result insert(@RequestBody SysAgencyUserFo sysAgencyUserFo){
        return sysAgencyUserService.insert(sysAgencyUserFo);
    }

    /**
     * 修改
     */
    @PutMapping("/")
    public Result update(@RequestBody SysAgencyUserFo sysAgencyUserFo){
        sysAgencyUserService.updateById(sysAgencyUserFo);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    public Result delete(@RequestParam("id") String id){
        sysAgencyUserService.removeById(id);
        return Result.success();
    }

}
