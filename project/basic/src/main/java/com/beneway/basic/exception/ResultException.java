package com.beneway.basic.exception;

import org.springframework.http.HttpStatus;

public class ResultException extends RuntimeException {

  private HttpStatus status;

  private Object body;

  public ResultException(HttpStatus status, Object body){
    this.status = status;
    this.body = body;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public Object getBody() {
    return body;
  }

  public void setBody(Object body) {
    this.body = body;
  }
}
