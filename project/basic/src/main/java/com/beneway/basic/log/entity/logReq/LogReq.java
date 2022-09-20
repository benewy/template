package com.beneway.basic.log.entity.logReq;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("log_req")
public class LogReq implements Serializable {
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
   * 请求地址
   */
  private String reqUrl;
  /**
   * 请求方法
   */
  private String reqMethod;
  /**
   * 请求ip
   */
  private String reqIp;
  /**
   * 请求参数
   */
  private String reqParams;
  /**
   * 请求body
   */
  private String reqBody;
  /**
   * 时长
   */
  private Integer duration;
  /**
   * 是否异常
   */
  private Boolean isError;
  /**
   * 异常信息
   */
  private String errorInfo;
  /**
   * 错误码
   */
  private String errorCode;
  /**
   * 错误消息
   */
  private String errorMsg;
  /**
   * 模块类型
   */
  private String moduleType;
  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

}
