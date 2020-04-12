/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.api.widget.input;

import io.github.mmm.ui.api.widget.UiAtomicWidget;
import io.github.mmm.ui.api.widget.UiLabel;
import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.value.UiValuedWidget;

/**
 * {@link UiValuedWidget} for an {@link UiAtomicWidget atomic} input field.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract interface UiInput<V> extends UiAbstractInput<V>, UiAtomicWidget {

  /**
   * @return {@code true} if the {@link #getNameWidget() name widget} has already been created, {@code false} otherwise.
   *         Helpful to avoid unintended lazy initialization.
   */
  boolean hasNameWidget();

  /**
   * @return the {@link UiLabel} of this input. May be lazily created on the first call of this method to avoid
   *         unnecessary overhead (e.g. if a {@link UiInput} is used for inline editing).
   */
  UiLabel getNameWidget();

  /**
   * @return {@code true} if the {@link #getContainerWidget() container widget} has already been created, {@code false}
   *         otherwise. Helpful to avoid unintended lazy initialization.
   */
  boolean hasContainerWidget();

  /**
   * @return the {@link UiRegularWidget} containing both the {@link #getNameWidget() name widget} and the actual input
   *         widget. Will be lazily created on the first call of this method.
   */
  UiRegularWidget getContainerWidget();

}