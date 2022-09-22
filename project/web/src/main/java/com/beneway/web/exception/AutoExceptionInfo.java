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
