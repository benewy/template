package com.beneway.basic.system.sys_area.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/18 16:28
 */
public enum SysAreaLevelEnum {

    /**
     * 省级
     */
    PROVINCE(1),
    /**
     * 市级
     */
    CITY(2),
    /**
     * 区县级
     */
    AREA(3);

    @EnumValue
    @JsonValue
    private final Integer level;

    SysAreaLevelEnum(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }
}
