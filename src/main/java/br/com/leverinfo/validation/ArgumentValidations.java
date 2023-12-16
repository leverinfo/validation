package br.com.leverinfo.validation;

import br.com.leverinfo.validation.exception.InvalidArgumentException;
import br.com.leverinfo.validation.exception.RequiredArgumentException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Recommended to handle with argument validations
 *
 * @author franciscosousabr
 */
public final class ArgumentValidations {

  /**
   * Throws {@link InvalidArgumentException} if value is not null
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   * @param <T> Value type
   */
  public static <T> void isNull(T value, ValidationMessage message) {
    if (Objects.nonNull(value)) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link RequiredArgumentException} if value is null
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   * @param <T> Value type
   */
  public static <T> void isNotNull(T value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    }
  }

  /**
   * Throws {@link RequiredArgumentException} if value is null or `InvalidArgumentException` if
   * value is not blank
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isBlank(CharSequence value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (!value.equals("")) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link RequiredArgumentException} if value is null or {@link InvalidArgumentException}
   * if value is blank
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isNotBlank(CharSequence value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (value.equals("")) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if all values are null
   *
   * @param values Desired values
   * @param message {@link ValidationMessage}
   * @param <T> Iterable type
   */
  public static <T> void anyIsNotNull(Iterable<T> values, ValidationMessage message) {
    if (ObjectUtils.allNull(
        StreamSupport.stream(values.spliterator(), false).toArray(Object[]::new))) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if all values are null or blank
   *
   * @param values Desired values
   * @param message {@link ValidationMessage}
   */
  public static void anyIsNotBlank(Iterable<CharSequence> values, ValidationMessage message) {
    if (StringUtils.isAllBlank(
        StreamSupport.stream(values.spliterator(), false).toArray(CharSequence[]::new))) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if more than one value is not null or if all values are
   * null
   *
   * @param values Desired values
   * @param message {@link ValidationMessage}
   * @param <T> Iterable type
   */
  public static <T> void onlyOneIsNotNull(Iterable<T> values, ValidationMessage message) {
    long notNullCount = 0;
    for (T value : values) {
      if (Objects.nonNull(value)) {
        notNullCount++;
      }

      if (notNullCount > 1) {
        throw new InvalidArgumentException(message);
      }
    }

    if (notNullCount == 0) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if more than one value is not blank or if all values
   * are blank
   *
   * @param values Desired values
   * @param message {@link ValidationMessage}
   */
  public static void onlyOneIsNotBlank(Iterable<CharSequence> values, ValidationMessage message) {
    long notBlankCount = 0;
    for (CharSequence value : values) {
      if (StringUtils.isNotBlank(value)) {
        notBlankCount++;
      }

      if (notBlankCount > 1) {
        throw new InvalidArgumentException(message);
      }
    }

    if (notBlankCount == 0) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link RequiredArgumentException} if value is null or {@link InvalidArgumentException}
   * if value is not empty
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   * @param <K> Map key type
   * @param <V> Map value type
   */
  public static <K, V> void isEmpty(Map<K, V> value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (!value.isEmpty()) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link RequiredArgumentException} if value is null or {@link InvalidArgumentException}
   * if value is not empty
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   * @param <T> Collection type
   */
  public static <T> void isEmpty(Collection<T> value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (!value.isEmpty()) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link RequiredArgumentException} if value is null or {@link InvalidArgumentException}
   * if value is empty
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   * @param <K> Map key type
   * @param <V> Map value type
   */
  public static <K, V> void isNotEmpty(Map<K, V> value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (value.isEmpty()) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link RequiredArgumentException} if value is null or {@link InvalidArgumentException}
   * if value is empty
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   * @param <T> Collection type
   */
  public static <T> void isNotEmpty(Collection<T> value, ValidationMessage message) {
    if (Objects.isNull(value)) {
      throw new RequiredArgumentException(message);
    } else if (value.isEmpty()) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   * @param <T> Value type
   */
  public static <T> void isEqualTo(T value, T other, ValidationMessage message) {
    if (!Objects.equals(value, other)) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   * @param <T> Value type
   */
  public static <T> void isNotEqualTo(T value, T other, ValidationMessage message) {
    if (Objects.equals(value, other)) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isNotEqualToZero(byte value, ValidationMessage message) {
    if (value == 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isNotEqualToZero(short value, ValidationMessage message) {
    if (value == 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isNotEqualToZero(int value, ValidationMessage message) {
    if (value == 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isNotEqualToZero(long value, ValidationMessage message) {
    if (value == 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isNotEqualToZero(float value, ValidationMessage message) {
    if (value == 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isNotEqualToZero(double value, ValidationMessage message) {
    if (value == 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isNotEqualToZero(BigDecimal value, ValidationMessage message) {
    if (value.compareTo(BigDecimal.ZERO) == 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThan(byte value, byte other, ValidationMessage message) {
    if (value >= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThan(short value, short other, ValidationMessage message) {
    if (value >= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThan(int value, int other, ValidationMessage message) {
    if (value >= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThan(long value, long other, ValidationMessage message) {
    if (value >= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThan(float value, float other, ValidationMessage message) {
    if (value >= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThan(double value, double other, ValidationMessage message) {
    if (value >= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   * @param <T> Comparable type
   */
  public static <T extends Comparable<T>> void isLessThan(
      T value, T other, ValidationMessage message) {
    if (value.compareTo(other) >= 0) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanZero(byte value, ValidationMessage message) {
    if (value >= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanZero(short value, ValidationMessage message) {
    if (value >= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanZero(int value, ValidationMessage message) {
    if (value >= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanZero(long value, ValidationMessage message) {
    if (value >= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanZero(float value, ValidationMessage message) {
    if (value >= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanZero(double value, ValidationMessage message) {
    if (value >= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanZero(BigDecimal value, ValidationMessage message) {
    if (value.compareTo(java.math.BigDecimal.ZERO) >= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualTo(byte value, byte other, ValidationMessage message) {
    if (value > other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualTo(short value, short other, ValidationMessage message) {
    if (value > other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualTo(int value, int other, ValidationMessage message) {
    if (value > other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualTo(long value, long other, ValidationMessage message) {
    if (value > other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualTo(float value, float other, ValidationMessage message) {
    if (value > other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualTo(double value, double other, ValidationMessage message) {
    if (value > other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   * @param <T> Comparable type
   */
  public static <T extends Comparable<T>> void isLessThanOrEqualTo(
      T value, T other, ValidationMessage message) {
    if (value.compareTo(other) > 0) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualToZero(byte value, ValidationMessage message) {
    if (value > 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualToZero(short value, ValidationMessage message) {
    if (value > 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualToZero(int value, ValidationMessage message) {
    if (value > 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualToZero(long value, ValidationMessage message) {
    if (value > 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualToZero(float value, ValidationMessage message) {
    if (value > 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualToZero(double value, ValidationMessage message) {
    if (value > 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is greater than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isLessThanOrEqualToZero(BigDecimal value, ValidationMessage message) {
    if (value.compareTo(BigDecimal.ZERO) > 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThan(byte value, byte other, ValidationMessage message) {
    if (value <= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThan(short value, short other, ValidationMessage message) {
    if (value <= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThan(int value, int other, ValidationMessage message) {
    if (value <= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThan(long value, long other, ValidationMessage message) {
    if (value <= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThan(float value, float other, ValidationMessage message) {
    if (value <= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThan(double value, double other, ValidationMessage message) {
    if (value <= other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   * @param <T> Comparable type
   */
  public static <T extends Comparable<T>> void isGreaterThan(
      T value, T other, ValidationMessage message) {
    if (value.compareTo(other) <= 0) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanZero(byte value, ValidationMessage message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanZero(short value, ValidationMessage message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanZero(int value, ValidationMessage message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanZero(long value, ValidationMessage message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanZero(float value, ValidationMessage message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanZero(double value, ValidationMessage message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than or equal to zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanZero(BigDecimal value, ValidationMessage message) {
    if (value.compareTo(java.math.BigDecimal.ZERO) <= 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualTo(byte value, byte other, ValidationMessage message) {
    if (value < other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualTo(short value, short other, ValidationMessage message) {
    if (value < other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualTo(int value, int other, ValidationMessage message) {
    if (value < other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualTo(long value, long other, ValidationMessage message) {
    if (value < other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualTo(float value, float other, ValidationMessage message) {
    if (value < other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualTo(double value, double other, ValidationMessage message) {
    if (value < other) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than other value
   *
   * @param value Desired value
   * @param other Value to compare
   * @param message {@link ValidationMessage}
   * @param <T> Comparable type
   */
  public static <T extends Comparable<T>> void isGreaterThanOrEqualTo(
      T value, T other, ValidationMessage message) {
    if (value.compareTo(other) < 0) {
      throw new InvalidArgumentException(message, value, other);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualToZero(byte value, ValidationMessage message) {
    if (value < 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualToZero(short value, ValidationMessage message) {
    if (value < 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualToZero(int value, ValidationMessage message) {
    if (value < 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualToZero(long value, ValidationMessage message) {
    if (value < 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualToZero(float value, ValidationMessage message) {
    if (value < 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualToZero(double value, ValidationMessage message) {
    if (value < 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is less than zero
   *
   * @param value Desired value
   * @param message {@link ValidationMessage}
   */
  public static void isGreaterThanOrEqualToZero(BigDecimal value, ValidationMessage message) {
    if (value.compareTo(BigDecimal.ZERO) < 0) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not between limits
   *
   * @param value Desired value
   * @param startValue Initial limit
   * @param endValue Final limit
   * @param message {@link ValidationMessage}
   */
  public static void isBetween(
      byte value, byte startValue, byte endValue, ValidationMessage message) {
    if (!(value >= startValue && value <= endValue)) {
      throw new InvalidArgumentException(message, value, startValue, endValue);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not between limits
   *
   * @param value Desired value
   * @param startValue Initial limit
   * @param endValue Final limit
   * @param message {@link ValidationMessage}
   */
  public static void isBetween(
      short value, short startValue, short endValue, ValidationMessage message) {
    if (!(value >= startValue && value <= endValue)) {
      throw new InvalidArgumentException(message, value, startValue, endValue);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not between limits
   *
   * @param value Desired value
   * @param startValue Initial limit
   * @param endValue Final limit
   * @param message {@link ValidationMessage}
   */
  public static void isBetween(int value, int startValue, int endValue, ValidationMessage message) {
    if (!(value >= startValue && value <= endValue)) {
      throw new InvalidArgumentException(message, value, startValue, endValue);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not between limits
   *
   * @param value Desired value
   * @param startValue Initial limit
   * @param endValue Final limit
   * @param message {@link ValidationMessage}
   */
  public static void isBetween(
      long value, long startValue, long endValue, ValidationMessage message) {
    if (!(value >= startValue && value <= endValue)) {
      throw new InvalidArgumentException(message, value, startValue, endValue);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not between limits
   *
   * @param value Desired value
   * @param startValue Initial limit
   * @param endValue Final limit
   * @param message {@link ValidationMessage}
   */
  public static void isBetween(
      float value, float startValue, float endValue, ValidationMessage message) {
    if (!(value >= startValue && value <= endValue)) {
      throw new InvalidArgumentException(message, value, startValue, endValue);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not between limits
   *
   * @param value Desired value
   * @param startValue Initial limit
   * @param endValue Final limit
   * @param message {@link ValidationMessage}
   */
  public static void isBetween(
      double value, double startValue, double endValue, ValidationMessage message) {
    if (!(value >= startValue && value <= endValue)) {
      throw new InvalidArgumentException(message, value, startValue, endValue);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not between limits
   *
   * @param value Desired value
   * @param startValue Initial limit
   * @param endValue Final limit
   * @param message {@link ValidationMessage}
   */
  public static void isBetween(
      BigDecimal value, BigDecimal startValue, BigDecimal endValue, ValidationMessage message) {
    if (!(value.compareTo(startValue) >= 0 && value.compareTo(endValue) <= 0)) {
      throw new InvalidArgumentException(message, value, startValue, endValue);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if condition is false
   *
   * @param condition Desired condition
   * @param message {@link ValidationMessage}
   */
  public static void isTrue(boolean condition, ValidationMessage message) {
    if (!condition) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if condition is true
   *
   * @param condition Desired condition
   * @param message {@link ValidationMessage}
   */
  public static void isFalse(boolean condition, ValidationMessage message) {
    if (condition) {
      throw new InvalidArgumentException(message);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value does not match with pattern
   *
   * @param value Desired value
   * @param pattern Desired pattern
   * @param message {@link ValidationMessage}
   */
  public static void matchesPattern(CharSequence value, String pattern, ValidationMessage message) {
    if (!Pattern.matches(pattern, value)) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if collection does not contain value
   *
   * @param value Desired value
   * @param collection Collection to validate
   * @param message {@link ValidationMessage}
   * @param <T> Value type
   */
  public static <T> void contains(
      T value, Collection<? extends T> collection, ValidationMessage message) {
    if (!collection.contains(value)) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if collection contains value
   *
   * @param value Desired value
   * @param collection Collection to validate
   * @param message {@link ValidationMessage}
   * @param <T> Value type
   */
  public static <T> void doesNotContain(
      T value, Collection<? extends T> collection, ValidationMessage message) {
    if (collection.contains(value)) {
      throw new InvalidArgumentException(message, value);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value has not desired size
   *
   * @param value Desired value
   * @param size Size to validate
   * @param message {@link ValidationMessage}
   */
  public static void hasSize(CharSequence value, int size, ValidationMessage message) {
    if (value.length() != size) {
      throw new InvalidArgumentException(message, value, size);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value has not desired size
   *
   * @param value Desired value
   * @param size Size to validate
   * @param message {@link ValidationMessage}
   * @param <K> Map key type
   * @param <V> Map value type
   */
  public static <K, V> void hasSize(Map<K, V> value, int size, ValidationMessage message) {
    if (value.size() != size) {
      throw new InvalidArgumentException(message, size);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value has not desired size
   *
   * @param value Desired value
   * @param size Size to validate
   * @param message {@link ValidationMessage}
   * @param <T> Collection type
   */
  public static <T> void hasSize(Collection<T> value, int size, ValidationMessage message) {
    if (value.size() != size) {
      throw new InvalidArgumentException(message, size);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value has not desired size range
   *
   * @param value Desired value
   * @param minSize Initial limit
   * @param maxSize Final limit
   * @param message {@link ValidationMessage}
   */
  public static void hasSizeBetween(
      CharSequence value, int minSize, int maxSize, ValidationMessage message) {
    if (value.length() < minSize || value.length() > maxSize) {
      throw new InvalidArgumentException(message, value, minSize, maxSize);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value has not desired size range
   *
   * @param value Desired value
   * @param minSize Initial limit
   * @param maxSize Final limit
   * @param message {@link ValidationMessage}
   * @param <K> Map key type
   * @param <V> Map value type
   */
  public static <K, V> void hasSizeBetween(
      Map<K, V> value, int minSize, int maxSize, ValidationMessage message) {
    if (value.size() < minSize || value.size() > maxSize) {
      throw new InvalidArgumentException(message, minSize, maxSize);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value has not desired size range
   *
   * @param value Desired value
   * @param minSize Initial limit
   * @param maxSize Final limit
   * @param message {@link ValidationMessage}
   * @param <T> Collection type
   */
  public static <T> void hasSizeBetween(
      Collection<T> value, int minSize, int maxSize, ValidationMessage message) {
    if (value.size() < minSize || value.size() > maxSize) {
      throw new InvalidArgumentException(message, minSize, maxSize);
    }
  }

  /**
   * Throws {@link InvalidArgumentException} if value is not of desired type
   *
   * @param value Desired value
   * @param type Desired type
   * @param message {@link ValidationMessage}
   * @param <T> Value type
   */
  public static <T> void isInstanceOf(T value, Class<?> type, ValidationMessage message) {
    if (!type.isInstance(value)) {
      throw new InvalidArgumentException(message, value, type);
    }
  }

  private ArgumentValidations() {}
}
