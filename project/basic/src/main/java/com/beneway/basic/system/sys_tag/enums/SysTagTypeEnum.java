package com.beneway.basic.system.sys_tag.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SysTagTypeEnum {


    /**
     * 用户
     */
    USER("U"),

    /**
     * 单位
     */
    AGENCY("A"),

    /**
     * 合同
     */
    CONTRACT("C");

    @EnumValue
    @JsonValue
    private final String type;

    SysTagTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SysTagTypeEnum getByType(String type) {
        for (SysTagTypeEnum value : values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }

}
