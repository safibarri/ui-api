/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.widget.input;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.widget.UiNativeWidget;

/**
 * {@link UiNumberInput} for an {@link Integer} {@link #getValue() value}.<br>
 * Example:
 *
 * <pre>
 * <input type="number" placeholder="placeholder">
 * </pre>
 *
 * @since 1.0.0
 */
public interface UiIntegerInput extends UiNumberInput<Integer>, UiNativeWidget {

  /**
   * @param context the {@link UiContext}.
   * @param name the {@link #getName() name} (label).
   * @return the new {@link UiIntegerInput}.
   */
  static UiIntegerInput of(UiContext context, String name) {

    UiIntegerInput widget = context.create(UiIntegerInput.class);
    widget.setName(name);
    return widget;
  }

}