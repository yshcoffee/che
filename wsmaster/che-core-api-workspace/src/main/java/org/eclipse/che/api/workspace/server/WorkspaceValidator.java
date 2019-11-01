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
package org.eclipse.che.api.workspace.server;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;
import static org.eclipse.che.api.core.model.workspace.config.MachineConfig.MEMORY_LIMIT_ATTRIBUTE;
import static org.eclipse.che.api.core.model.workspace.config.MachineConfig.MEMORY_REQUEST_ATTRIBUTE;
import static org.eclipse.che.api.core.model.workspace.config.MachineConfig.CPU_LIMIT_ATTRIBUTE;
import static org.eclipse.che.api.core.model.workspace.config.MachineConfig.CPU_REQUEST_ATTRIBUTE;
import static org.eclipse.che.api.workspace.server.SidecarToolingWorkspaceUtil.isSidecarBasedWorkspace;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.inject.Singleton;
import org.eclipse.che.api.core.ServerException;
import org.eclipse.che.api.core.ValidationException;
import org.eclipse.che.api.core.model.workspace.Workspace;
import org.eclipse.che.api.core.model.workspace.WorkspaceConfig;
import org.eclipse.che.api.core.model.workspace.config.Command;
import org.eclipse.che.api.core.model.workspace.config.Environment;
import org.eclipse.che.api.core.model.workspace.config.MachineConfig;
import org.eclipse.che.api.core.model.workspace.config.Recipe;
import org.eclipse.che.api.core.model.workspace.config.Volume;

/**
 * Validator for {@link Workspace}.
 *
 * @author Yevhenii Voevodin
 */
@Singleton
public class WorkspaceValidator {

  /**
   * Must contain [3, 100] characters, first and last character is letter or digit, available
   * characters {A-Za-z0-9.-_}.
   */
  private static final Pattern WS_NAME =
      Pattern.compile("[a-zA-Z0-9][-_.a-zA-Z0-9]{1,98}[a-zA-Z0-9]");

  private static final Pattern VOLUME_NAME =
      Pattern.compile("[a-zA-Z][a-zA-Z0-9-_.]{0,18}[a-zA-Z0-9]");
  private static final Pattern VOLUME_PATH = Pattern.compile("/.+");

  /**
   * Checks whether given workspace configuration object is in application valid state, so it
   * provides enough data to be processed by internal components, and the data it provides is valid
   * so consistency is not violated.
   *
   * @param config configuration to validate
   * @throws ValidationException if any of validation constraints is violated
   * @throws ServerException when any other error occurs during environment validation
   */
  public void validateConfig(WorkspaceConfig config) throws ValidationException, ServerException {
    // configuration object properties
    checkNotNull(config.getName(), "Workspace name required");
    check(
        WS_NAME.matcher(config.getName()).matches(),
        "Incorrect workspace name, it must be between 3 and 100 characters and may contain digits, "
            + "latin letters, underscores, dots, dashes and must start and end only with digits, "
            + "latin letters or underscores");

    validateEnvironments(config);

    // commands
    for (Command command : config.getCommands()) {
      check(
          !isNullOrEmpty(command.getName()),
          "Workspace %s contains command with null or empty name",
          config.getName());
      check(
          !isNullOrEmpty(command.getCommandLine())
              || !isNullOrEmpty(
                  command.getAttributes().get(Command.COMMAND_ACTION_REFERENCE_CONTENT_ATTRIBUTE)),
          "Command line or content required for command '%s' in workspace '%s'.",
          command.getName(),
          config.getName());
    }

    // projects
    // TODO

    // ensure using either plugins or installers but not both
    validatePlugins(config);
  }

  /**
   * Checks whether workspace attributes are valid. The attribute is valid if it's key is not null &
   * not empty & is not prefixed with 'codenvy'.
   *
   * @param attributes the map to check
   * @throws ValidationException when attributes are not valid
   */
  public void validateAttributes(Map<String, String> attributes) throws ValidationException {
    for (String attributeName : attributes.keySet()) {
      // attribute name should not be empty and should not start with codenvy
      check(
          attributeName != null
              && !attributeName.trim().isEmpty()
              && !attributeName.toLowerCase().startsWith("codenvy"),
          "Attribute name '%s' is not valid",
          attributeName);
    }
  }

