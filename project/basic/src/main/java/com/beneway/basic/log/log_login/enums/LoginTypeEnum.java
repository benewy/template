package com.beneway.basic.log.log_login.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/22 15:23
 */
public enum LoginTypeEnum {

    FORM("F", "表单登录"),

    QRCODE("Q", "扫码登录"),

    NO_SECRET("N", "免密登录");

    /**
     * 类型
     */
    @EnumValue
    @JsonValue
    private final String type;
    /**
     * 描述
     */
    private final String desc;


    LoginTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
