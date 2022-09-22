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
