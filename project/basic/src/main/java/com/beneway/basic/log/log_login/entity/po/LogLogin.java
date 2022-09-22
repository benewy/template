package com.beneway.basic.log.log_login.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.log.log_login.enums.LoginTypeEnum;
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
 * 登录日志表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-22 15:21:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("log_login")
public class LogLogin implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户类型
     */
    private UserTypeEnum userType;
    /**
     * 登录类型
     */
    private LoginTypeEnum loginType;
    /**
     * 模块类型
     */
    private String moduleType;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
