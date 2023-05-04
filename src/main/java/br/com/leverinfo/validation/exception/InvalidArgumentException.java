package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

public class InvalidArgumentException extends ValidationException {

  public InvalidArgumentException(ValidationMessage validationMessage) {
    super(validationMessage);
  }
}
