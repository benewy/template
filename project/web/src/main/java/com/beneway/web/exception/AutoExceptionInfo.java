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

package com.beneway.web.exception;

public class AutoExceptionInfo {

  private static final ThreadLocal<AutoExceptionInfo> exceptionThreadLocal = new ThreadLocal<>();

  private Integer code;

  private String msg;

  private Throwable e;

  private AutoExceptionInfo(Integer code, String msg, Throwable e){
    this.code = code;
    this.msg = msg;
    this.e = e;
  }

  public static void setExceptionInfo(Integer code, String msg, Throwable e) {
    AutoExceptionInfo myExceptionInfo = new AutoExceptionInfo(code, msg, e);
    exceptionThreadLocal.set(myExceptionInfo);
  }

  public static AutoExceptionInfo getExceptionInfo(){
    AutoExceptionInfo myExceptionInfo = exceptionThreadLocal.get();
    exceptionThreadLocal.remove();
    return myExceptionInfo;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public Throwable getE() {
    return e;
  }
}
