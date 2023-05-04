package br.com.leverinfo.validation;

import br.com.leverinfo.validation.exception.InvalidArgumentException;
import br.com.leverinfo.validation.exception.RequiredArgumentException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.StringUtils;

public final class ArgumentValidations {

  public static void isNotNull(Object value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    }
  }

  public static void isNotBlank(String value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (value.equals("")) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void anyIsNotBlank(Iterable<String> values, ValidationMessage message) {
    if (StringUtils.isAllBlank(
        StreamSupport.stream(values.spliterator(), false).toArray(String[]::new))) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void onlyOneIsNotNull(Iterable<Object> values, ValidationMessage message) {
    boolean anyIsNotNull = false;
    for (Object value : values) {
      if (Objects.nonNull(value)) {
        if (anyIsNotNull) {
          throw new InvalidArgumentException(message);
        }

        anyIsNotNull = true;
      }
    }

    if (!anyIsNotNull) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void onlyOneIsNotBlank(Iterable<CharSequence> values, ValidationMessage message) {
    boolean anyIsNotBlank = false;
    for (CharSequence value : values) {
      if (StringUtils.isNotBlank(value)) {
        if (anyIsNotBlank) {
          throw new InvalidArgumentException(message);
        }

        anyIsNotBlank = true;
      }
    }

    if (!anyIsNotBlank) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isNotEmpty(Map<?, ?> value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (value.isEmpty()) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isNotEmpty(Collection<?> value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (value.isEmpty()) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanZero(int value, ValidationMessage message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanZero(long value, ValidationMessage message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanZero(BigDecimal value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThan(int value, int other, ValidationMessage message) {
    if (value <= other) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThan(long value, long other, ValidationMessage message) {
    if (value <= other) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThan(BigDecimal value, BigDecimal other, ValidationMessage message) {
    if (value.compareTo(other) <= 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanOrEqualToZero(int value, ValidationMessage message) {
    if (value < 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanOrEqualToZero(long value, ValidationMessage message) {
    if (value < 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanOrEqualToZero(BigDecimal value, ValidationMessage message) {
    if (value.compareTo(BigDecimal.ZERO) < 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanOrEqualTo(int value, int other, ValidationMessage message) {
    if (value < other) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanOrEqualTo(long value, long other, ValidationMessage message) {
    if (value < other) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isGreaterThanOrEqualTo(
      BigDecimal value, BigDecimal other, ValidationMessage message) {
    if (value.compareTo(other) < 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isLessThanOrEqualTo(
      BigDecimal value, BigDecimal other, ValidationMessage message) {
    if (value.compareTo(other) > 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isNotEqualToZero(int value, ValidationMessage message) {
    if (value == 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isNotEqualToZero(long value, ValidationMessage message) {
    if (value == 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isNotEqualToZero(BigDecimal value, ValidationMessage message) {
    if (value.compareTo(BigDecimal.ZERO) == 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isBetween(int value, int startValue, int endValue, ValidationMessage message) {
    if (!(value >= startValue && value <= endValue)) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isBetween(
      long value, long startValue, long endValue, ValidationMessage message) {
    if (!(value >= startValue && value <= endValue)) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isBetween(
      BigDecimal value, BigDecimal startValue, BigDecimal endValue, ValidationMessage message) {
    if (!(value.compareTo(startValue) >= 0 && value.compareTo(endValue) <= 0)) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isEqualTo(Object value, Object other, ValidationMessage message) {
    if (!Objects.equals(value, other)) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isNotEqualTo(Object value, Object other, ValidationMessage message) {
    if (Objects.equals(value, other)) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isTrue(boolean condition, ValidationMessage message) {
    if (!condition) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isFalse(boolean condition, ValidationMessage message) {
    if (condition) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void matchesPattern(CharSequence value, String pattern, ValidationMessage message) {
    if (!Pattern.matches(pattern, value)) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void contains(Object value, Collection<?> collection, ValidationMessage message) {
    if (!collection.contains(value)) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void hasSize(CharSequence value, int size, ValidationMessage message) {
    if (value.length() != size) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void hasSize(Collection<?> value, int size, ValidationMessage message) {
    if (value.size() != size) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void hasSizeBetween(
      CharSequence value, int minSize, int maxSize, ValidationMessage message) {
    if (value.length() < minSize || value.length() > maxSize) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void hasSizeBetween(
      Collection<?> value, int minSize, int maxSize, ValidationMessage message) {
    if (value.size() < minSize || value.size() > maxSize) {
      throw new InvalidArgumentException(message);
    }
  }

  private ArgumentValidations() {}
}
