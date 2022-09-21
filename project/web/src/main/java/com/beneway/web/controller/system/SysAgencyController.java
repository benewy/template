package com.beneway.web.controller.system;

import com.beneway.basic.system.sys_agency.fo.SysAgencyFo;
import com.beneway.basic.system.sys_agency.fo.SysAgencyQueryFo;
import com.beneway.basic.system.sys_agency.po.SysAgency;
import com.beneway.basic.system.sys_agency.service.SysAgencyService;
import com.beneway.basic.system.sys_agency.vo.SysAgencyVo;
import com.restful.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 15:30:50
 */
@CrossOrigin
@RestController
@RequestMapping("/sys_agency")
public class SysAgencyController {

    @Resource
    private SysAgencyService sysAgencyService;

    /**
     * 查询
     */
    @GetMapping("/")
    public Result find(@RequestParam("id") String id){
        SysAgency sysAgency = sysAgencyService.getById(id);
        return Result.success(sysAgency);
    }

    /**
     * 分页查询
     */
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody SysAgencyQueryFo sysAgencyQueryFo){
        return sysAgencyService.queryPage(sysAgencyQueryFo);
    }

    /**
     * 获取单位列表
     * @return
     */
    @GetMapping("/getTreeList")
    public Result getTreeList(){
        List<SysAgencyVo> treeList = sysAgencyService.getTreeListByPid(0);
        return Result.success(treeList);
    }


    /**
     * 插入
     */
    @PostMapping("/")
    public Result insert(@RequestBody SysAgencyFo sysAgencyFo){
        return sysAgencyService.insert(sysAgencyFo);
    }

    /**
     * 修改
     */
    @PutMapping("/")
    public Result update(@RequestBody SysAgencyFo sysAgencyFo){
        sysAgencyService.updateById(sysAgencyFo);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    public Result delete(@RequestParam("id") String id){
        sysAgencyService.removeById(id);
        return Result.success();
    }

}
