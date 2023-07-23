package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

/**
 * Recommended to be thrown when some operation is not allowed
 *
 * @author franciscosousabr
 */
public class NotAllowedException extends ValidationException {

  /**
   * Creates the exception
   *
   * @param validationMessage {@link ValidationMessage}
   * @param params Parameter list
   */
  public NotAllowedException(ValidationMessage validationMessage, Object... params) {
    super(validationMessage, params);
  }
}
