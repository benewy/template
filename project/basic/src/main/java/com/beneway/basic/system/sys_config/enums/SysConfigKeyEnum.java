package com.beneway.basic.system.sys_config.enums;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/18 10:21
 */
public enum SysConfigKeyEnum {

    /**
     * h5地址
     */
    h5Url("h5Url"),

    /**
     * 相对方端地址
     */
    companyUrl("companyUrl");

    private final String key;

    SysConfigKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
