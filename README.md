# validation

[![Maven Central](https://img.shields.io/maven-central/v/br.com.leverinfo/validation.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22br.com.leverinfo%22%20AND%20a:%22validation%22)

This library helps to handle with validations in Java.

## Requirements

- Java 1.8+

## How to Use

To start, add that Maven dependency:

```
<dependency>
  <groupId>br.com.leverinfo</groupId>
  <artifactId>validation</artifactId>
  <version>0.2.0</version>
</dependency>
```

...then implement the interface `ValidationMessage` by enum (it can be by a class as well):
 
```
public enum MyMessages implements ValidationMessage {

  UNEXPECTED_ERROR("0001", "An unexpected error was encountered"),
  NAME_REQUIRED("0002", "Name is required");
  // ...

  MyMessages(String code, String message) {
    this.code = code;
    this.message = message;
  }

  private final String code;
  private final String message;

  public String getCode() {
    return code;
  }

  public final String getMessage() {
    return message;
  }
}
```

then, in your method, you can validate arguments like this:

```
...
import br.com.leverinfo.validation.ArgumentValidations;
...

public class Foo {
    
  public String printName(String name) {
    ArgumentValidations.isNotBlank(name, MyMessages.NAME_REQUIRED);
  
    return System.out.println(name);
  }
}
```

* If argument value is null, then a `RequiredArgumentException` will be thrown.
* If argument value is empty, then a `InvalidArgumentException` will be thrown.

## Available Validations

Below are the available validations:

### ArgumentValidations

Recommended to handle with argument validations

* `isNotNull(Object)` - Throws `RequiredArgumentException` if value is null
* `isNotBlank(String)` - Throws `RequiredArgumentException` if value is null or `InvalidArgumentException` if value is blank
* `anyIsNotBlank(Iterable<String>)` - Throws `InvalidArgumentException` if all values are blank
* `onlyOneIsNotNull(Iterable<Object>)` - Throws `InvalidArgumentException` if more than one value is not null or if all values are null
* `onlyOneIsNotBlank(Iterable<CharSequence>)` - Throws `InvalidArgumentException` if more than one value is not blank or if all values are blank
* `isNotEmpty(Map | Collection)` - Throws `RequiredArgumentException` if value is null or `InvalidArgumentException` if value is empty
* `isGreaterThanZero(int | long | BigDecimal)` - Throws `RequiredArgumentException` if value is null or `InvalidArgumentException` if value is less than or equal to zero
* `isGreaterThan(int | long | BigDecimal)` - Throws `InvalidArgumentException` if value is less than or equal to other value
* `isGreaterThanOrEqualToZero(int | long | BigDecimal)` - Throws `InvalidArgumentException` if value is less than zero
* `isGreaterThanOrEqualTo(int | long | BigDecimal)` - Throws `InvalidArgumentException` if value is less than other value
* `isNotEqualToZero(int | long | BigDecimal)` - Throws `InvalidArgumentException` if value is equal to zero
* `isBetween(int | long | BigDecimal)` - Throws `InvalidArgumentException` if value is not between limits
* `isEqualTo(Object)` - Throws `InvalidArgumentException` if value is not equal to other value
* `isNotEqualTo(Object)` - Throws `InvalidArgumentException` if value is equal to other value
* `isTrue(boolean)` - Throws `InvalidArgumentException` if condition is not true
* `isFalse(boolean)` - Throws `InvalidArgumentException` if condition is true
* `matchesPattern(CharSequence)` - Throws `InvalidArgumentException` if value does not match with pattern
* `contains(Object)` - Throws `InvalidArgumentException` if collection does not contain value
* `hasSize(CharSequence | Collection)` - Throws `InvalidArgumentException` if value has not desired size
* `hasSizeBetween(CharSequence | Collection)` - Throws `InvalidArgumentException` if value has not desired size range

### ConditionValidations

Recommended to handle with condition validations

* `isTrue(boolean)` - Throws `NotAllowedException` if condition is not true
* `isFalse(boolean)` - Throws `NotAllowedException` if condition is true

## Custom Exceptions 

This library provides some exception classe to handle with some validations situations.

* `RequiredArgumentException` - Recommended to be thrown when some argument value is missing
* `InvalidArgumentException` - Recommended to be thrown when some argument value is invalid
* `NotAllowedException` - Recommended to be thrown when some operation is not allowed
* `NotFoundException` - Recommended to be thrown when some item is not found
* `DependencyNotFoundException` - Recommended to be thrown when some dependency is not found
* `DuplicatedException` - Recommended to be thrown when some item is duplicated

## Roadmap

* Implement custom bean validations
* Create unit tests
* Configure CI/CD pipeline

Your contribution is appreciated.