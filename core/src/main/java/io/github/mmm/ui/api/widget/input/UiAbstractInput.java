/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.api.widget.input;

import io.github.mmm.ui.api.widget.UiAtomicWidget;
import io.github.mmm.ui.api.widget.UiLabel;
import io.github.mmm.ui.api.widget.value.UiValidatableWidget;
import io.github.mmm.ui.api.widget.value.UiValuedWidget;

/**
 * {@link UiValuedWidget} for an {@link UiAtomicWidget atomic} input field.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract interface UiAbstractInput<V> extends UiValidatableWidget<V> {

  /**
   * @return the {@link UiLabel#getText() label} for the {@link UiInput#getNameWidget() name widget} or the title of
   *         the {@link io.github.mmm.ui.api.widget.panel.UiFormGroup}.
   */
  String getName();

  /**
   * @param name the new {@link #getName() name}. Prevents lazy initialization of the {@link UiInput#getNameWidget()
   *        name widget}.
   */
  void setName(String name);

}