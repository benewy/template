package com.beneway;

import logon.tracer.helper.AlarmLogHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AppTest {

  @Test
  public void testLogonTracer() {
    AlarmLogHelper.getPrintLogInstance().error("123", new Exception("teshi"));
  }

}

@SpringBootApplication
class UtApplication {
  static void main(String[] args) {
    SpringApplication.run(UtApplication.class, args);
  }
}
