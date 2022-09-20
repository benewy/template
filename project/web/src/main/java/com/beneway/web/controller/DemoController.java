package com.beneway.web.controller;

import com.restful.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/demo")
public class DemoController {
  @GetMapping("/")
  public Result demo(){
    return Result.success("ok");
  }
}
