/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.spi.widget;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.datatype.UiFlagMode;
import io.github.mmm.ui.datatype.UiPropagation;
import io.github.mmm.ui.event.UiDisableEvent;
import io.github.mmm.ui.event.UiEnableEvent;
import io.github.mmm.ui.event.UiEvent;
import io.github.mmm.ui.event.UiEventListener;
import io.github.mmm.ui.event.UiEventType;
import io.github.mmm.ui.event.UiHideEvent;
import io.github.mmm.ui.event.UiShowEvent;
import io.github.mmm.ui.widget.UiNativeWidget;
import io.github.mmm.ui.widget.composite.UiComposite;
import io.github.mmm.ui.widget.window.UiAbstractWindow;

/**
 * Abstract base implementation of {@link UiNativeWidget}.
 *
 * @since 1.0.0
 */
public abstract class AbstractUiNativeWidget extends AbstractUiWidget implements UiNativeWidget {

  private UiComposite<?> parent;

  private boolean handlersRegistered;

  private UiEventType programmaticEventType;

  private long visibleState;

  private long enabledState;

  /**
   * The constructor.
   *
   * @param context the {@link UiContext}.
   */
  public AbstractUiNativeWidget(UiContext context) {

    super(context);
    this.handlersRegistered = false;
    if (isInitiallyVisible()) {
      this.visibleState = 0;
    } else {
      this.visibleState = 1;
    }
    this.enabledState = 0;
  }

  /**
   * @return {@code true} if this widget is initially visible (locally native), {@code false} otherwise.
   */
  protected boolean isInitiallyVisible() {

    return !(this instanceof UiAbstractWindow);
  }

  @Override
  public UiComposite<?> getParent() {

    return this.parent;
  }

  @Override
  protected void setParent(UiComposite<?> parent) {

    requireNotDisposed();
    if (parent == this.parent) {
      return;
    }
    UiPropagation oldPropagation = UiPropagation.NONE;
    if (this.parent != null) {
      oldPropagation = this.parent.getPropagation();
    }
    this.parent = parent;
    oldPropagation.updateParent(this);
  }

  @Override
  public boolean isVisible(UiFlagMode flagMode) {

    if (flagMode == null) {
      if (this.visibleState != 0) {
        return false;
      }
      if (this.parent == null) {
        return false;
      } else {
        return this.parent.isVisible(null);
      }
    }
    return flagMode.isSet(this.visibleState);
  }

  @Override
  public void setVisible(boolean visible, UiFlagMode flagMode) {

    requireNotDisposed();
    doSetVisibleState(flagMode.set(this.visibleState, visible));
  }

  /**
   * @param visible {@code true} to show this widget (in case its parent is also visible), {@code false} to hide this
   *        widget.
   */
  protected abstract void setVisibleNative(boolean visible);

  /**
   * @param widget the {@link AbstractUiNativeWidget}.
   * @return the internal {@link #isVisible(UiFlagMode) visible state} of the given {@code widget}.
   */
  protected static final long doGetVisibleState(AbstractUiNativeWidget widget) {

    return widget.visibleState;
  }

  /**
   * @param state new value of {@link #doGetVisibleState(AbstractUiNativeWidget)}.
   */
  protected final void doSetVisibleState(long state) {

    if (state == this.visibleState) {
      return;
    }
    boolean oldNativeVisible = (this.visibleState == 0);
    this.visibleState = state;
    boolean nativeVisible = (this.visibleState == 0);
    if ((oldNativeVisible == nativeVisible)) {
      return;
    }
    setVisibleNative(nativeVisible);
    UiEvent event;
    if (nativeVisible) {
      event = new UiShowEvent(this);
    } else {
      event = new UiHideEvent(this);
    }
    fireEvent(event);
  }

  /**
   * @param widget the {@link AbstractUiNativeWidget}.
   * @param state new value of {@link #doGetVisibleState(AbstractUiNativeWidget)} of the given {@code widget}.
   */
  protected static final void doSetVisibleState(AbstractUiNativeWidget widget, long state) {

    widget.doSetVisibleState(state);
  }

