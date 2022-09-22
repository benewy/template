package com.beneway.basic.system.sys_filter_unit.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Create by zhy on 2022/3/10 20:20
 */
public enum SysFilterUnitKeyEnum {

    /**
     * 登录用户第一级区域
     */
    LOGIN_USER_FIRST_AREA("LOGIN_USER_FIRST_AREA");

    @EnumValue
    @JsonValue
    private final String key;

    SysFilterUnitKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static SysFilterUnitKeyEnum getByKey(String key) {
        for (SysFilterUnitKeyEnum value : values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return null;
    }

}
