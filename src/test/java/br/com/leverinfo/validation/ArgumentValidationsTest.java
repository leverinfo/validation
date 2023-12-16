package br.com.leverinfo.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import br.com.leverinfo.validation.exception.InvalidArgumentException;
import br.com.leverinfo.validation.exception.RequiredArgumentException;
import java.math.BigDecimal;
import java.util.*;
import org.junit.jupiter.api.Test;

class ArgumentValidationsTest {

  @Test
  void testIsNull_Success() {
    assertThatCode(() -> ArgumentValidations.isNull(null, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNull_Error() {
    Object value = new Object();
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNull(value, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsNotNull_Success() {
    assertThatCode(() -> ArgumentValidations.isNotNull(new Object(), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotNull_Error() {
    RequiredArgumentException requiredArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotNull(null, Validations.ANY_VALIDATION),
            RequiredArgumentException.class);

    assertThat(requiredArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsBlank_Success() {
    assertThatCode(() -> ArgumentValidations.isBlank("", Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBlank_Error() {
    RequiredArgumentException requiredArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isBlank(null, Validations.ANY_VALIDATION),
            RequiredArgumentException.class);

    assertThat(requiredArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isBlank("Any string", Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsNotBlank_Success() {
    assertThatCode(() -> ArgumentValidations.isNotBlank("Any string", Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotBlank_Error() {
    RequiredArgumentException requiredArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotBlank(null, Validations.ANY_VALIDATION),
            RequiredArgumentException.class);

    assertThat(requiredArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotBlank("", Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testAnyIsNotNull_Success() {
    final Set<Object> objects = new HashSet<>();
    objects.add(new Object());
    objects.add(new Object());
    objects.add(null);

    assertThatCode(() -> ArgumentValidations.anyIsNotNull(objects, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testAnyIsNotNull_Error() {
    final Set<Object> objects = new HashSet<>();
    objects.add(null);
    objects.add(null);
    objects.add(null);

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.anyIsNotNull(objects, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testAnyIsNotBlank_Success() {
    final Set<CharSequence> strings = new HashSet<>();
    strings.add("Any string");
    strings.add("Another string");
    strings.add(null);
    strings.add("");

    assertThatCode(() -> ArgumentValidations.anyIsNotBlank(strings, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testAnyIsNotBlank_Error() {
    final Set<CharSequence> nullStrings = new HashSet<>();
    nullStrings.add(null);
    nullStrings.add(null);
    nullStrings.add(null);

    InvalidArgumentException invalidArgumentExceptionForNull =
        catchThrowableOfType(
            () -> ArgumentValidations.anyIsNotBlank(nullStrings, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionForNull.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    final List<CharSequence> blankStrings = new ArrayList<>();
    blankStrings.add("");
    blankStrings.add("");
    blankStrings.add("");

    InvalidArgumentException invalidArgumentExceptionForBlank =
        catchThrowableOfType(
            () -> ArgumentValidations.anyIsNotBlank(blankStrings, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionForBlank.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testOnlyOneIsNotNull_Success() {
    final Set<Object> objects = new HashSet<>();
    objects.add(new Object());
    objects.add(null);
    objects.add(null);

    assertThatCode(() -> ArgumentValidations.onlyOneIsNotNull(objects, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testOnlyOneIsNotNull_Error() {
    final Set<Object> moreThanOneNotNullObjects = new HashSet<>();
    moreThanOneNotNullObjects.add(new Object());
    moreThanOneNotNullObjects.add(new Object());
    moreThanOneNotNullObjects.add(null);

    InvalidArgumentException invalidArgumentExceptionMoreThanOneNotNull =
        catchThrowableOfType(
            () ->
                ArgumentValidations.onlyOneIsNotNull(
                    moreThanOneNotNullObjects, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionMoreThanOneNotNull.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    final Set<Object> allNullObjects = new HashSet<>();
    allNullObjects.add(null);
    allNullObjects.add(null);
    allNullObjects.add(null);

    InvalidArgumentException invalidArgumentExceptionAllNull =
        catchThrowableOfType(
            () -> ArgumentValidations.onlyOneIsNotNull(allNullObjects, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionAllNull.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testOnlyOneIsNotBlank_Success() {
    final Set<CharSequence> strings = new HashSet<>();
    strings.add("Any string");
    strings.add("");
    strings.add(null);

    assertThatCode(() -> ArgumentValidations.onlyOneIsNotBlank(strings, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testOnlyOneIsNotBlank_Error() {
    final Set<CharSequence> moreThanOneNotBlankStrings = new HashSet<>();
    moreThanOneNotBlankStrings.add("Any string");
    moreThanOneNotBlankStrings.add("Another string");
    moreThanOneNotBlankStrings.add("");
    moreThanOneNotBlankStrings.add(null);

    InvalidArgumentException invalidArgumentExceptionMoreThanOneNotBlank =
        catchThrowableOfType(
            () ->
                ArgumentValidations.onlyOneIsNotBlank(
                    moreThanOneNotBlankStrings, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionMoreThanOneNotBlank.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    final Set<CharSequence> allBlankOrNullStrings = new HashSet<>();
    allBlankOrNullStrings.add("");
    allBlankOrNullStrings.add(null);
    allBlankOrNullStrings.add(null);

    InvalidArgumentException invalidArgumentExceptionAllBlankOrNull =
        catchThrowableOfType(
            () ->
                ArgumentValidations.onlyOneIsNotBlank(
                    allBlankOrNullStrings, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionAllBlankOrNull.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsEmpty_Map_Success() {
    assertThatCode(() -> ArgumentValidations.isEmpty(new HashMap<>(), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsEmpty_Map_Error() {
    RequiredArgumentException requiredArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isEmpty((Map<?, ?>) null, Validations.ANY_VALIDATION),
            RequiredArgumentException.class);

    assertThat(requiredArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    Map<String, String> map = new HashMap<>();
    map.put("Key", "Value");

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isEmpty(map, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsEmpty_Collection_Success() {

    assertThatCode(() -> ArgumentValidations.isEmpty(new ArrayList<>(), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsEmpty_Collection_Error() {
    RequiredArgumentException requiredArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isEmpty((Collection<?>) null, Validations.ANY_VALIDATION),
            RequiredArgumentException.class);

    assertThat(requiredArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    Collection<String> collection = new ArrayList<>();
    collection.add("Any string");

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isEmpty(collection, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsNotEmpty_Map_Success() {
    Map<String, String> map = new HashMap<>();
    map.put("Key", "Value");

    assertThatCode(() -> ArgumentValidations.isNotEmpty(map, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEmpty_Map_Error() {
    RequiredArgumentException requiredArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEmpty((Map<?, ?>) null, Validations.ANY_VALIDATION),
            RequiredArgumentException.class);

    assertThat(requiredArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    HashMap<Object, Object> value = new HashMap<>();
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEmpty(value, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsNotEmpty_Collection_Success() {
    Collection<String> collection = new ArrayList<>();
    collection.add("Any string");

    assertThatCode(() -> ArgumentValidations.isNotEmpty(collection, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEmpty_Collection_Error() {
    RequiredArgumentException requiredArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEmpty((Collection<?>) null, Validations.ANY_VALIDATION),
            RequiredArgumentException.class);

    assertThat(requiredArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);

    ArrayList<Object> value = new ArrayList<>();
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEmpty(value, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsEqualTo_Success() {
    String string1 = "Any string";
    String string2 = "Any string";

    assertThatCode(
            () -> ArgumentValidations.isEqualTo(string1, string2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsEqualTo_Error() {
    String string1 = "Any string";
    String string2 = "Another string";

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isEqualTo(string1, string2, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new String[] {string1, string2});
  }

  @Test
  void testIsNotEqualTo_Success() {
    String string1 = "Any string";
    String string2 = "Another string";

    assertThatCode(
            () -> ArgumentValidations.isNotEqualTo(string1, string2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEqualTo_Error() {
    String string1 = "Any string";
    String string2 = "Any string";

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEqualTo(string1, string2, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new String[] {string1, string2});
  }

  @Test
  void testIsNotEqualToZero_Byte_Success() {
    assertThatCode(() -> ArgumentValidations.isNotEqualToZero((byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isNotEqualToZero((byte) -1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEqualToZero_Byte_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEqualToZero((byte) 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {(byte) 0});
  }

  @Test
  void testIsNotEqualToZero_Short_Success() {
    assertThatCode(
            () -> ArgumentValidations.isNotEqualToZero((short) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isNotEqualToZero((short) -1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEqualToZero_Short_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEqualToZero((short) 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {(short) 0});
  }

  @Test
  void testIsNotEqualToZero_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isNotEqualToZero(1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isNotEqualToZero(-1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEqualToZero_Int_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEqualToZero(0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {0});
  }

  @Test
  void testIsNotEqualToZero_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isNotEqualToZero(1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isNotEqualToZero(-1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEqualToZero_Long_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEqualToZero(0L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {0L});
  }

  @Test
  void testIsNotEqualToZero_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isNotEqualToZero(1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isNotEqualToZero(-1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEqualToZero_Float_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEqualToZero(0.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {0.0f});
  }

  @Test
  void testIsNotEqualToZero_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isNotEqualToZero(1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isNotEqualToZero(-1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEqualToZero_Double_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEqualToZero(0.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {0.0});
  }

  @Test
  void testIsNotEqualToZero_BigDecimal_Success() {
    assertThatCode(
            () -> ArgumentValidations.isNotEqualToZero(BigDecimal.ONE, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isNotEqualToZero(
                    BigDecimal.valueOf(-1), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotEqualToZero_BigDecimal_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isNotEqualToZero(BigDecimal.ZERO, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {BigDecimal.ZERO});
  }

  @Test
  void testIsLessThan_Byte_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThan((byte) 1, (byte) 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Byte_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan((byte) 1, (byte) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {(byte) 1, (byte) 1});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan((byte) 2, (byte) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams())
        .isEqualTo(new Object[] {(byte) 2, (byte) 1});
  }

  @Test
  void testIsLessThan_Short_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThan((short) 1, (short) 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Short_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan((short) 1, (short) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {(short) 1, (short) 1});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan((short) 2, (short) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams())
        .isEqualTo(new Object[] {(short) 2, (short) 1});
  }

  @Test
  void testIsLessThan_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThan(1, 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Int_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(1, 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {1, 1});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(2, 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {2, 1});
  }

  @Test
  void testIsLessThan_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThan(1L, 2L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Long_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(1L, 1L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {1L, 1L});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(2L, 1L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {2L, 1L});
  }

  @Test
  void testIsLessThan_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThan(1.0f, 2.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Float_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(1.0f, 1.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {1.0f, 1.0f});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(2.0f, 1.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams())
        .isEqualTo(new Object[] {2.0f, 1.0f});
  }

  @Test
  void testIsLessThan_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThan(1.0, 2.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Double_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(1.0, 1.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {1.0, 1.0});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(2.0, 1.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {2.0, 1.0});
  }

  @Test
  void testIsLessThan_Comparable_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isLessThan(
                    BigDecimal.ONE, BigDecimal.valueOf(2), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Comparable_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isLessThan(
                    BigDecimal.ONE, BigDecimal.ONE, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {BigDecimal.ONE, BigDecimal.ONE});

    BigDecimal value = BigDecimal.valueOf(2);
    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThan(value, BigDecimal.ONE, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams())
        .isEqualTo(new Object[] {value, BigDecimal.ONE});
  }

  @Test
  void testIsLessThanZero_Byte_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero((byte) -1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Byte_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero((byte) 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {(byte) 0});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero((byte) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {(byte) 1});
  }

  @Test
  void testIsLessThanZero_Short_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero((short) -1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Short_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero((short) 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {(short) 0});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero((short) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {(short) 1});
  }

  @Test
  void testIsLessThanZero_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero(-1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Int_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {1});
  }

  @Test
  void testIsLessThanZero_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero(-1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Long_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(0L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0L});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(1L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {1L});
  }

  @Test
  void testIsLessThanZero_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero(-1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Float_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(0.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0.0f});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(1.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {1.0f});
  }

  @Test
  void testIsLessThanZero_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero(-1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Double_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(0.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0.0});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(1.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams()).isEqualTo(new Object[] {1.0});
  }

  @Test
  void testIsLessThanZero_BigDecimal_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isLessThanZero(
                    BigDecimal.valueOf(-1), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_BigDecimal_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(BigDecimal.ZERO, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {BigDecimal.ZERO});

    InvalidArgumentException invalidArgumentExceptionGreaterThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanZero(BigDecimal.ONE, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreaterThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreaterThan.getParams())
        .isEqualTo(new Object[] {BigDecimal.ONE});
  }

  @Test
  void testIsLessThanOrEqualTo_Byte_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    (byte) 1, (byte) 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    (byte) 1, (byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualTo_Byte_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    (byte) 1, (byte) 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {(byte) 1, (byte) 0});
  }

  @Test
  void testIsLessThanOrEqualTo_Short_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    (short) 1, (short) 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    (short) 1, (short) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualTo_Short_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    (short) 1, (short) 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {(short) 1, (short) 0});
  }

  @Test
  void testIsLessThanOrEqualTo_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanOrEqualTo(1, 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isLessThanOrEqualTo(1, 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualTo_Int_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualTo(1, 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {1, 0});
  }

  @Test
  void testIsLessThanOrEqualTo_Long_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualTo(1L, 2L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualTo(1L, 1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualTo_Long_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualTo(1L, 0L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {1L, 0L});
  }

  @Test
  void testIsLessThanOrEqualTo_Float_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualTo(1.0f, 2.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualTo(1.0f, 1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualTo_Float_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualTo(1.0f, 0.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {1.0f, 0.0f});
  }

  @Test
  void testIsLessThanOrEqualTo_Double_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualTo(1.0, 2.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualTo(1.0, 1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualTo_Double_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualTo(1.0, 0.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {1.0, 0.0});
  }

  @Test
  void testIsLessThanOrEqualTo_Comparable_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    BigDecimal.ONE, BigDecimal.valueOf(2), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    BigDecimal.ONE, BigDecimal.ONE, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualTo_Comparable_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    BigDecimal.ONE, BigDecimal.ZERO, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams())
        .isEqualTo(new Object[] {BigDecimal.ONE, BigDecimal.ZERO});
  }

  @Test
  void testIsLessThanOrEqualToZero_Byte_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero((byte) -1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualToZero((byte) 0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualToZero_Byte_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualToZero((byte) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {(byte) 1});
  }

  @Test
  void testIsLessThanOrEqualToZero_Short_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero((short) -1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero((short) 0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualToZero_Short_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero((short) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {(short) 1});
  }

  @Test
  void testIsLessThanOrEqualToZero_Int_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualToZero(-1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isLessThanOrEqualToZero(0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualToZero_Int_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualToZero(1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {1});
  }

  @Test
  void testIsLessThanOrEqualToZero_Long_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualToZero(-1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualToZero(0L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualToZero_Long_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualToZero(1L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {1L});
  }

  @Test
  void testIsLessThanOrEqualToZero_Float_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualToZero(-1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualToZero(0.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualToZero_Float_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualToZero(1.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {1.0f});
  }

  @Test
  void testIsLessThanOrEqualToZero_Double_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualToZero(-1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isLessThanOrEqualToZero(0.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualToZero_Double_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isLessThanOrEqualToZero(1.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {1.0});
  }

  @Test
  void testIsLessThanOrEqualToZero_BigDecimal_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero(
                    BigDecimal.valueOf(-1), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero(
                    BigDecimal.ZERO, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanOrEqualToZero_BigDecimal_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero(
                    BigDecimal.ONE, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {BigDecimal.ONE});
  }

  @Test
  void testIsGreaterThan_Byte_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThan((byte) 2, (byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Byte_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan((byte) 1, (byte) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {(byte) 1, (byte) 1});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan((byte) 1, (byte) 2, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams())
        .isEqualTo(new Object[] {(byte) 1, (byte) 2});
  }

  @Test
  void testIsGreaterThan_Short_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThan((short) 2, (short) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Short_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThan((short) 1, (short) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {(short) 1, (short) 1});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThan((short) 1, (short) 2, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams())
        .isEqualTo(new Object[] {(short) 1, (short) 2});
  }

  @Test
  void testIsGreaterThan_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThan(2, 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Int_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan(1, 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {1, 1});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan(1, 2, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {1, 2});
  }

  @Test
  void testIsGreaterThan_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThan(2L, 1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Long_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan(1L, 1L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {1L, 1L});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan(1L, 2L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {1L, 2L});
  }

  @Test
  void testIsGreaterThan_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThan(2.0f, 1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Float_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan(1.0f, 1.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {1.0f, 1.0f});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan(1.0f, 2.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {1.0f, 2.0f});
  }

  @Test
  void testIsGreaterThan_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThan(2.0, 1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Double_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan(1.0, 1.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {1.0, 1.0});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThan(1.0, 2.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {1.0, 2.0});
  }

  @Test
  void testIsGreaterThan_Comparable_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThan(
                    BigDecimal.valueOf(2), BigDecimal.ONE, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Comparable_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThan(
                    BigDecimal.ONE, BigDecimal.ONE, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {BigDecimal.ONE, BigDecimal.ONE});

    BigDecimal other = BigDecimal.valueOf(2);
    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThan(
                    BigDecimal.ONE, other, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams())
        .isEqualTo(new Object[] {BigDecimal.ONE, other});
  }

  @Test
  void testIsGreaterThanZero_Byte_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanZero((byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Byte_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero((byte) 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {(byte) 0});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero((byte) -1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {(byte) -1});
  }

  @Test
  void testIsGreaterThanZero_Short_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanZero((short) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Short_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero((short) 0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {(short) 0});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero((short) -1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {(short) -1});
  }

  @Test
  void testIsGreaterThanZero_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThanZero(1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Int_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(-1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {-1});
  }

  @Test
  void testIsGreaterThanZero_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThanZero(1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Long_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(0L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0L});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(-1L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {-1L});
  }

  @Test
  void testIsGreaterThanZero_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThanZero(1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Float_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(0.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0.0f});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(-1.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {-1.0f});
  }

  @Test
  void testIsGreaterThanZero_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThanZero(1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Double_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(0.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0.0});

    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(-1.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {-1.0});
  }

  @Test
  void testIsGreaterThanZero_BigDecimal_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanZero(BigDecimal.ONE, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_BigDecimal_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThanZero(BigDecimal.ZERO, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {BigDecimal.ZERO});

    BigDecimal value = BigDecimal.valueOf(-1);
    InvalidArgumentException invalidArgumentExceptionLessThan =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanZero(value, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLessThan.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLessThan.getParams()).isEqualTo(new Object[] {value});
  }

  @Test
  void testIsGreaterThanOrEqualTo_Byte_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    (byte) 2, (byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    (byte) 1, (byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualTo_Byte_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    (byte) 0, (byte) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {(byte) 0, (byte) 1});
  }

  @Test
  void testIsGreaterThanOrEqualTo_Short_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    (short) 2, (short) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    (short) 1, (short) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualTo_Short_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    (short) 0, (short) 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {(short) 0, (short) 1});
  }

  @Test
  void testIsGreaterThanOrEqualTo_Int_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(2, 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(1, 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualTo_Int_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(0, 1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0, 1});
  }

  @Test
  void testIsGreaterThanOrEqualTo_Long_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(2L, 1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(1L, 1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualTo_Long_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(0L, 1L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0L, 1L});
  }

  @Test
  void testIsGreaterThanOrEqualTo_Float_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(2.0f, 1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(1.0f, 1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualTo_Float_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(0.0f, 1.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0.0f, 1.0f});
  }

  @Test
  void testIsGreaterThanOrEqualTo_Double_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(2.0, 1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(1, 1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualTo_Double_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(0.0, 1.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {0.0, 1.0});
  }

  @Test
  void testIsGreaterThanOrEqualTo_Comparable_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    BigDecimal.valueOf(2), BigDecimal.ONE, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    BigDecimal.ONE, BigDecimal.ONE, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualTo_Comparable_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    BigDecimal.ZERO, BigDecimal.ONE, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams())
        .isEqualTo(new Object[] {BigDecimal.ZERO, BigDecimal.ONE});
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Byte_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    (byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    (byte) 0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Byte_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    (byte) -1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {(byte) -1});
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Short_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    (short) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    (short) 0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Short_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    (short) -1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {(short) -1});
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Int_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Int_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(-1, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {-1});
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Long_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(0L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Long_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(-1L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {-1L});
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Float_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(0.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Float_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(-1.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {-1.0f});
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Double_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(0.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualToZero_Double_Error() {
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(-1.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {-1.0});
  }

  @Test
  void testIsGreaterThanOrEqualToZero_BigDecimal_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    BigDecimal.ONE, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    BigDecimal.ZERO, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanOrEqualToZero_BigDecimal_Error() {
    BigDecimal value = BigDecimal.valueOf(-1);
    InvalidArgumentException invalidArgumentExceptionEquals =
        catchThrowableOfType(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(value, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionEquals.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionEquals.getParams()).isEqualTo(new Object[] {value});
  }

  @Test
  void testIsBetween_Byte_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    (byte) 1, (byte) 1, (byte) 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    (byte) 2, (byte) 1, (byte) 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    (byte) 3, (byte) 1, (byte) 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBetween_Byte_Error() {
    InvalidArgumentException invalidArgumentExceptionLess =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isBetween(
                    (byte) 0, (byte) 1, (byte) 3, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLess.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLess.getParams())
        .isEqualTo(new Object[] {(byte) 0, (byte) 1, (byte) 3});

    InvalidArgumentException invalidArgumentExceptionGreater =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isBetween(
                    (byte) 4, (byte) 1, (byte) 3, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreater.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreater.getParams())
        .isEqualTo(new Object[] {(byte) 4, (byte) 1, (byte) 3});
  }

  @Test
  void testIsBetween_Short_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    (short) 1, (short) 1, (short) 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    (short) 2, (short) 1, (short) 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    (short) 3, (short) 1, (short) 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBetween_Short_Error() {
    InvalidArgumentException invalidArgumentExceptionLess =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isBetween(
                    (short) 0, (short) 1, (short) 3, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLess.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLess.getParams())
        .isEqualTo(new Object[] {(short) 0, (short) 1, (short) 3});

    InvalidArgumentException invalidArgumentExceptionGreater =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isBetween(
                    (short) 4, (short) 1, (short) 3, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreater.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreater.getParams())
        .isEqualTo(new Object[] {(short) 4, (short) 1, (short) 3});
  }

  @Test
  void testIsBetween_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isBetween(1, 1, 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isBetween(2, 1, 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isBetween(3, 1, 3, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBetween_Int_Error() {
    InvalidArgumentException invalidArgumentExceptionLess =
        catchThrowableOfType(
            () -> ArgumentValidations.isBetween(0, 1, 3, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLess.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLess.getParams()).isEqualTo(new Object[] {0, 1, 3});

    InvalidArgumentException invalidArgumentExceptionGreater =
        catchThrowableOfType(
            () -> ArgumentValidations.isBetween(4, 1, 3, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreater.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreater.getParams()).isEqualTo(new Object[] {4, 1, 3});
  }

  @Test
  void testIsBetween_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isBetween(1L, 1L, 3L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isBetween(2L, 1L, 3L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isBetween(3L, 1L, 3L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBetween_Long_Error() {
    InvalidArgumentException invalidArgumentExceptionLess =
        catchThrowableOfType(
            () -> ArgumentValidations.isBetween(0L, 1L, 3L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLess.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLess.getParams()).isEqualTo(new Object[] {0L, 1L, 3L});

    InvalidArgumentException invalidArgumentExceptionGreater =
        catchThrowableOfType(
            () -> ArgumentValidations.isBetween(4L, 1L, 3L, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreater.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreater.getParams()).isEqualTo(new Object[] {4L, 1L, 3L});
  }

  @Test
  void testIsBetween_Float_Success() {
    assertThatCode(
            () -> ArgumentValidations.isBetween(1.0f, 1.0f, 3.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isBetween(2.0f, 1.0f, 3.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () -> ArgumentValidations.isBetween(3.0f, 1.0f, 3.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBetween_Float_Error() {
    InvalidArgumentException invalidArgumentExceptionLess =
        catchThrowableOfType(
            () -> ArgumentValidations.isBetween(0.0f, 1.0f, 3.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLess.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLess.getParams()).isEqualTo(new Object[] {0.0f, 1.0f, 3.0f});

    InvalidArgumentException invalidArgumentExceptionGreater =
        catchThrowableOfType(
            () -> ArgumentValidations.isBetween(4.0f, 1.0f, 3.0f, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreater.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreater.getParams())
        .isEqualTo(new Object[] {4.0f, 1.0f, 3.0f});
  }

  @Test
  void testIsBetween_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isBetween(1.0, 1.0, 3.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isBetween(2.0, 1.0, 3.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(() -> ArgumentValidations.isBetween(3.0, 1.0, 3.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBetween_Double_Error() {
    InvalidArgumentException invalidArgumentExceptionLess =
        catchThrowableOfType(
            () -> ArgumentValidations.isBetween(0.0, 1.0, 3.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLess.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLess.getParams()).isEqualTo(new Object[] {0.0, 1.0, 3.0});

    InvalidArgumentException invalidArgumentExceptionGreater =
        catchThrowableOfType(
            () -> ArgumentValidations.isBetween(4.0, 1.0, 3.0, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreater.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreater.getParams()).isEqualTo(new Object[] {4.0, 1.0, 3.0});
  }

  @Test
  void testIsBetween_BigDecimal_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    BigDecimal.ONE,
                    BigDecimal.ONE,
                    BigDecimal.valueOf(3),
                    Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    BigDecimal.valueOf(2),
                    BigDecimal.ONE,
                    BigDecimal.valueOf(3),
                    Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();

    assertThatCode(
            () ->
                ArgumentValidations.isBetween(
                    BigDecimal.valueOf(3),
                    BigDecimal.ONE,
                    BigDecimal.valueOf(3),
                    Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBetween_BigDecimal_Error() {
    BigDecimal endValue = BigDecimal.valueOf(3);
    InvalidArgumentException invalidArgumentExceptionLess =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isBetween(
                    BigDecimal.ZERO, BigDecimal.ONE, endValue, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionLess.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionLess.getParams())
        .isEqualTo(new Object[] {BigDecimal.ZERO, BigDecimal.ONE, endValue});

    BigDecimal value = BigDecimal.valueOf(4);
    InvalidArgumentException invalidArgumentExceptionGreater =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isBetween(
                    value, BigDecimal.ONE, endValue, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentExceptionGreater.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentExceptionGreater.getParams())
        .isEqualTo(new Object[] {value, BigDecimal.ONE, endValue});
  }

  @Test
  void testIsTrue_Success() {
    assertThatCode(() -> ArgumentValidations.isTrue(true, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsTrue_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isTrue(false, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsFalse_Success() {
    assertThatCode(() -> ArgumentValidations.isFalse(false, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsFalse_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.isFalse(true, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
  }

  @Test
  void testMatchesPattern_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.matchesPattern(
                    "123-45", "^\\d{3}-\\d{2}$", Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testMatchesPattern_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.matchesPattern(
                    "12-345", "^\\d{3}-\\d{2}$", Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {"12-345"});
  }

  @Test
  void testContains_Success() {
    Collection<String> collection = new ArrayList<>();
    collection.add("Any string");

    assertThatCode(
            () ->
                ArgumentValidations.contains("Any string", collection, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testContains_Error() {
    Collection<String> collection = new ArrayList<>();
    collection.add("Any string");

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.contains(
                    "Other string", collection, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {"Other string"});
  }

  @Test
  void testDoesNotContain_Success() {
    Collection<String> collection = new ArrayList<>();
    collection.add("Any string");

    assertThatCode(
            () ->
                ArgumentValidations.doesNotContain(
                    "Other string", collection, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testDoesNotContain_Error() {
    Collection<String> collection = new ArrayList<>();
    collection.add("Any string");

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.doesNotContain(
                    "Any string", collection, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {"Any string"});
  }

  @Test
  void testHasSize_CharSequence_Success() {
    assertThatCode(() -> ArgumentValidations.hasSize("Any string", 10, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testHasSize_CharSequence_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.hasSize("Any string", 5, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {"Any string", 5});
  }

  @Test
  void testHasSize_Map_Success() {
    Map<String, String> map = new HashMap<>();
    map.put("Key", "Value");

    assertThatCode(() -> ArgumentValidations.hasSize(map, 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testHasSize_Map_Error() {
    Map<String, String> map = new HashMap<>();
    map.put("Key", "Value");

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.hasSize(map, 2, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {2});
  }

  @Test
  void testHasSize_Collection_Success() {
    Set<String> set = new TreeSet<>();
    set.add("Any string");

    assertThatCode(() -> ArgumentValidations.hasSize(set, 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testHasSize_Collection_Error() {
    Set<String> set = new TreeSet<>();
    set.add("Any string");

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.hasSize(set, 2, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {2});
  }

  @Test
  void testHasSizeBetween_CharSequence_Success() {
    assertThatCode(
            () ->
                ArgumentValidations.hasSizeBetween("Any string", 5, 15, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testHasSizeBetween_CharSequence_Error() {
    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.hasSizeBetween("Any string", 1, 5, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {"Any string", 1, 5});
  }

  @Test
  void testHasSizeBetween_Map_Success() {
    Map<String, String> map = new HashMap<>();
    map.put("Key", "Value");

    assertThatCode(() -> ArgumentValidations.hasSizeBetween(map, 1, 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testHasSizeBetween_Map_Error() {
    Map<String, String> map = new HashMap<>();
    map.put("Key", "Value");

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.hasSizeBetween(map, 2, 3, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {2, 3});
  }

  @Test
  void testHasSizeBetween_Collection_Success() {
    Set<String> set = new TreeSet<>();
    set.add("Any string");

    assertThatCode(() -> ArgumentValidations.hasSizeBetween(set, 1, 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testHasSizeBetween_Collection_Error() {
    Set<String> set = new TreeSet<>();
    set.add("Any string");

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () -> ArgumentValidations.hasSizeBetween(set, 2, 3, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams()).isEqualTo(new Object[] {2, 3});
  }

  @Test
  void testIsInstanceOf_Success() {
    String anyString = "Any string";

    assertThatCode(
            () ->
                ArgumentValidations.isInstanceOf(
                    anyString, CharSequence.class, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsInstanceOf_Error() {
    String anyString = "Any string";

    InvalidArgumentException invalidArgumentException =
        catchThrowableOfType(
            () ->
                ArgumentValidations.isInstanceOf(
                    anyString, Integer.class, Validations.ANY_VALIDATION),
            InvalidArgumentException.class);

    assertThat(invalidArgumentException.getValidationMessage())
        .isEqualTo(Validations.ANY_VALIDATION);
    assertThat(invalidArgumentException.getParams())
        .isEqualTo(new Object[] {anyString, Integer.class});
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
