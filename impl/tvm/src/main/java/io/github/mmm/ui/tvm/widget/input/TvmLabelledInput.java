/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.widget.attribute.UiWidgetWithLabel;

/**
 * {@link TvmInput} that also implements {@link UiWidgetWithLabel}.
 *
 * @param <V> type of {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class TvmLabelledInput<V> extends TvmHtmlInput<V> implements UiWidgetWithLabel {

  private final HTMLElement topWidget;

  private String label;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param type the {@link HTMLInputElement#getType() type} of the input.
   */
  public TvmLabelledInput(UiContext context, String type) {

    super(context, type);
    this.topWidget = Window.current().getDocument().createElement("label");
    this.topWidget.appendChild(this.widget);
    this.label = "";
  }

  @Override
  public HTMLElement getTopWidget() {

    return this.topWidget;
  }

  @Override
  public String getLabel() {

    return this.label;
  }

  @Override
  public void setLabel(String label) {

    if (label == null) {
      label = "";
    }
    this.label = label;
    setTextContent(label);
  }

}