  @Override
  public boolean isEnabled(UiFlagMode flagMode) {

    if (flagMode == null) {
      if (this.enabledState != 0) {
        return false;
      }
      if (this.parent == null) {
        return false;
      } else {
        return this.parent.isEnabled(null);
      }
    }
    return flagMode.isSet(this.enabledState);
  }

  @Override
  public void setEnabled(boolean enabled, UiFlagMode flagMode) {

    requireNotDisposed();
    doSetEnabledState(flagMode.set(this.enabledState, enabled));
  }

  /**
   * @param enabled the new {@link #isEnabled() enabled state}. Use {@code true} to enable and {@code false} to disable
   *        this widget.
   */
  protected abstract void setEnabledNative(boolean enabled);

  /**
   * @param widget the {@link AbstractUiNativeWidget}.
   * @return the internal {@link #isEnabled(UiFlagMode) enabled state} of the given {@code widget}.
   */
  protected static final long doGetEnabledState(AbstractUiNativeWidget widget) {

    return widget.enabledState;
  }

  /**
   * @param state new value of {@link #doGetEnabledState(AbstractUiNativeWidget)}.
   */
  protected final void doSetEnabledState(long state) {

    if (state == this.enabledState) {
      return;
    }
    boolean oldNativeEnabled = (this.enabledState == 0);
    this.enabledState = state;
    boolean nativeEnabled = (this.enabledState == 0);
    if ((oldNativeEnabled == nativeEnabled)) {
      return;
    }
    setEnabledNative(nativeEnabled);
    UiEvent event;
    if (nativeEnabled) {
      event = new UiEnableEvent(this);
    } else {
      event = new UiDisableEvent(this);
    }
    fireEvent(event);
  }

  /**
   * @param widget the {@link AbstractUiNativeWidget}.
   * @param state new value of {@link #doGetEnabledState(AbstractUiNativeWidget)} of the given {@code widget}.
   */
  protected static final void doSetEnabledState(AbstractUiNativeWidget widget, long state) {

    widget.doSetEnabledState(state);
  }

  @Override
  public void addListener(UiEventListener listener, boolean weak) {

    if (!this.handlersRegistered) {
      registerHandlers();
      this.handlersRegistered = true;
    }
    super.addListener(listener, weak);
  }

  /**
   * Registers the according handlers to the underlying native widget.
   */
  protected void registerHandlers() {

    if (this.handlersRegistered) {
      throw new IllegalStateException();
    }
  }

  /**
   * @return the {@link UiEventType} that was previously {@link #setProgrammaticEventType(UiEventType) set} as
   *         programmatic. ATTENTION: As a side-effect the value is reset to {@code null}.
   * @see io.github.mmm.ui.event.UiEvent#isProgrammatic()
   */
  protected UiEventType getProgrammaticEventType() {

    UiEventType result = this.programmaticEventType;
    this.programmaticEventType = null;
    return result;
  }

  /**
   * @param eventType the {@link UiEventType} to mark as {@link io.github.mmm.ui.event.UiEvent#isProgrammatic()
   *        programmatic}. If the next event is of this type, it will be set as
   *        {@link io.github.mmm.ui.event.UiEvent#isProgrammatic() programmatic}.
   */
  protected void setProgrammaticEventType(UiEventType eventType) {

    this.programmaticEventType = eventType;
  }

  @Override
  public final boolean isDisposed() {

    return (this.enabledState == -1L);
  }

  /**
   * Ensures that this widget is not {@link #isDisposed() disposed}.
   *
   * @throws IllegalStateException otherwise.
   */
  protected void requireNotDisposed() {

    if (isDisposed()) {
      throw new IllegalStateException(toString() + " is disposed");
    }
  }

  @Override
  public final void dispose() {

    if (isDisposed()) {
      return;
    }
    doDispose();
    this.enabledState = -1L;
    this.visibleState = -1L;
    this.parent = null;
  }

  /**
   * Called from {@link #dispose()}
   */
  protected void doDispose() {

  }

}
