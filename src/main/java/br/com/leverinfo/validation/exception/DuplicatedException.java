package br.com.leverinfo.validation.exception;

import br.com.leverinfo.validation.ValidationMessage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DuplicatedException extends ValidationException {

  private final transient Object[] params;

  public DuplicatedException(ValidationMessage validationMessage, Object... params) {
    super(validationMessage);
    this.params = params;
  }

  public Object[] getParams() {
    return params;
  }

  public String getStringParams() {
    return Stream.of(params).map(Object::toString).collect(Collectors.joining(","));
  }

  @Override
  public String toString() {
    return super.toString() + "(" + getStringParams() + ")";
  }
}
