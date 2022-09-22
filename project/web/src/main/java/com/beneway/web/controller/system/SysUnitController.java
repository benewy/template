package com.beneway.web.controller.system;

import com.beneway.basic.system.sys_unit.entity.vo.SysUnitComVo;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_unit.service.SysUnitService;
import com.restful.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
