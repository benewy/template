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
