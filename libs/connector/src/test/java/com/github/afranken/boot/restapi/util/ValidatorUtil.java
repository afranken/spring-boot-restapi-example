package com.github.afranken.boot.restapi.util;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Wraps validation API methods for easy use in tests.
 */
public class ValidatorUtil {
  private static Validator validator;

  static {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  /**
   * Validate the given entity and return number of constrain violations
   *
   * @return number of constrain violations. The number 0 means there are no violations.
   */
  public static <T> int countViolationsOn(T entity) {
    return validateInternal(entity).size();
  }

  /**
   * Validate given entity.
   *
   * @throws IllegalArgumentException in case given entity has validation errors.
   */
  public static <T> void validate(T entity) {
    Set<ConstraintViolation<T>> constraintViolations = validateInternal(entity);
    if (constraintViolations.size() != 0) {
      String errorMessage =
          constraintViolations.stream().map(ConstraintViolation::getMessage).collect(
              Collectors.joining());
      throw new IllegalArgumentException(
          "Error validating " + entity.getClass() + " with errors: " + errorMessage);
    }
  }

  private static <T> Set<ConstraintViolation<T>> validateInternal(T entity) {
    return validator.validate(entity);
  }
}
