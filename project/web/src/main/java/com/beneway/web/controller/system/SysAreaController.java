package com.beneway.web.controller.system;

import com.beneway.basic.system.sys_area.po.SysArea;
import com.beneway.basic.system.sys_area.service.SysAreaService;
import com.restful.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/18 17:37
 */
@RestController
@RequestMapping("/sys_area")
public class SysAreaController {

    @Autowired
    private SysAreaService sysAreaService;

    /**
     * 获取区域选择列表
     * @return
     */
    @GetMapping("/getSelectList")
    public Result getSelectList() {
        List<SysArea> list = sysAreaService.list();
        return Result.success(list);
    }

}
