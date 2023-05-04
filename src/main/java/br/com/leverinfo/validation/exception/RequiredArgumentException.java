package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

public class RequiredArgumentException extends ValidationException {

  public RequiredArgumentException(ValidationMessage validationMessage) {
    super(validationMessage);
  }
}
