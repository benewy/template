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
