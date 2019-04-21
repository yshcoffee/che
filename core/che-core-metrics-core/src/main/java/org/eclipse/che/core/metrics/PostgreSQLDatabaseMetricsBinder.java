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
package org.eclipse.che.core.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.binder.db.PostgreSQLDatabaseMetrics;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.eclipse.che.commons.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PostgreSQLDatabaseMetricsBinder implements MeterBinder {
  private static final Logger LOG = LoggerFactory.getLogger(PostgreSQLDatabaseMetrics.class);

  // private final DataSource dataSource;

  private PostgreSQLDatabaseMetrics postgreSQLDatabaseMetrics;

  @Inject
  public PostgreSQLDatabaseMetricsBinder(@Nullable BasicDataSource dataSource) {
    if (dataSource != null) {
      if ("org.postgresql.Driver".equals(dataSource.getDriverClassName())) {
        LOG.info("ok");
        try (Connection c = dataSource.getConnection();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT current_database()")) {

          if (r.next()) {
            String var = r.getString(1);
            LOG.info("Db name {}", var);
            postgreSQLDatabaseMetrics = new PostgreSQLDatabaseMetrics(dataSource, var);
          }

        } catch (SQLException e) {
          throw new RuntimeException(e.getMessage(), e);
        }
      }
    }
  }

  @Override
  public void bindTo(MeterRegistry registry) {
    if (postgreSQLDatabaseMetrics != null) {
      postgreSQLDatabaseMetrics.bindTo(registry);
    }
  }
}
