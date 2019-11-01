/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.api.workspace.server.devfile.schema;

import static org.eclipse.che.api.workspace.server.devfile.Constants.SCHEMA_LOCATION;
import static org.eclipse.che.commons.lang.IoUtil.getResource;
import static org.eclipse.che.commons.lang.IoUtil.readAndCloseQuietly;

import java.io.IOException;
import java.io.StringReader;
import java.lang.ref.SoftReference;
import javax.inject.Singleton;

/** Loads a schema content and stores it in soft reference. */
@Singleton
public class DevfileSchemaProvider {

  private SoftReference<String> schemaRef = new SoftReference<>(null);

  public String getSchemaContent() throws IOException {
    //String schema = schemaRef.get();
	String schema = null;
    if (schema == null) {
      schema = loadFile();
      schemaRef = new SoftReference<>(schema);
    }
    return schema;
  }

  public StringReader getAsReader() throws IOException {
    return new StringReader(getSchemaContent());
  }

  private String loadFile() throws IOException {
    return readAndCloseQuietly(getResource(SCHEMA_LOCATION));
  }
}
