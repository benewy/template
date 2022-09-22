package com.beneway.basic.log.log_sql.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/21 9:55
 */
public enum LogSqlTypeEnum {

    /**
     * 更新
     */
    UPDATE("U"),
    /**
     * 删除
     */
    DETELE("D");

    /**
     * 类型
     */
    @EnumValue
    @JsonValue
    private final String type;

    LogSqlTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
