package com.beneway.basic.system.sys_tag.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.system.sys_tag.enums.SysTagTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wkx
 * @email 1181597045@qq.com
 * @date 2022-03-04 14:04:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_tag_map")
public class SysTagMap implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 类型
     */
    private SysTagTypeEnum type;
    /**
     * 关联tagId
     */
    private Integer sysTagId;
    /**
     * 关联映射表id
     */
    private String assId;

}
