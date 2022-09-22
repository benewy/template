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

package com.beneway.basic.system.sys_message.modules.enums;

import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.system.sys_message.modules.impl.ZwddPhoneMessageModuleServiceImpl;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/27 17:46
 *
 * 消息模块枚举类
 */
public enum MessageModuleEnum {

    /**
     * 政务钉钉手机端
     */
    ZWDD_PHONE(UserTypeEnum.SYSTEM, ZwddPhoneMessageModuleServiceImpl.class, "政务钉钉手机端", true);

    /**
     * 用户类型
     */
    private final UserTypeEnum userTypeEnum;
    /**
     * 执行方法类
     */
    private final Class aClass;
    /**
     * 描述
     */
    private final String desc;
    /**
     * 是否生产环境才能执行
     */
    private final boolean isProdSend;

    MessageModuleEnum(UserTypeEnum userTypeEnum, Class aClass, String desc, boolean isProdSend) {
        this.userTypeEnum = userTypeEnum;
        this.aClass = aClass;
        this.desc = desc;
        this.isProdSend = isProdSend;
    }

    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    public Class getaClass() {
        return aClass;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isProdSend() {
        return isProdSend;
    }
}
