package com.beneway.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.basic.system.sys_tag.po.SysTag;
import com.beneway.basic.system.sys_tag.service.SysTagService;
import com.restful.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
