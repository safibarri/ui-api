/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.api.event.action;

/**
 * {@link UiAction} for <em>confirm</em>. It will acknowledge the current step or data.
 *
 * @see UiActionApprove
 * @see UiActionSubmit
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface UiActionConfirm extends UiAction {

  /** @see #getId() */
  String ACTION_ID = "Confirm";

  @Override
  default String getId() {

    return ACTION_ID;
  }

}
