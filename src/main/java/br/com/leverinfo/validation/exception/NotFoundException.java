package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

/**
 * Recommended to be thrown when some item is not found
 *
 * @author franciscosousabr
 */
public class NotFoundException extends ValidationException {

  /**
   * Creates the exception
   *
   * @param validationMessage {@link ValidationMessage}
   * @param params Parameter list
   */
  public NotFoundException(ValidationMessage validationMessage, Object... params) {
    super(validationMessage, params);
  }
}
