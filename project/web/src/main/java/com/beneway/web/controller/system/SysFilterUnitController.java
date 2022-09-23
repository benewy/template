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

import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitKeyEnum;
import com.beneway.basic.system.sys_filter_unit.service.SysFilterUnitService;
import com.beneway.basic.system.sys_filter_unit.vo.SelectUnitVo;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.restful.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by zhy on 2022/3/11 10:54
 */
@RestController
@RequestMapping("/sys_filter_unit")
public class SysFilterUnitController {

    @Resource
    private SysFilterUnitService sysFilterUnitService;

    /**
     * 获取前端选择单位公共组件数据
     * @return
     */
    @GetMapping("/getSelectUnitData")
    public Result getSelectUnitData(String key) {
        SysFilterUnitKeyEnum keyEnum = SysFilterUnitKeyEnum.getByKey(key);
        SelectUnitVo selectUnitVo = sysFilterUnitService.getSelectUnitData(keyEnum);
        return Result.success(selectUnitVo);
    }

    /**
     * 获取子集列表
     * @param pid 父id
     * @param include 是否包含父
     * @return
     */
    @GetMapping("/getChildren")
    public Result getChildren(Integer pid, boolean include) {
        pid = pid != null ? pid : 1;
        List<SysUnit> list = sysFilterUnitService.getChildren(pid, include);
        return Result.success(list);
    }

    /**
     * 根据id获取一直到第一级的单位列表
     * @param id
     * @return
     */
    @GetMapping("/getTopLineList")
    public Result getTopLineList(Integer id){
        List<SysUnit> topLineList = sysFilterUnitService.getTopLineList(id);
        return Result.success(topLineList);
    }

}
