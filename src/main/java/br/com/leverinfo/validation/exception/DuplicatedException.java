package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

/**
 * Recommended to be thrown when some item is duplicated
 *
 * @author franciscosousabr
 */
public class DuplicatedException extends ValidationException {

  /**
   * Creates the exception
   *
   * @param validationMessage {@link ValidationMessage}
   * @param params Parameter list
   */
  public DuplicatedException(ValidationMessage validationMessage, Object... params) {
    super(validationMessage, params);
  }
}
