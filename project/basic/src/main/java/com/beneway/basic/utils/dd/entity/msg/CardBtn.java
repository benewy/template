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

package com.beneway.basic.utils.dd.entity.msg;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2021/12/15
 * @time: 13:49
 */
@Data
@Builder
@Accessors(chain = true)
public class CardBtn implements Serializable {

    /**
     * 使用独立跳转ActionCard样式时的按钮的标题，最长20个字符
     */
    private String title;

    /**
     * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接，最长500个字符
     */
    private String action_url;

    public CardBtn() {
    }

    public CardBtn(String title, String action_url) {
        this.title = title;
        this.action_url = action_url;
    }
}
