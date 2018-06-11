/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.ide.terminal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;
import javax.validation.constraints.NotNull;
import org.eclipse.che.ide.terminal.addons.TerminalAddonsProvider;
import org.eclipse.che.ide.terminal.helpers.TerminalGeometry;

/**
 * The class contains methods to display terminal.
 *
 * @author Dmitry Shnurenko
 */
final class TerminalViewImpl extends Composite implements TerminalView, Focusable, RequiresResize {

  interface TerminalViewImplUiBinder extends UiBinder<Widget, TerminalViewImpl> {}

  private static final TerminalViewImplUiBinder UI_BINDER =
      GWT.create(TerminalViewImplUiBinder.class);

  @UiField FlowPanel terminalPanel;

  @UiField Label unavailableLabel;

  private ActionDelegate delegate;

  private Terminal terminal;
  private Element terminalElement;

  public TerminalViewImpl() {
    initWidget(UI_BINDER.createAndBindUi(this));
  }

  @Override
  public void setDelegate(ActionDelegate delegate) {
    this.delegate = delegate;
  }

  /** {@inheritDoc} */
  @Override
  public void setTerminal(@NotNull final Terminal terminal) {
    unavailableLabel.setVisible(false);
    terminalPanel.setVisible(true);

    Terminal.applyAddon(TerminalAddonsProvider.getFitAddon());
    terminal.attachCustomKeyEventHandler(new CustomKeyEventTerminalHandler(terminal));

    this.terminal = terminal;
  }

  /** {@inheritDoc} */
  @Override
  public void showErrorMessage(@NotNull String message) {
    unavailableLabel.setText(message);
    unavailableLabel.setVisible(true);

    terminalPanel.setVisible(false);
  }

  /** Resize {@link Terminal} to current widget size. */
  @Override
  public void onResize() {
    resizeTimer.schedule(200);
  }

  private Timer resizeTimer =
      new Timer() {
        @Override
        public void run() {
          resizeTerminal();
        }
      };

  /**
   * To improve performance we should resize only visible terminals, because "resize terminal" is
   * quite expensive operation. When you click on the tab to activate hidden terminal this method
   * will be executed too, so terminal will be resized anyway.
   */
  private void resizeTerminal() {
    if (!this.isAttached() || !this.isVisible()) {
      return;
    }

    if (terminalElement != null) {
      TerminalGeometry geometry = terminal.proposeGeometry();
      terminal.resize(geometry.getCols(), geometry.getRows());
    } else {
      terminal.open(terminalPanel.getElement());
      terminalElement = terminalPanel.getElement();
    }
  }

  @Override
  public int getTabIndex() {
    return 0;
  }

  @Override
  public void setAccessKey(char key) {}

  private Timer focusTimer =
      new Timer() {
        @Override
        public void run() {
          terminal.focus();
        }
      };

  @Override
  public void setFocus(boolean focused) {
    if (terminal == null || terminal.getElement() == null) {
      return;
    }

    if (focused && !terminal.hasSelection()) {
      focusTimer.schedule(10);
    }
  }

  @Override
  public void setTabIndex(int index) {}
}
