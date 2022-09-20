package com.beneway.basic.system.sys_filter_user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Create by zhy on 2022/3/10 19:37
 */
public enum SysFilterUserTypeEnum {

    /**
     * 全部
     */
    ALL("A"),
    /**
     * 用户
     */
    USER("U"),
    /**
     * 单位
     */
    UNIT("N");

    @EnumValue
    @JsonValue
    private final String type;

    SysFilterUserTypeEnum(String type) {
        this.type = type;
    }

}
