package com.beneway.basic.system.sys_area.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.system.sys_area.enums.SysAreaLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 行政区域表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-18 16:24:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_area")
public class SysArea implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 父级id
     */
    private Integer pid;
    /**
     * 名称
     */
    @TableField("`name`")
    private String name;
    /**
     * 级别 1：省级 2：市级 3：区县级
     */
    @TableField("`level`")
    private SysAreaLevelEnum level;

}
