package com.beneway.basic.system.sys_message.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.common.common.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-06 19:04:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_message")
public class SysMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 合同id
     */
    private String problemId;
    /**
     * 查看人
     */
    private String lookUser;
    /**
     * 查看人用户类型
     */
    private UserTypeEnum userType;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 创建人(发送人) 默认为空
     */
    private String createUser;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 查看时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lookTime;

}
