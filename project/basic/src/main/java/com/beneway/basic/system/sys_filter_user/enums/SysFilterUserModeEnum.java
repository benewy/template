package com.beneway.basic.system.sys_filter_user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Create by zhy on 2022/3/10 18:10
 */
public enum SysFilterUserModeEnum {

    /**
     * 用户标签
     */
    USER_TAG("T", "用户标签"),
    /**
     * 用户列表
     */
    USER_LIST("L" ,"用户列表");

    @EnumValue
    @JsonValue
    private final String mode;

    private final String desc;

    SysFilterUserModeEnum(String mode, String desc) {
        this.mode = mode;
        this.desc = desc;
    }

    public String getMode() {
        return mode;
    }

    public String getDesc() {
        return desc;
    }

    public static SysFilterUserModeEnum getByMode(String mode) {
        for (SysFilterUserModeEnum value : values()) {
            if (value.getMode().equals(mode)) {
                return value;
            }
        }
        return null;
    }

}
