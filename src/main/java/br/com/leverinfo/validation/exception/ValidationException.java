package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

public abstract class ValidationException extends RuntimeException {

  private final transient ValidationMessage validationMessage;
  private final String code;

  protected ValidationException(ValidationMessage validationMessage) {
    super(validationMessage.getMessage());
    this.validationMessage = validationMessage;
    this.code = validationMessage.getCode();
  }

  public String getCode() {
    return code;
  }

  public ValidationMessage getValidationMessage() {
    return validationMessage;
  }
}
