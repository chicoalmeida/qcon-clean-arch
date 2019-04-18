package com.chico.cleanarch.domain.usecase.expection;

public class BusinessException extends RuntimeException {

  public BusinessException(final String message) {
    super(message);
  }
}
