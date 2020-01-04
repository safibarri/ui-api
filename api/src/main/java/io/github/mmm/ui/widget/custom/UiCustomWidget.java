/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.widget.custom;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.datatype.UiFlagMode;
import io.github.mmm.ui.datatype.UiStyles;
import io.github.mmm.ui.spi.widget.AbstractUiWidget;
import io.github.mmm.ui.widget.UiWidget;
import io.github.mmm.ui.widget.composite.UiComposite;

/**
 * This is the abstract base class for <em>custom widgets</em>. A custom widget is a {@link UiWidget} implemented via
 * {@link #getDelegate() delegation} to another widget. It is therefore toolkit independent and can be implemented as a
 * regular class. This makes the programming model of this UI-Toolkit easy to use. <br>
 * Typical use-cases for custom widgets are {@link io.github.mmm.ui.widget.input.UiInput} fields for custom datatypes
 * and {@link UiComposite}s for editors of particular business objects.
 *
 * @param <W> type of the {@link #getDelegate() delegate}.
 *
 * @since 1.0.0
 */
public abstract class UiCustomWidget<W extends UiWidget> extends AbstractUiWidget {

  /** @see #getDelegate() */
  private final W delegate;

  /** @see #initialize() */
  private boolean initialized;

  /**
   * The constructor.
   *
   * @param context is the {@link #getContext() context}.
   * @param delegate is the {@link #getDelegate() delegate}.
   */
  public UiCustomWidget(UiContext context, W delegate) {

    super(context);
    this.delegate = delegate;
  }

  /**
   * @return the adapted {@link UiWidget} wrapped by this custom widget.
   */
  public final W getDelegate() {

    return this.delegate;
  }

  /**
   * This method gets the {@link #getDelegate() delegate} of the given widget.
   *
   * @param <T> type of the {@link #getDelegate() delegate} of the given widgets.
   *
   * @param customWidget is the widget for which the {@link #getDelegate() delegate} is requested.
   * @return the requested {@link #getDelegate() delegate}.
   */
  protected static final <T extends UiWidget> T getDelegate(UiCustomWidget<T> customWidget) {

    return customWidget.getDelegate();
  }

  // --- delegation methods ---

  @Override
  public final UiComposite<?> getParent() {

    return this.delegate.getParent();
  }

  @Override
  protected void setParent(UiComposite<?> parent) {

    setParent(this.delegate, parent);
  }

  @Override
  public final void dispose() {

    this.delegate.dispose();
  }

  @Override
  public final boolean isDisposed() {

    return this.delegate.isDisposed();
  }

  @Override
  public boolean isVisible(UiFlagMode flagMode) {

    return this.delegate.isVisible(flagMode);
  }

  @Override
  public void setVisible(boolean visible, UiFlagMode flagMode) {

    this.delegate.setVisible(visible, flagMode);
  }

  @Override
  public boolean isEnabled(UiFlagMode flagMode) {

    return this.delegate.isEnabled(flagMode);
  }

  @Override
  public void setEnabled(boolean enabled, UiFlagMode flagMode) {

    this.delegate.setEnabled(enabled, flagMode);
  }

  @Override
  public UiStyles getStyles() {

    return this.delegate.getStyles();
  }

  @Override
  public final String getId() {

    return this.delegate.getId();
  }

  @Override
  public final void setId(String id) {

    this.delegate.setId(id);
  }

  /**
   * This method initializes this widget. It is automatically called from {@link #setParent(UiComposite)} so
   * initialization is performed before the widget is actually attached to the screen for the first time. The first call
   * of this method delegates to {@link #doInitialize()}. Further calls of this method will have no effect. <br>
   * <b>ATTENTION:</b><br>
   * You should not call this method directly unless you are absolutely aware of what you are doing.
   */
  protected final void initialize() {

    if (!this.initialized) {
      doInitialize();
      this.initialized = true;
    }
  }

  /**
   * This method is called from {@link #initialize()} but only if called for the first time. You may override this
   * method to add additional initialization logic. Then do not forget the <code>super</code> call. <br>
   * <b>ATTENTION:</b><br>
   * Never call this method directly.
   */
  protected void doInitialize() {

    // nothing by default...
  }

}