  private void validateEnvironments(WorkspaceConfig config) throws ValidationException {
    boolean environmentIsNotSet =
        (config.getEnvironments() == null || config.getEnvironments().isEmpty())
            && isNullOrEmpty(config.getDefaultEnv());
    boolean isSidecarWorkspace = isSidecarBasedWorkspace(config.getAttributes());
    if (environmentIsNotSet && isSidecarWorkspace) {
      // sidecar based workspaces allowed not to have environment
      return;
    }
    check(!isNullOrEmpty(config.getDefaultEnv()), "Workspace default environment name required");
    checkNotNull(config.getEnvironments(), "Workspace must contain at least one environment");
    check(
        config.getEnvironments().containsKey(config.getDefaultEnv()),
        "Workspace default environment configuration required");

    for (Environment environment : config.getEnvironments().values()) {
      checkNotNull(environment, "Environment must not be null");
      Recipe recipe = environment.getRecipe();
      checkNotNull(recipe, "Environment recipe must not be null");
      checkNotNull(recipe.getType(), "Environment recipe type must not be null");

      for (Entry<String, ? extends MachineConfig> machineEntry :
          environment.getMachines().entrySet()) {
        validateMachine(machineEntry.getKey(), machineEntry.getValue());
      }
    }
  }

  private void validateMachine(String machineName, MachineConfig machine)
      throws ValidationException {
    validateLongAttribute(
        MEMORY_LIMIT_ATTRIBUTE, machine.getAttributes().get(MEMORY_LIMIT_ATTRIBUTE), machineName);
    validateLongAttribute(
        MEMORY_REQUEST_ATTRIBUTE,
        machine.getAttributes().get(MEMORY_REQUEST_ATTRIBUTE),
        machineName);

    validateDoubleAttribute(
        CPU_LIMIT_ATTRIBUTE, machine.getAttributes().get(CPU_LIMIT_ATTRIBUTE), machineName);
    validateDoubleAttribute(
        CPU_REQUEST_ATTRIBUTE,
        machine.getAttributes().get(CPU_REQUEST_ATTRIBUTE),
        machineName);

    for (Entry<String, ? extends Volume> volumeEntry : machine.getVolumes().entrySet()) {
      String volumeName = volumeEntry.getKey();
      check(
          VOLUME_NAME.matcher(volumeName).matches(),
          "Volume name '%s' in machine '%s' is invalid",
          volumeName,
          machineName);
      Volume volume = volumeEntry.getValue();
      check(
          volume != null && !isNullOrEmpty(volume.getPath()),
          "Path of volume '%s' in machine '%s' is invalid. It should not be empty",
          volumeName,
          machineName);
      check(
          VOLUME_PATH.matcher(volume.getPath()).matches(),
          "Path '%s' of volume '%s' in machine '%s' is invalid. It should be absolute",
          volume.getPath(),
          volumeName,
          machineName);
    }
  }

  private void validateLongAttribute(
      String attributeName, String attributeValue, String machineName) throws ValidationException {
    if (attributeValue != null) {
      try {
        Long.parseLong(attributeValue);
      } catch (NumberFormatException e) {
        throw new ValidationException(
            format(
                "Value '%s' of attribute '%s' in machine '%s' is illegal",
                attributeValue, attributeName, machineName));
      }
    }
  }
  

  private void validateDoubleAttribute(
      String attributeName, String attributeValue, String machineName) throws ValidationException {
    if (attributeValue != null) {
      try {
        Double.parseDouble(attributeValue);
      } catch (NumberFormatException e) {
        throw new ValidationException(
            format(
                "Value '%s' of attribute '%s' in machine '%s' is illegal",
                attributeValue, attributeName, machineName));
      }
    }
  }

  /**
   * Check that workspace has either plugins defined in attributes (Che 7) or installers defined in
   * machines (Che 6).
   *
   * @throws ValidationException if workspace config contains both installers section and nonempty
   *     plugins or editor field in attributes.
   */
  private void validatePlugins(WorkspaceConfig config) throws ValidationException {
    Optional<String> installers =
        config
            .getEnvironments()
            .values()
            .stream()
            .flatMap(env -> env.getMachines().values().stream())
            .flatMap(machine -> machine.getInstallers().stream())
            .findAny();
    Map<String, String> attributes = config.getAttributes();
    if (isSidecarBasedWorkspace(attributes) && installers.isPresent()) {
      throw new ValidationException("Workspace config cannot have both plugins and installers.");
    }
  }

  /**
   * Checks that object reference is not null, throws {@link ValidationException} in the case of
   * null {@code object} with given {@code message}.
   */
  private static void checkNotNull(Object object, String message) throws ValidationException {
    if (object == null) {
      throw new ValidationException(message);
    }
  }

  /**
   * Checks that expression is true, throws {@link ValidationException} otherwise.
   *
   * <p>Exception uses error message built from error message template and error message parameters.
   */
  private static void check(boolean expression, String fmt, Object... args)
      throws ValidationException {
    if (!expression) {
      throw new ValidationException(format(fmt, args));
    }
  }

  /**
   * Checks that expression is true, throws {@link ValidationException} otherwise.
   *
   * <p>Exception uses error message built from error message template and error message parameters.
   */
  private static void check(boolean expression, String message) throws ValidationException {
    if (!expression) {
      throw new ValidationException(message);
    }
  }
}
