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

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2021/12/15
 * @time: 13:44
 * <p>
 * 钉钉工作通知，卡片消息
 */
@Data
@Accessors(chain = true)
public class Card implements Serializable {

    /**
     * 标题
     * 透出到会话列表和通知的文案，最长64个字符
     */
    private String title;

    /**
     * 消息内容
     * 消息内容，目前不支持markdown，只能传纯文本
     */
    private String markdown;

    /**
     * 使用整体跳转ActionCard样式时的标题，必须与single_url同时设置，最长20个字符
     * 这俩参数和按钮不共存，一旦有这个参数，那么按钮就没了，自动变成'查看详情>'
     */
    private String single_title;

    /**
     * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接，最长500个字符
     * 这俩参数和按钮不共存，一旦有这个参数，那么按钮就没了，自动变成'查看详情>'
     */
    private String single_url;

    /**
     * 使用独立跳转ActionCard样式时的按钮排列方式，竖直排列(0)，横向排列(1)；必须与btn_json_list同时设置
     */
    private String btn_orientation;

    /**
     * 使用独立跳转ActionCard样式时的按钮列表；必须与btn_orientation同时设置
     */
    private List<CardBtn> btn_json_list;

    public Card() {
        this.title = "工作通知";
        this.btn_orientation = "1";
        //如果要跳转小程序，则需要生成跳转链接，http链接仅访问H5
        // addBtn(new CardBtn("前往查看", "https://hetong.zssfj.zhoushan.gov.cn:8083/new-contract-zs-web-mobile/#/?id=bd0cd92dc6f4524169bfe670426831b5"));
    }

    public void addBtn(CardBtn cardBtn) {
        if (this.btn_json_list == null) {
            this.btn_json_list = new LinkedList<>();
        }
        this.btn_json_list.add(cardBtn);
    }
}
