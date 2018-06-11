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

import com.google.gwt.dom.client.Element;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.eclipse.che.ide.terminal.helpers.TerminalGeometry;
import org.eclipse.che.ide.terminal.settings.TerminalOptions;

/**
 * GWT binding to term.js script
 *
 * @author Evgen Vidolob
 * @author Oleksandr Andriienko
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Terminal {

  @SuppressWarnings("unused")
  public Terminal(TerminalOptions options) {}

  @JsProperty(name = "options")
  public native TerminalOptions getOptions();

  @JsProperty(name = "cols")
  public native int getCols();

  @JsProperty(name = "rows")
  public native int getRows();

  @JsProperty(name = "element")
  public native Element getElement();

  @JsProperty(name = "parent")
  public native Element getParent();

  public native void open(Element element);

  public native void write(String data);

  public native void writeln(String data);

  public native String getText();

  public native void resize(int cols, int rows);

  public native void on(String event, TerminalHandlers.TerminalDataEventHandler function);

  public native void on(String event, TerminalHandlers.TerminalResizeEventHandler function);

  public native void focus();

  public native void blur();

  public native void fit();

  public native void scrollDisp(int dispDiff);

  public native void scrollEnd();

  public native void scrollHome();

  public native boolean hasSelection();

  public native TerminalGeometry proposeGeometry();

  public static native void applyAddon(Object addon);

  @JsMethod(name = "attachCustomKeyEventHandler")
  public native void attachCustomKeyEventHandler(TerminalHandlers.CustomKeyEventHandler keyHandler);

  public native void reset();

  public native void destroy();
}
