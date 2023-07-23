package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Base exception for validations
 *
 * @author franciscosousabr
 */
public abstract class ValidationException extends RuntimeException {

  private final transient ValidationMessage validationMessage;
  private final transient String code;
  private final transient Object[] params;

  /**
   * Creates the exception
   *
   * @param validationMessage {@link ValidationMessage}
   * @param params Parameter list
   */
  protected ValidationException(ValidationMessage validationMessage, Object... params) {
    super(validationMessage.getMessage());
    this.validationMessage = validationMessage;
    this.code = validationMessage.getCode();
    this.params = params;
  }

  /**
   * Gets exception {@link ValidationMessage}
   *
   * @return Exception {@link ValidationMessage}
   */
  public ValidationMessage getValidationMessage() {
    return validationMessage;
  }

  /**
   * Gets validation code
   *
   * @return Validation code
   */
  public String getCode() {
    return code;
  }

  /**
   * Gets params array
   *
   * @return Params array
   */
  public Object[] getParams() {
    return params;
  }

  /**
   * Gets params array as string
   *
   * @return Params array as string
   */
  public String getStringParams() {
    return Stream.of(params).map(Object::toString).collect(Collectors.joining(","));
  }

  @Override
  public String toString() {
    return super.toString() + "(" + getStringParams() + ")";
  }
}
