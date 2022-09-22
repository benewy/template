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
