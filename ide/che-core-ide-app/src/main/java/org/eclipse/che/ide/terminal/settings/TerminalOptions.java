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
import org.eclipse.che.ide.terminal.Terminal;

/**
 * Terminal options. See more {@link Terminal}.
 *
 * @author Evgen Vidolob
 * @author Oleksandr Andriienko
 */
@JsType(namespace = JsPackage.GLOBAL)
public class TerminalOptions {
  /** Default terminal options constructor */
  @JsIgnore
  public TerminalOptions() {
    setTheme(new TerminalTheme());
    setConvertEol(false);
    setTermName("xterm");
    setCols(80);
    setRows(24);
    setCursorBlink(false);
    setCursorStyle("block");
    setVisualBell(false);
    setPopOnBell(false);
    setScrollBack(1000);
    setScreenKeys(false);
    setDebug(false);
    setCancelEvents(false);
    setDisableStdin(false);
    setUseFlowControl(false);
    setTabStopWidth(8);
    setFontSize(12);
    setFontFamily("DejaVu Sans Mono");
  }

  @JsProperty(name = "convertEol")
  public native void setConvertEol(boolean convertEol);

  @JsProperty(name = "convertEol")
  public native boolean getConvertEol();

  @JsProperty(name = "termName")
  public native void setTermName(String termName);

  @JsProperty(name = "termName")
  public native String getTermName();

  @JsProperty(name = "cols")
  public native void setCols(int cols);

  @JsProperty(name = "cols")
  public native int getCols();

  @JsProperty(name = "rows")
  public native void setRows(int rows);

  @JsProperty(name = "rows")
  public native int getRows();

  @JsProperty(name = "cursorBlink")
  public native void setCursorBlink(boolean cursorBlink);

  @JsProperty(name = "cursorBlink")
  public native boolean getCursorBlink();

  @JsProperty(name = "cursorStyle")
  public native void setCursorStyle(String cursorStyle);

  @JsProperty(name = "cursorStyle")
  public native String getCursorStyle();

  @JsProperty(name = "visualBell")
  public native void setVisualBell(boolean visualBell);

  @JsProperty(name = "visualBell")
  public native boolean getVisualBell();

  @JsProperty(name = "popOnBell")
  public native void setPopOnBell(boolean popOnBell);

  @JsProperty(name = "popOnBell")
  public native boolean getPopOnBell();

  @JsProperty(name = "scrollback")
  public native void setScrollBack(int scrollBack);

  @JsProperty(name = "scrollback")
  public native int getScrollBack();

  @JsProperty(name = "screenKeys")
  public native void setScreenKeys(boolean screenKeys);

  @JsProperty(name = "screenKeys")
  public native boolean getScreenKeys();

  @JsProperty(name = "debug")
  public native void setDebug(boolean debug);

  @JsProperty(name = "debug")
  public native boolean getDebug();

  @JsProperty(name = "cancelEvents")
  public native void setCancelEvents(boolean cancelEvents);

  @JsProperty(name = "cancelEvents")
  public native boolean getCancelEvents();

  @JsProperty(name = "disableStdin")
  public native void setDisableStdin(boolean disableStdin);

  @JsProperty(name = "disableStdin")
  public native boolean getDisableStdin();

  @JsProperty(name = "useFlowControl")
  public native void setUseFlowControl(boolean useFlowControl);

  @JsProperty(name = "useFlowControl")
  public native boolean getUseFlowControl();

  @JsProperty(name = "tabStopWidth")
  public native void setTabStopWidth(int tabStopWidth);

  @JsProperty(name = "tabStopWidth")
  public native int getTabStopWidth();

  @JsProperty(name = "theme")
  public native void setTheme(TerminalTheme theme);

  @JsProperty(name = "theme")
  public native TerminalTheme getTheme();

  @JsProperty(name = "fontSize")
  public native int getFontSize();

  @JsProperty(name = "fontSize")
  public native void setFontSize(int fontSize);

  @JsProperty(name = "fontFamily")
  public native String getFontFamily();

  @JsProperty(name = "fontFamily")
  public native void setFontFamily(String fontFamily);
}
