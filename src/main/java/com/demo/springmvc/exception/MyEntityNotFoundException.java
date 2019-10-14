package com.demo.springmvc.exception;

public class MyEntityNotFoundException extends RuntimeException {
  public MyEntityNotFoundException(String message){
    super(message);
  }
}
