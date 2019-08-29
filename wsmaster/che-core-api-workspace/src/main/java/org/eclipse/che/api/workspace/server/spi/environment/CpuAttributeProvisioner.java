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
package org.eclipse.che.api.workspace.server.spi.environment;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.eclipse.che.api.core.model.workspace.config.MachineConfig.CPU_LIMIT_ATTRIBUTE;
import static org.eclipse.che.api.core.model.workspace.config.MachineConfig.CPU_REQUEST_ATTRIBUTE;

import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.eclipse.che.api.core.model.workspace.config.MachineConfig;
import org.eclipse.che.commons.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configures cpu attributes for a given machine, if they are not present in {@link
 * MachineConfig} the attributes are taken from recipe, when available, by the specific
 * infrastructure implementation, or from wsmaster properties as a fallback
 *
 * <p>There are two cpu-related properties: - che.workspace.default_cpu_limit_mb - defines
 * default machine cpu limit - che.workspace.default_cpu_request_mb - defines default
 * requested machine cpu allocation
 *
 * <p>if default requested cpu allocation is greater then default cpu limit, requested cpu
 * allocation is set to be equal to cpu limit.
 */
@Singleton
public class CpuAttributeProvisioner {

  private static final Logger LOG = LoggerFactory.getLogger(CpuAttributeProvisioner.class);

  private final String defaultMachineMaxCpuSizeAttribute;
  private final String defaultMachineRequestCpuSizeAttribute;

  @Inject
  public CpuAttributeProvisioner(
      @Named("che.workspace.default_cpu_limit_mb") long defaultMachineMaxCpuSizeAttribute,
      @Named("che.workspace.default_cpu_request_mb")
          long defaultMachineRequestCpuSizeAttribute) {
    // if the passed default request is greater than the default limit, request is ignored
    if (defaultMachineRequestCpuSizeAttribute > defaultMachineMaxCpuSizeAttribute) {
      defaultMachineRequestCpuSizeAttribute = defaultMachineMaxCpuSizeAttribute;
      LOG.error(
          "Requested default container cpu limit is less than default cpu request. Cpu request parameter is ignored.");
    }

    this.defaultMachineMaxCpuSizeAttribute =
        String.valueOf(defaultMachineMaxCpuSizeAttribute);
    this.defaultMachineRequestCpuSizeAttribute =
        String.valueOf(defaultMachineRequestCpuSizeAttribute);
  }

  /**
   * Configures cpu attributes, if they are missing in {@link MachineConfig}
   *
   * <p>Note: Default cpu request and cpu will only be used if BOTH cpuLimit and
   * cpuRequest are null or 0, otherwise the provided value will be used for both parameters.
   *
   * @param machineConfig - given machine configuration
   * @param cpuLimit - cpu limit parameter configured by user in specific infra recipe. Can be
   *     null or 0 if defaults should be used
   * @param cpuRequest - cpu request parameter configured by user in specific infra recipe.
   *     Can be null or 0 if defaults should be used
   */
  public void provision(
      InternalMachineConfig machineConfig,
      @Nullable Long cpuLimit,
      @Nullable Long cpuRequest) {
    // if both properties are not defined
    if ((cpuLimit == null || cpuLimit <= 0)
        && (cpuRequest == null || cpuRequest <= 0)) {
      cpuLimit = Long.valueOf(defaultMachineMaxCpuSizeAttribute);
      cpuRequest = Long.valueOf(defaultMachineRequestCpuSizeAttribute);
    } else if ((cpuLimit == null || cpuLimit <= 0)) { // if cpuLimit only is undefined
      cpuLimit = cpuRequest;
    } else if ((cpuRequest == null
        || cpuRequest <= 0)) { // if cpuRequest only is undefined
      cpuRequest = cpuLimit;
    } else if (cpuRequest > cpuLimit) { // if both properties are defined, but not consistent
      cpuRequest = cpuLimit;
    }

    final Map<String, String> attributes = machineConfig.getAttributes();
    String configuredLimit = attributes.get(CPU_LIMIT_ATTRIBUTE);
    String configuredRequest = attributes.get(CPU_REQUEST_ATTRIBUTE);
    if (isNullOrEmpty(configuredLimit) && isNullOrEmpty(configuredRequest)) {
      attributes.put(CPU_LIMIT_ATTRIBUTE, String.valueOf(cpuLimit));
      attributes.put(CPU_REQUEST_ATTRIBUTE, String.valueOf(cpuRequest));
    } else if (isNullOrEmpty(configuredLimit)) {
      attributes.put(CPU_LIMIT_ATTRIBUTE, configuredRequest);
    } else if (isNullOrEmpty(configuredRequest)) {
      attributes.put(CPU_REQUEST_ATTRIBUTE, configuredLimit);
    }
  }
}
