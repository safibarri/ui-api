/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.impl;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.spi.ServiceHelper;

/**
 * Provides the {@link UiContext}.
 *
 * @since 1.0.0
 */
public final class UiContextProvider {

  /** The {@link UiContext#get() singleton instance} of {@link UiContext}. */
  public static final UiContext CONTEXT = ServiceHelper.singleton(UiContext.class);

  private UiContextProvider() {

    super();
  }

}
