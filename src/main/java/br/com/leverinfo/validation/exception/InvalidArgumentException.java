package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

/**
 * Recommended to be thrown when some argument value is invalid
 *
 * @author franciscosousabr
 */
public class InvalidArgumentException extends ValidationException {

  /**
   * Creates the exception
   *
   * @param validationMessage {@link ValidationMessage}
   * @param params Parameter list
   */
  public InvalidArgumentException(ValidationMessage validationMessage, Object... params) {
    super(validationMessage, params);
  }
}
