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
package org.eclipse.che.core.db;

import java.sql.SQLException;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class BasicDataSourceProvider implements Provider<BasicDataSource> {

  @Inject DataSource dataSource;

  @Override
  public BasicDataSource get() {
    if (dataSource instanceof BasicDataSource) {
      return (BasicDataSource) dataSource;
    } else {
      try {
        if (dataSource.isWrapperFor(BasicDataSource.class)) {
          return dataSource.unwrap(BasicDataSource.class);
        }
      } catch (SQLException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }

    throw new RuntimeException("Unexpected exception");
  }
}
