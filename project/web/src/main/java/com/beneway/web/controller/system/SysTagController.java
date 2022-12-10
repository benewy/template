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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.basic.system.sys_tag.po.SysTag;
import com.beneway.basic.system.sys_tag.service.SysTagService;
import com.restful.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/3/26 11:43
 */
@RestController
@RequestMapping("/sys_tag")
public class SysTagController {

    @Resource
    private SysTagService sysTagService;

    /**
     * 获取前端公共组件回显信息
     * @param id
     * @return
     */
    @GetMapping("/getComTagInfo")
    public Result getComTagInfo(@RequestParam Integer id) {
        SysTag sysTag = sysTagService.getOne(new LambdaQueryWrapper<SysTag>()
                .select(SysTag::getTagType, SysTag::getTagName)
                .eq(SysTag::getId, id));
        return Result.success(sysTag);
    }

}
