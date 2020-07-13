/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.api.widget.data;

import io.github.mmm.ui.api.attribute.AttributeWriteMultiSelection;
import io.github.mmm.ui.api.attribute.AttributeWriteSelection;
import io.github.mmm.ui.api.attribute.AttributeWriteSelections;
import io.github.mmm.ui.api.widget.UiRegularWidget;

/**
 * Abstract interface for widgets showing complex data in columns such as {@link UiDataTable}.
 *
 * @param <R> type of the data for the rows displayed by this widget. Typically a {@link io.github.mmm.bean.Bean}.
 * @since 1.0.0
 */
public abstract interface UiAbstractDataWidget<R>
    extends UiRegularWidget, AttributeWriteSelection<R>, AttributeWriteSelections<R>, AttributeWriteMultiSelection {

  /** The {@link io.github.mmm.ui.api.datatype.UiStyles#add(String) style} for an data widget. */
  String STYLE = "ui-data";

  /**
   * @param rowTemplate the template instance of a row.
   */
  void setRowTemplate(R rowTemplate);

  /**
   * Interface to read and write the value of a property of the data model.
   *
   * @param <D> type of the data model.
   * @param <C> type of the property value (cell value).
   */
  interface ColumnAdapter<D, C> {

    /**
     * @return the {@link Class} reflecting the cell value (e.g. {@link String}, {@link Boolean}, {@link Integer},
     *         {@link java.math.BigDecimal}, etc.).
     */
    Class<C> getType();

    /**
     * Getter to read the property value from the data model.
     *
     * @param data the data object to read the value from.
     * @return the property value from the data object.
     */
    C get(D data);

    /**
     * Setter to write the property value back to the data model. Only needed for inline editing when {@link UiColumn}
     * is not {@link UiColumn#isReadOnly() read-only}.
     *
     * @param data
     * @param value
     */
    default void set(D data, C value) {

      throw new UnsupportedOperationException();
    }

  }

}
