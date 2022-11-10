package com.beneway;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AppTest {

}

@SpringBootApplication
class UtApplication {
  static void main(String[] args) {
    SpringApplication.run(UtApplication.class, args);
  }
}
