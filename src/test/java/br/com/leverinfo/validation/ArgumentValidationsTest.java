package br.com.leverinfo.validation;

import static br.com.leverinfo.test.ValidationAssertions.assertThatInvalidArgumentException;
import static br.com.leverinfo.test.ValidationAssertions.assertThatRequiredArgumentException;
import static org.assertj.core.api.Assertions.assertThatCode;

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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isNull(new Object(), Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsNotNull_Success() {
    assertThatCode(() -> ArgumentValidations.isNotNull(new Object(), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotNull_Error() {
    assertThatRequiredArgumentException()
        .isThrownBy(() -> ArgumentValidations.isNotNull(null, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsBlank_Success() {
    assertThatCode(() -> ArgumentValidations.isBlank("", Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsBlank_Error() {
    assertThatRequiredArgumentException()
        .isThrownBy(() -> ArgumentValidations.isBlank(null, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isBlank("Any string", Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsNotBlank_Success() {
    assertThatCode(() -> ArgumentValidations.isNotBlank("Any string", Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsNotBlank_Error() {
    assertThatRequiredArgumentException()
        .isThrownBy(() -> ArgumentValidations.isNotBlank(null, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isNotBlank("", Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.anyIsNotNull(objects, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.anyIsNotBlank(nullStrings, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    final List<CharSequence> blankStrings = new ArrayList<>();
    blankStrings.add("");
    blankStrings.add("");
    blankStrings.add("");

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.anyIsNotBlank(blankStrings, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.onlyOneIsNotNull(
                    moreThanOneNotNullObjects, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    final Set<Object> allNullObjects = new HashSet<>();
    allNullObjects.add(null);
    allNullObjects.add(null);
    allNullObjects.add(null);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.onlyOneIsNotNull(allNullObjects, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.onlyOneIsNotBlank(
                    moreThanOneNotBlankStrings, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    final Set<CharSequence> allBlankStrings = new HashSet<>();
    allBlankStrings.add("");
    allBlankStrings.add(null);
    allBlankStrings.add(null);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.onlyOneIsNotBlank(allBlankStrings, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsEmpty_Map_Success() {
    assertThatCode(() -> ArgumentValidations.isEmpty(new HashMap<>(), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsEmpty_Map_Error() {
    assertThatRequiredArgumentException()
        .isThrownBy(() -> ArgumentValidations.isEmpty((Map<?, ?>) null, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    Map<String, String> map = new HashMap<>();
    map.put("Key", "Value");

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isEmpty(map, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsEmpty_Collection_Success() {

    assertThatCode(() -> ArgumentValidations.isEmpty(new ArrayList<>(), Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsEmpty_Collection_Error() {
    assertThatRequiredArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isEmpty((Collection<?>) null, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    Collection<String> collection = new ArrayList<>();
    collection.add("Any string");

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isEmpty(collection, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatRequiredArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isNotEmpty((Map<?, ?>) null, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isNotEmpty(new HashMap<>(), Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatRequiredArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isNotEmpty((Collection<?>) null, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isNotEmpty(new ArrayList<>(), Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isEqualTo(string1, string2, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isNotEqualTo(string1, string2, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isNotEqualToZero((byte) 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isNotEqualToZero((short) 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isNotEqualToZero(0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isNotEqualToZero(0L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isNotEqualToZero(0.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isNotEqualToZero(0.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isNotEqualToZero(BigDecimal.ZERO, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThan_Byte_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThan((byte) 1, (byte) 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Byte_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThan((byte) 1, (byte) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThan((byte) 2, (byte) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThan_Short_Success() {
    assertThatCode(
            () -> ArgumentValidations.isLessThan((short) 1, (short) 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Short_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThan((short) 1, (short) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThan((short) 2, (short) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThan_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThan(1, 2, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Int_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThan(1, 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThan(2, 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThan_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThan(1L, 2L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Long_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThan(1L, 1L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThan(2L, 1L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThan_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThan(1.0f, 2.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Float_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThan(1.0f, 1.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThan(2.0f, 1.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThan_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThan(1.0, 2.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThan_Double_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThan(1.0, 1.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThan(2.0, 1.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isLessThan(
                    BigDecimal.ONE, BigDecimal.ONE, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isLessThan(
                    BigDecimal.valueOf(2), BigDecimal.ONE, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThanZero_Byte_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero((byte) -1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Byte_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero((byte) 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero((byte) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThanZero_Short_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero((short) -1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Short_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero((short) 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero((short) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThanZero_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero(-1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Int_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero(0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero(1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThanZero_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero(-1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Long_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero(0L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero(1L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThanZero_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero(-1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Float_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero(0.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero(1.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsLessThanZero_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isLessThanZero(-1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsLessThanZero_Double_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero(0.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanZero(1.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanZero(BigDecimal.ZERO, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanZero(BigDecimal.ONE, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    (byte) 1, (byte) 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    (short) 1, (short) 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isLessThanOrEqualTo(1, 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanOrEqualTo(1L, 0L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanOrEqualTo(1.0f, 0.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanOrEqualTo(1.0, 0.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isLessThanOrEqualTo(
                    BigDecimal.ONE, BigDecimal.ZERO, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanOrEqualToZero((byte) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero((short) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanOrEqualToZero(1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanOrEqualToZero(1L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanOrEqualToZero(1.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isLessThanOrEqualToZero(1.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isLessThanOrEqualToZero(
                    BigDecimal.ONE, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThan_Byte_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThan((byte) 2, (byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Byte_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThan((byte) 1, (byte) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThan((byte) 1, (byte) 2, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThan((short) 1, (short) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThan((short) 1, (short) 2, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThan_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThan(2, 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Int_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThan(1, 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThan(1, 2, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThan_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThan(2L, 1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Long_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThan(1L, 1L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThan(1L, 2L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThan_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThan(2.0f, 1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Float_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThan(1.0f, 1.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThan(1.0f, 2.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThan_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThan(2.0, 1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThan_Double_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThan(1.0, 1.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThan(1.0, 2.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThan(
                    BigDecimal.ONE, BigDecimal.ONE, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThan(
                    BigDecimal.ONE, BigDecimal.valueOf(2), Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThanZero_Byte_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanZero((byte) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Byte_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanZero((byte) 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanZero((byte) -1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThanZero_Short_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanZero((short) 1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Short_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanZero((short) 0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanZero((short) -1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThanZero_Int_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThanZero(1, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Int_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThanZero(0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThanZero(-1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThanZero_Long_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThanZero(1L, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Long_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThanZero(0L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThanZero(-1L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThanZero_Float_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThanZero(1.0f, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Float_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThanZero(0.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThanZero(-1.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThanZero_Double_Success() {
    assertThatCode(() -> ArgumentValidations.isGreaterThanZero(1.0, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_Double_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThanZero(0.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isGreaterThanZero(-1.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsGreaterThanZero_BigDecimal_Success() {
    assertThatCode(
            () -> ArgumentValidations.isGreaterThanZero(BigDecimal.ONE, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsGreaterThanZero_BigDecimal_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanZero(BigDecimal.ZERO, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanZero(
                    BigDecimal.valueOf(-1), Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    (byte) 0, (byte) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    (short) 0, (short) 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(0, 1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(0L, 1L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(0.0f, 1.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanOrEqualTo(0.0, 1.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanOrEqualTo(
                    BigDecimal.ZERO, BigDecimal.ONE, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    (byte) -1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    (byte) -1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(-1, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(-1L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(-1.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isGreaterThanOrEqualToZero(-1.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isGreaterThanOrEqualToZero(
                    BigDecimal.valueOf(-1), Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isBetween(
                    (byte) 0, (byte) 1, (byte) 3, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isBetween(
                    (byte) 4, (byte) 1, (byte) 3, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isBetween(
                    (short) 0, (short) 1, (short) 3, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isBetween(
                    (short) 4, (short) 1, (short) 3, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isBetween(0, 1, 3, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isBetween(4, 1, 3, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isBetween(0L, 1L, 3L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isBetween(4L, 1L, 3L, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isBetween(0.0f, 1.0f, 3.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () -> ArgumentValidations.isBetween(4.0f, 1.0f, 3.0f, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isBetween(0.0, 1.0, 3.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isBetween(4.0, 1.0, 3.0, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isBetween(
                    BigDecimal.ZERO,
                    BigDecimal.ONE,
                    BigDecimal.valueOf(3),
                    Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.isBetween(
                    BigDecimal.valueOf(4),
                    BigDecimal.ONE,
                    BigDecimal.valueOf(3),
                    Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsTrue_Success() {
    assertThatCode(() -> ArgumentValidations.isTrue(true, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsTrue_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isTrue(false, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testIsFalse_Success() {
    assertThatCode(() -> ArgumentValidations.isFalse(false, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testIsFalse_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.isFalse(true, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.matchesPattern(
                    "12-345", "^\\d{3}-\\d{2}$", Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.contains(
                    "Other string", collection, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.doesNotContain(
                    "Any string", collection, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
  }

  @Test
  void testHasSize_CharSequence_Success() {
    assertThatCode(() -> ArgumentValidations.hasSize("Any string", 10, Validations.ANY_VALIDATION))
        .doesNotThrowAnyException();
  }

  @Test
  void testHasSize_CharSequence_Error() {
    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.hasSize("Any string", 5, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.hasSize(map, 2, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.hasSize(set, 2, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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
    assertThatInvalidArgumentException()
        .isThrownBy(
            () ->
                ArgumentValidations.hasSizeBetween("Any string", 1, 5, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.hasSizeBetween(map, 2, 3, Validations.ANY_VALIDATION))
        .withValidationMessage(Validations.ANY_VALIDATION);
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

    assertThatInvalidArgumentException()
        .isThrownBy(() -> ArgumentValidations.hasSizeBetween(set, 2, 3, Validations.ANY_VALIDATION))
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
