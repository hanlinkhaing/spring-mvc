package com.demo.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMvc3Application {

  private static Logger
          logger= LoggerFactory.getLogger(SpringMvc3Application.class);



  public static void main(String[] args) {
    SpringApplication.run(SpringMvc3Application.class, args);
  }






}
