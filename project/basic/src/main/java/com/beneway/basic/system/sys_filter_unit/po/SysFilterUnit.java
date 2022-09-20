package com.beneway.basic.system.sys_filter_unit.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitKeyEnum;
import com.beneway.basic.system.sys_filter_unit.enums.SysFilterUnitModeEnum;
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
 * @date 2022-03-10 20:14:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_filter_unit")
public class SysFilterUnit implements Serializable {
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
    private SysFilterUnitKeyEnum key;
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

}
