package com.beneway.basic.common.utils.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * Create by zhy on 2022/2/28 13:52
 *
 * 分页查询参数类
 */
@Data
public class PageQuery implements Serializable {

    private int page = 1;

    private int size = 10;

    private Boolean isMax = false;

    public Page getPageObj() {
        int size = isMax ? Integer.MAX_VALUE : this.size;
        Page page = new Page(this.page, size);
        return page;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }

}
