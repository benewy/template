package com.beneway.basic.system.sys_config.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统配置表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-18 10:16:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_config")
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 配置项key
     */
    @TableField("`key`")
    private String key;
    /**
     * 配置值
     */
    @TableField("`value`")
    private String value;

}
