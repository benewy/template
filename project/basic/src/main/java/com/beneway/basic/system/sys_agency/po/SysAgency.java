package com.beneway.basic.system.sys_agency.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 14:50:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_agency")
public class SysAgency implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 排序
     */
    private String seq;
    /**
     * 父id
     */
    private Integer pid;

    /**
     * 用户id
     */
    private String userIds;

    /**
     * 审核用户id
     */
    private String managerIds;
    /**
     * 是否为父级单位
     */
    private String isFather;
}
