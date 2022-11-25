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

import com.beneway.basic.system.sys_unit.entity.vo.SysUnitComVo;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_unit.service.SysUnitService;
import com.restful.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create by zhy on 2022/3/8 11:48
 */
@RestController()
@RequestMapping("/sys_unit")
public class SysUnitController {

    @Resource
    private SysUnitService sysUnitService;

    /**
     * 获取前端统一组件的单位信息
     * @return
     */
    @GetMapping("/getComUnitInfo")
    public Result getComUnitInfo(@RequestParam Integer unitId) {
        SysUnitComVo comUnitInfo = sysUnitService.getComUnitInfo(unitId);
        return Result.success(comUnitInfo);
    }

    /**
     * 获取单位区域树集合
     * @return
     */
    @GetMapping("/getAreaTree")
    public Result getAreaTree() {
        List<SysUnitVo> areaTree = sysUnitService.getAreaTree();
        return Result.success(areaTree);
    }

}
