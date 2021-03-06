/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.api.datatype;

/**
 * The scrolling behavior (visibility of a scrollbar).
 *
 * @since 1.0.0
 */
public enum UiScrollBarVisibility {

  /** The scrollbar is displayed only if the content is too large for the containing panel. */
  AUTO,

  /** The scrollbar is always visible. */
  ALWAYS,

  /** The scrollbar is always hidden. Overflow is not reachable for end-user. */
  HIDDEN

}
