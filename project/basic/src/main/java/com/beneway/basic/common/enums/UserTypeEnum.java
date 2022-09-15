package com.beneway.basic.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/12 15:02
 *
 * 用户类型枚举
 */
public enum UserTypeEnum {

    /**
     * 系统
     */
    SYSTEM("S");

    /**
     * 类型
     */
    @EnumValue
    @JsonValue
    private final String type;

    UserTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static UserTypeEnum getByType(String type) {
        for (UserTypeEnum value : values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }

}
