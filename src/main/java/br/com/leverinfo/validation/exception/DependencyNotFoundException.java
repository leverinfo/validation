package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;

/**
 * Recommended to be thrown when some dependency is not found
 *
 * @author franciscosousabr
 */
public class DependencyNotFoundException extends ValidationException {

  /**
   * Creates the exception
   *
   * @param validationMessage {@link ValidationMessage}
   * @param params Parameter list
   */
  public DependencyNotFoundException(ValidationMessage validationMessage, Object... params) {
    super(validationMessage, params);
  }
}
