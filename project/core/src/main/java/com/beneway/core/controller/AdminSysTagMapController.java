package com.beneway.core.controller;

import com.beneway.basic.system.sys_tag.service.SysTagMapService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin_sys_tagMap")
public class AdminSysTagMapController {

    @Resource
    SysTagMapService sysTagMapService;



}
