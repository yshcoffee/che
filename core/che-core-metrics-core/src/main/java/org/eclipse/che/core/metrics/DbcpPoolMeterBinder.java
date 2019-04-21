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

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

@Singleton
public class DbcpPoolMeterBinder implements MeterBinder {

  private final BasicDataSource dataSource;

  @Inject
  public DbcpPoolMeterBinder(BasicDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void bindTo(MeterRegistry registry) {
    Gauge.builder("che.jdbc.connections.max", dataSource::getMaxTotal)
        .description("Maximum number of active connections that can be allocated at the same time")
        .register(registry);
    Gauge.builder("che.jdbc.connections.active", dataSource::getNumActive)
        .description(
            "The current number of active connections that have been allocated from this data source")
        .register(registry);
    Gauge.builder("che.jdbc.connections.idle", dataSource::getMinIdle)
        .description("Minimum number of idle connections in the pool")
        .register(registry);
  }
}
