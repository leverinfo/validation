package br.com.leverinfo.validation;

import br.com.leverinfo.validation.exception.NotAllowedException;

/**
 * Recommended to handle with condition validations
 *
 * @author franciscosousabr
 */
public final class ConditionValidations {

  /**
   * Throws {@link NotAllowedException} if condition is false
   *
   * @param condition Desired condition
   * @param message {@link ValidationMessage}
   */
  public static void isTrue(boolean condition, ValidationMessage message) {
    if (!condition) {
      throw new NotAllowedException(message);
    }
  }

  /**
   * Throws {@link NotAllowedException} if condition is true
   *
   * @param condition Desired condition
   * @param message {@link ValidationMessage}
   */
  public static void isFalse(boolean condition, ValidationMessage message) {
    if (condition) {
      throw new NotAllowedException(message);
    }
  }

  private ConditionValidations() {}
}
