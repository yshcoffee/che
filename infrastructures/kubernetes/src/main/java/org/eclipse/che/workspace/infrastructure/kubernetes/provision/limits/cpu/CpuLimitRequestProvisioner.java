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
package org.eclipse.che.workspace.infrastructure.kubernetes.provision.limits.cpu;

import static org.eclipse.che.api.core.model.workspace.config.MachineConfig.CPU_LIMIT_ATTRIBUTE;
import static org.eclipse.che.api.core.model.workspace.config.MachineConfig.CPU_REQUEST_ATTRIBUTE;
import static org.eclipse.che.workspace.infrastructure.kubernetes.Names.machineName;

import io.fabric8.kubernetes.api.model.Container;
import java.util.Map;
import javax.inject.Inject;
import org.eclipse.che.api.core.model.workspace.runtime.RuntimeIdentity;
import org.eclipse.che.api.workspace.server.spi.InfrastructureException;
import org.eclipse.che.api.workspace.server.spi.environment.InternalMachineConfig;
import org.eclipse.che.api.workspace.server.spi.environment.CpuAttributeProvisioner;
import org.eclipse.che.commons.annotation.Traced;
import org.eclipse.che.commons.tracing.TracingTags;
import org.eclipse.che.workspace.infrastructure.kubernetes.environment.KubernetesEnvironment;
import org.eclipse.che.workspace.infrastructure.kubernetes.environment.KubernetesEnvironment.PodData;
import org.eclipse.che.workspace.infrastructure.kubernetes.provision.ConfigurationProvisioner;
import org.eclipse.che.workspace.infrastructure.kubernetes.util.Containers;

/**
 * Sets or overrides Kubernetes container RAM limit and request if corresponding attributes are
 * present in machine corresponding to the container.
 *
 * @author Anton Korneta
 */
public class CpuLimitRequestProvisioner implements ConfigurationProvisioner {

  private final CpuAttributeProvisioner cpuAttributeProvisioner;

  @Inject
  public CpuLimitRequestProvisioner(CpuAttributeProvisioner cpuAttributeProvisioner) {
    this.cpuAttributeProvisioner = cpuAttributeProvisioner;
  }

  @Override
  @Traced
  public void provision(KubernetesEnvironment k8sEnv, RuntimeIdentity identity)
      throws InfrastructureException {

    TracingTags.WORKSPACE_ID.set(identity::getWorkspaceId);

    final Map<String, InternalMachineConfig> machines = k8sEnv.getMachines();
    for (PodData pod : k8sEnv.getPodsData().values()) {
      for (Container container : pod.getSpec().getContainers()) {
        InternalMachineConfig machineConfig = machines.get(machineName(pod, container));
        cpuAttributeProvisioner.provision(
            machineConfig, Containers.getCpuLimit(container), Containers.getCpuRequest(container));
        final Map<String, String> attributes = machineConfig.getAttributes();
        String cpuLimitAttribute = attributes.get(CPU_LIMIT_ATTRIBUTE);
        if (cpuLimitAttribute != null) {
          Containers.addCpuLimit(container, Long.parseLong(cpuLimitAttribute));
        }
        String cpuRequestAttribute = attributes.get(CPU_REQUEST_ATTRIBUTE);
        if (cpuRequestAttribute != null) {
          Containers.addCpuRequest(container, Long.parseLong(cpuRequestAttribute));
        }
      }
    }
  }
}
