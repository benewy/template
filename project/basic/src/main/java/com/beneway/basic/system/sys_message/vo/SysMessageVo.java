package com.beneway.basic.system.sys_message.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.beneway.basic.system.sys_message.po.SysMessage;
import lombok.Data;

@Data
public class SysMessageVo extends SysMessage {

    /**
     * 合同标题
     */
    @TableField(exist = false)
    private String title;

}
