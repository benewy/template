package com.beneway.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.system.sys_message.fo.SysMessageQueryFo;
import com.beneway.basic.system.sys_message.service.SysMessageService;
import com.beneway.basic.system.sys_message.vo.SysMessageVo;
import com.beneway.basic.utils.AppUtils;
import com.restful.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/11 13:11
 */
@RestController
@RequestMapping("/sys_message")
public class SysMessageController {

    @Autowired
    private SysMessageService sysMessageService;

    @Autowired
    private AppUtils appUtils;

    /**
     * 获取当前用户信息列表，分页
     *
     * @return
     * @author yhz
     * @create 2022/4/12 17:00
     * @Param
     **/
    @PostMapping("/queryPageMessage")
    public Result queryPageMessage(@RequestBody SysMessageQueryFo sysMessageQueryFo) {
        String loginUserId = appUtils.getLoginUserId();
        sysMessageQueryFo.setLookUser(loginUserId);
        UserTypeEnum userType = appUtils.getLoginUserType();
        sysMessageQueryFo.setUserType(userType);
        Page<SysMessageVo> iPage = sysMessageService.queryPageMessage(sysMessageQueryFo);
        return Result.success(iPage);
    }

    /**
     * 设置已读
     *
     * @return
     * @author yhz
     * @create 2022/4/12 17:00
     * @Param
     **/
    @PostMapping("/isRead")
    public Result isRead(@RequestBody List<String> ids) {
        sysMessageService.isRead(ids);
        return Result.success();
    }

    /**全部已读
     * @author  yhz
     * @create  2022/4/14 15:37
     * @Param
     * @return
     **/
    @GetMapping("/isReadAll")
    public Result isReadAll() {
        sysMessageService.isReadAll(appUtils.getLoginUserId());
        return Result.success();
    }

    /**
     * 删除已读信息
     *
     * @return
     * @author yhz
     * @create 2022/4/12 17:00
     * @Param
     **/
    @GetMapping("/clear")
    public Result clear() {
        sysMessageService.clear(appUtils.getLoginUserId());
        return Result.success();
    }

    /**
     * 剩余未读信息数
     *
     * @return
     * @author yhz
     * @create 2022/4/12 17:01
     * @Param
     **/
    @GetMapping("/messageUnread")
    public Result messageUnread() {
        Integer integer = sysMessageService.messageUnread(appUtils.getLoginUserId());
        return Result.success(integer);
    }
}
