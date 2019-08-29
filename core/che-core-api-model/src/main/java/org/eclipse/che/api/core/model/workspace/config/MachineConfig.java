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
package org.eclipse.che.api.core.model.workspace.config;

import java.util.List;
import java.util.Map;

/**
 * Machine configuration
 *
 * @author Alexander Garagatyi
 */
public interface MachineConfig {
	/**
	   * Name of the attribute from {@link #getAttributes()} which if present defines cpu limit of
	   * the machine in bytes. If cpu limit is set in environment specific recipe this attribute used
	   * in {@code MachineConfig} should override value from recipe.
	   */
	  String CPU_LIMIT_ATTRIBUTE = "cpuLimit";

	  /**
	   * Name of the attribute from {@link #getAttributes()} which if present defines requested cpu
	   * allocation of the machine in bytes. If cpu request is set in environment specific recipe
	   * this attribute used in {@code MachineConfig} should override value from recipe. If both request
	   * and limit are defined, and cpu request is greater than the cpu limit, this value is
	   * ignored and only limit is used
	   */
	  String CPU_REQUEST_ATTRIBUTE = "cpuRequest";
	

  /**
   * Name of the attribute from {@link #getAttributes()} which if present defines memory limit of
   * the machine in bytes. If memory limit is set in environment specific recipe this attribute used
   * in {@code MachineConfig} should override value from recipe.
   */
  String MEMORY_LIMIT_ATTRIBUTE = "memoryLimitBytes";

  /**
   * Name of the attribute from {@link #getAttributes()} which if present defines requested memory
   * allocation of the machine in bytes. If memory request is set in environment specific recipe
   * this attribute used in {@code MachineConfig} should override value from recipe. If both request
   * and limit are defined, and memory request is greater than the memory limit, this value is
   * ignored and only limit is used
   */
  String MEMORY_REQUEST_ATTRIBUTE = "memoryRequestBytes";

  /**
   * Name of the attribute from {@link #getAttributes()} which, if present, defines the entrypoint
   * command to be executed in the machine/container.
   *
   * <p>The format is a YAML list of strings, e.g. {@code ['/bin/sh', '-c']}
   */
  String CONTAINER_COMMAND_ATTRIBUTE = "containerCommand";

  /**
   * Name of the attribute from {@link #getAttributes()} which, if present, defines the command line
   * arguments of the entrypoint command specified using the {@link #CONTAINER_COMMAND_ATTRIBUTE}.
   *
   * <p>If {@link #CONTAINER_COMMAND_ATTRIBUTE} is not present, the default command defined in the
   * image is used and the arguments are provided to it.
   *
   * <p>The format is a YAML list of strings, e.g. {@code ['-f', '--yes']}
   */
  String CONTAINER_ARGS_ATTRIBUTE = "containerArgs";

  /**
   * Returns configured installers.
   *
   * <p>Values can be installer id with version separated with ':' symbol or just id then latest
   * version will be used. Values examples: org.exec-agent:v1.0, org.exec-agent,
   * org.exec-agent:latest.
   */
  List<String> getInstallers();

  /** Returns mapping of references to configurations of servers deployed into machine. */
  Map<String, ? extends ServerConfig> getServers();

  /** Returns environment variables of machine. */
  Map<String, String> getEnv();

  /** Returns attributes of machine. */
  Map<String, String> getAttributes();

  /** Returns volumes of machine */
  Map<String, ? extends Volume> getVolumes();
}
