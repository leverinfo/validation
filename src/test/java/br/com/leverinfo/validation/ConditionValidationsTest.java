package br.com.leverinfo.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import br.com.leverinfo.validation.exception.NotAllowedException;
import org.junit.jupiter.api.Test;

class ConditionValidationsTest {

  @Test
  void testIsTrue_Success() {
    assertThatCode(() -> ConditionValidations.isTrue(true, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsTrue_Error() {
    NotAllowedException notAllowedException =
        catchThrowableOfType(
            () -> ConditionValidations.isTrue(false, Validations.ANY_VALIDATION),
            NotAllowedException.class);

    assertThat(notAllowedException.getValidationMessage()).isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsFalse_Success() {
    assertThatCode(() -> ConditionValidations.isFalse(false, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsFalse_Error() {
    NotAllowedException notAllowedException =
        catchThrowableOfType(
            () -> ConditionValidations.isFalse(true, Validations.ANY_VALIDATION),
            NotAllowedException.class);

    assertThat(notAllowedException.getValidationMessage()).isEqualTo(Validations.ANY_VALIDATION);
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
