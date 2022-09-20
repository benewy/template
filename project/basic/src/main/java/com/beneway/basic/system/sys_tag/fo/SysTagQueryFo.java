package com.beneway.basic.system.sys_tag.fo;

import com.beneway.basic.utils.page.PageQuery;
import lombok.Data;

/**
 * Create by zhy on 2022/2/28 16:18
 */
@Data
public class SysTagQueryFo extends PageQuery {

    /**
     *
     */
    private Integer id;

    /**
     * 分类
     */
    private String type;

    /**
     * 说明
     */
    private String remake;

}
