package com.beneway.basic.system.sys_user.entity.fo;

import com.beneway.basic.utils.page.PageQuery;
import lombok.Data;

import java.util.Collection;

/**
 * Create by zhy on 2022/2/28 16:18
 */
@Data
public class SysUserQueryFo extends PageQuery {

    /**
     * 用户名
     */
    private String username;

    /**
     * 单位id
     */
    private Integer unitId;

    /**
     * 单位列表
     */
    private Collection<Integer> unitIdList;

}
