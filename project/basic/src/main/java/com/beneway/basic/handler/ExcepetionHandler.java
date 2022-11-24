package com.beneway.basic.handler;

import com.beneway.basic.exception.ResultException;
import com.restful.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExcepetionHandler {

  @ExceptionHandler(value = ResultException.class)
  public Result ResultException(ResultException exception){
    return new Result(exception.getBody(),exception.getStatus());
  }

  @ExceptionHandler(SQLException.class)
  public Result SQLException(SQLException exception){
    return new Result("数据库产生了异常", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
