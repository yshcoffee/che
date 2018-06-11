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

import elemental.events.KeyboardEvent;
import jsinterop.annotations.JsFunction;
import org.eclipse.che.ide.terminal.helpers.TerminalGeometry;

/** @author Oleksandr Andriienko */
public interface TerminalHandlers {

  @JsFunction
  @FunctionalInterface
  interface TerminalDataEventHandler<T extends String> {
    void invoke(T... args);
  }

  @JsFunction
  @FunctionalInterface
  interface TerminalResizeEventHandler<T extends TerminalGeometry> {
    void invoke(T args);
  }

  @JsFunction
  @FunctionalInterface
  interface CustomKeyEventHandler {
    boolean onKeyDown(KeyboardEvent ev);
  }
}
