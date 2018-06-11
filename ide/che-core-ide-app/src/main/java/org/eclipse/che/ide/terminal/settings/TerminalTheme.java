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
package org.eclipse.che.ide.terminal.settings;

import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/** @author Alexander Andrienko */
@JsType(namespace = JsPackage.GLOBAL)
public class TerminalTheme {

  /** Default terminal theme constructor */
  @JsIgnore
  public TerminalTheme() {
    setCursor("white");
    setBackGround("black");
    setForeGround("white");
  }

  @JsProperty(name = "cursor")
  public native void setCursor(String cursor);

  @JsProperty(name = "cursor")
  public native String getCursor();

  @JsProperty(name = "background")
  public native void setBackGround(String backGround);

  @JsProperty(name = "background")
  public native String getBackGround();

  @JsProperty(name = "foreground")
  public native void setForeGround(String foreground);

  @JsProperty(name = "foreground")
  public native String getForeGround();
}
