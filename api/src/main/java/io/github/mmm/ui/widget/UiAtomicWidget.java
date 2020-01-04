/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.widget;

import io.github.mmm.ui.widget.menu.UiAbstractMenuItem;
import io.github.mmm.ui.widget.window.UiWindow;

/**
 * Interface for a <em>regular</em> {@link UiWidget}. Here regular means that it represents a generic widget that can be
 * placed {@link io.github.mmm.ui.widget.panel.UiDynamicPanel almost anywhere}. Widgets that are not regular are e.g.
 * {@link UiWindow} or {@link UiAbstractMenuItem}.
 *
 * @since 1.0.0
 */
public abstract interface UiAtomicWidget extends UiRegularWidget {

  /**
   * @return the text of this widget. It is a brief description that explains this widget to the end-user. Will be the
   *         empty {@link String} if no tooltip is specified.
   */
  String getTooltip();

  /**
   * @param tooltip is the new {@link #getTooltip() tooltip}.
   */
  void setTooltip(String tooltip);

}