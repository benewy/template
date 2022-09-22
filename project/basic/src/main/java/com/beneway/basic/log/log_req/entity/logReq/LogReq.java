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

package com.beneway.basic.log.log_req.entity.logReq;

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
