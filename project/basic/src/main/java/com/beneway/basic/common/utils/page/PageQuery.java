/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

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
