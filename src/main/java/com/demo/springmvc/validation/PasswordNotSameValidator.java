package com.demo.springmvc.validation;

import com.demo.springmvc.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordNotSameValidator
        implements ConstraintValidator<PasswordNotSame, User> {
  @Override
  public boolean isValid(User user, ConstraintValidatorContext context) {
    return user.getPassword().equals(user.getConfirmPassword());
  }
}
