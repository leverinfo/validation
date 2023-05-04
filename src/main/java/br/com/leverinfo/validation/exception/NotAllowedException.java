package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

public class NotAllowedException extends ValidationException {

  public NotAllowedException(ValidationMessage validationMessage) {
    super(validationMessage);
  }
}
