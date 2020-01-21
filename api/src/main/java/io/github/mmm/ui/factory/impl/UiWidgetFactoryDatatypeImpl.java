/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.factory.impl;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.factory.UiSingleWidgetFactoryDatatype;
import io.github.mmm.ui.factory.UiWidgetFactoryDatatype;
import io.github.mmm.ui.spi.factory.AbstractUiWidgetFactory;
import io.github.mmm.ui.widget.input.UiInput;

/**
 * Implementation of {@link UiWidgetFactoryDatatype}.
 *
 * @since 1.0.0
 */
public class UiWidgetFactoryDatatypeImpl extends AbstractUiWidgetFactory<UiSingleWidgetFactoryDatatype<?>>
    implements UiWidgetFactoryDatatype {

  /** The singleton instance. */
  public static final UiWidgetFactoryDatatypeImpl INSTANCE = new UiWidgetFactoryDatatypeImpl();

  /**
   * The constructor.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public UiWidgetFactoryDatatypeImpl() {

    super((Class) UiSingleWidgetFactoryDatatype.class);
  }

  @Override
  public <V> UiInput<V> create(Class<V> datatype, boolean required, UiContext context) {

    return createForType(datatype, required, context);
  }

}
