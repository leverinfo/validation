package br.com.leverinfo.validation;

/**
 * Represents a validation message structure
 *
 * @author franciscosousabr
 */
public interface ValidationMessage {

  /**
   * Gets validation code
   *
   * @return Validation code
   */
  String getCode();

  /**
   * Gets validation message
   *
   * @return Validation message
   */
  String getMessage();
}
