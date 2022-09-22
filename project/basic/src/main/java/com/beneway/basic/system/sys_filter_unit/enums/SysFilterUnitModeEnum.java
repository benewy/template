/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

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
