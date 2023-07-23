package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

/**
 * Recommended to be thrown when some argument value is missing
 *
 * @author franciscosousabr
 */
public class RequiredArgumentException extends ValidationException {

  /**
   * Creates the exception
   *
   * @param validationMessage {@link ValidationMessage}
   * @param params Parameter list
   */
  public RequiredArgumentException(ValidationMessage validationMessage, Object... params) {
    super(validationMessage, params);
  }
}
