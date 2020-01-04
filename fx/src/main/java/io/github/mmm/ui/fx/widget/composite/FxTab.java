/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.fx.widget.composite;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.fx.widget.FxWidgetStyleable;
import io.github.mmm.ui.fx.widget.panel.FxTabPanel;
import io.github.mmm.ui.widget.UiRegularWidget;
import io.github.mmm.ui.widget.composite.UiTab;
import javafx.scene.control.Tab;

/**
 * Implementation of {@link UiTab} using JavaFx {@link Tab}.
 *
 * @since 1.0.0
 */
public class FxTab extends FxWidgetStyleable<Tab> implements UiTab {

  private UiRegularWidget child;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public FxTab(UiContext context) {

    super(context, new Tab());
    this.nativeWidget.setUserData(this);
    this.nativeWidget.setClosable(false);
  }

  private FxTabPanel getTabPanel() {

    return (FxTabPanel) getParent();
  }

  @Override
  public void setChild(UiRegularWidget child) {

    this.nativeWidget.setContent(getNode(child));
    this.child = child;
  }

  @Override
  public UiRegularWidget getChild() {

    return this.child;
  }

  @Override
  public String getLabel() {

    return this.nativeWidget.getText();
  }

  @Override
  public void setLabel(String label) {

    this.nativeWidget.setText(label);
  }

  @Override
  public void setId(String id) {

    this.nativeWidget.setId(id);
  }

  @Override
  protected void setVisibleNative(boolean visible) {

    FxTabPanel tabPanel = getTabPanel();
    if (tabPanel != null) {
      tabPanel.setTabVisible(this, visible);
    }
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.nativeWidget.setDisable(!enabled);
  }

  @Override
  public char getAccessKey() {

    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setAccessKey(char accessKey) {

    // TODO Auto-generated method stub

  }

  @Override
  public boolean isFocused() {

    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean setFocused() {

    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isClosable() {

    return this.nativeWidget.isClosable();
  }

  @Override
  public void setClosable(boolean closable) {

    this.nativeWidget.setClosable(closable);
  }

  /**
   * @param tab the JavaFx {@link Tab}.
   * @return the owning {@link FxTab}.
   */
  public static FxTab get(Tab tab) {

    return (FxTab) tab.getUserData();
  }

}
