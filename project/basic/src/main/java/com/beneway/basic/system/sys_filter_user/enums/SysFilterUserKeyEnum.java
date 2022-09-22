package com.beneway.basic.system.sys_filter_user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Create by zhy on 2022/3/10 19:52
 */
public enum SysFilterUserKeyEnum {

    /**
     * 全部用户
     */
    ALL_USER("ALL_USER"),

    /**
     * 登录用户同单位用户
     */
    LOGIN_UNIT("LOGIN_UNIT"),

    /**
     * 根据单位获取操作人员标签的用户列表 后台调用
     */
    USER_OPT_LIST("USER_OPT_LIST"),

    /**
     * 登录用户单位审查人员用户列表
     */
    USER_SC_LIST("USER_SC_LIST"),

    /**
     * 登录用户一级区域下的司法局，审查人员用户列表
     */
    LOGIN_SCJ_SC_LIST("LOGIN_SCJ_SC_LIST"),

    /**
     * 根据单位获取管理人员标签的用户列表 后台调用
     */
    USER_UNIT_ADMIN_LIST("USER_UNIT_ADMIN_LIST");

    @EnumValue
    @JsonValue
    private final String key;

    SysFilterUserKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static SysFilterUserKeyEnum getByKey(String key) {
        for (SysFilterUserKeyEnum value : values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return null;
    }

}
