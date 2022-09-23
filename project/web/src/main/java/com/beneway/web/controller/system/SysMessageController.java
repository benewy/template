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
