package com.beneway.basic.system.sys_filter_unit.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Create by zhy on 2022/3/10 18:00
 */
public enum SysFilterUnitModeEnum {

    /**
     * 单位id入参（只用于后台调用）用户使用
     */
    UNIT_ID_IN("I", "单位id入参（只用于后台调用）用户使用"),
    /**
     * 登录用户单位 用户使用
     */
    UNIT_LOGIN("D", "登录用户单位 用户使用"),

    /**
     * 单位标签
     */
    UNIT_TAG("T", "单位标签"),
    /**
     * 单位列表
     */
    UNIT_LIST("L", "单位列表"),
    /**
     * 区域范围
     */
    UNIT_AREA("A", "区域范围"),
    /**
     * 登录用户第一级区域范围
     */
    UNIT_AREA_LOGIN("G", "登录用户第一级区域范围"),
    /**
     * 登录用户的第一级区域范围下，单位标签
     */
    UNIT_AREA_LOGIN_TAG("R", "登录用户的第一级区域范围下，单位标签");

    @EnumValue
    @JsonValue
    private final String mode;

    private final String desc;

    SysFilterUnitModeEnum(String mode, String desc) {
        this.mode = mode;
        this.desc = desc;
    }

    public String getMode() {
        return mode;
    }

    public String getDesc() {
        return desc;
    }

    public static SysFilterUnitModeEnum getByMode(String mode) {
        for (SysFilterUnitModeEnum value : values()) {
            if (value.getMode().equals(mode)) {
                return value;
            }
        }
        return null;
    }

}
