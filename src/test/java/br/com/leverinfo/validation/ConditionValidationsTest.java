package br.com.leverinfo.validation;

import static br.com.leverinfo.test.ValidationAssertions.assertThatNotAllowedException;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class ConditionValidationsTest {

  @Test
  void testIsTrue_Success() {
    assertThatCode(() -> ConditionValidations.isTrue(true, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsTrue_Error() {
    assertThatNotAllowedException()
        .isThrownBy(() -> ConditionValidations.isTrue(false, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsFalse_Success() {
    assertThatCode(() -> ConditionValidations.isFalse(false, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsFalse_Error() {
    assertThatNotAllowedException()
        .isThrownBy(() -> ConditionValidations.isFalse(true, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  private enum Validations implements ValidationMessage {
    ANY_VALIDATION("0", "Any validation message");

    private final String code;
    private final String message;

    Validations(String code, String message) {
      this.code = code;
      this.message = message;
    }

    @Override
    public String getCode() {
      return code;
    }

    @Override
    public String getMessage() {
      return message;
    }
  }
}
