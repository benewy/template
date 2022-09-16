package com.beneway.basic.common.system.entity.sys_user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 钉钉员工code
     */
    private String employeeCode;
    /**
     * 钉钉用户id
     */
    private String zwddAccountId;
    /**
     * 用户当前单位索引
     */
    private Integer currentUnitIndex;
    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;



}
