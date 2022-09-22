package com.beneway.basic.system.sys_role.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.system.sys_role.enums.SysRoleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-01 14:59:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 说明
     */
    private String remark;
    /**
     * 类型
     */
    private SysRoleTypeEnum type = SysRoleTypeEnum.USER;
    /**
     * 类型为范围是的单位标签
     */
    private String unitTags;
    /**
     * 类型为范围是的用户标签
     */
    private String userTags;
    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

}
