/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.api.attribute;

import io.github.mmm.ui.api.widget.UiWidget;

/**
 * {@link UiWidget} with {@link #getValidationFailure() validation failure}.
 *
 * @since 1.0.0
 */
public abstract interface AttributeWriteValidationFailure extends AttributeReadValid {

  /** Custom style to mark entire panels as invalid that have no {@code invalid} pseudo-class. */
  String STYLE_INVALID = "invalid";

  @Override
  default boolean isValid() {

    return (getValidationFailure() == null);
  }

  /**
   * @return the validation error text of this widget itself (not recursive) or {@code null} for no error.
   */
  String getValidationFailure();

  /**
   * @param validationFailure is the validation failure text. The empty string or {@code null} will clear the error and
   *        mark the field as valid. Otherwise the field will be invalid.
   */
  default void setValidationFailure(String validationFailure) {

    setValidationFailure(validationFailure, false);
  }

  /**
   * @param validationFailure is the validation failure text. The empty string or {@code null} will clear the error and
   *        mark the field as valid. Otherwise the field will be invalid.
   * @param valueException - {@code true} if invoked from {@link AttributeReadValue#getValue()} due to an exception,
   *        {@code false} otherwise.
   */
  void setValidationFailure(String validationFailure, boolean valueException);

}
