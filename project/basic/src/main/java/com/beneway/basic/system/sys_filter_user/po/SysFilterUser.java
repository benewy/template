package com.beneway.basic.system.sys_filter_user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitModeEnum;
import com.beneway.basic.system.sys_filter_user.enums.SysFilterUserKeyEnum;
import com.beneway.basic.system.sys_filter_user.enums.SysFilterUserModeEnum;
import com.beneway.basic.system.sys_filter_user.enums.SysFilterUserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户筛选配置
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-10 17:54:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_filter_user")
public class SysFilterUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 业务key
     */
    @TableField("`key`")
    private SysFilterUserKeyEnum key;
    /**
     * 类型
     */
    private SysFilterUserTypeEnum type;
    /**
     * 说明
     */
    private String remark;
    /**
     * 单位模式
     */
    private SysFilterUnitModeEnum unitMode;
    /**
     * 单位数据
     */
    private String unitData;
    /**
     * 用户模式
     */
    private SysFilterUserModeEnum userMode;
    /**
     * 用户数据
     */
    private String userData;

}
