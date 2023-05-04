package br.com.leverinfo.validation;


import br.com.leverinfo.validation.exception.NotAllowedException;

public final class ConditionValidations {

  public static void isTrue(boolean condition, ValidationMessage message) {
    if (!condition) {
      throw new NotAllowedException(message);
    }
  }

  public static void isFalse(boolean condition, ValidationMessage message) {
    if (condition) {
      throw new NotAllowedException(message);
    }
  }

  private ConditionValidations() {}
}
