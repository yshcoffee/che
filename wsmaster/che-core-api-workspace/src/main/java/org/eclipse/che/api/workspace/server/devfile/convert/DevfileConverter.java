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
package org.eclipse.che.api.workspace.server.devfile.convert;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static java.util.Collections.singleton;
import static org.eclipse.che.api.workspace.server.devfile.Components.getIdentifiableComponentName;
import static org.eclipse.che.api.workspace.server.devfile.Constants.CURRENT_API_VERSION;

import java.util.Map;

import javax.inject.Inject;

import org.eclipse.che.api.core.ServerException;
import org.eclipse.che.api.core.model.workspace.WorkspaceConfig;
import org.eclipse.che.api.core.model.workspace.devfile.Command;
import org.eclipse.che.api.core.model.workspace.devfile.Component;
import org.eclipse.che.api.core.model.workspace.devfile.Devfile;
import org.eclipse.che.api.workspace.server.devfile.DevfileRecipeFormatException;
import org.eclipse.che.api.workspace.server.devfile.FileContentProvider;
import org.eclipse.che.api.workspace.server.devfile.URLFetcher;
import org.eclipse.che.api.workspace.server.devfile.URLFileContentProvider;
import org.eclipse.che.api.workspace.server.devfile.convert.component.ComponentToWorkspaceApplier;
import org.eclipse.che.api.workspace.server.devfile.exception.DevfileException;
import org.eclipse.che.api.workspace.server.devfile.exception.DevfileFormatException;
import org.eclipse.che.api.workspace.server.model.impl.CommandImpl;
import org.eclipse.che.api.workspace.server.model.impl.ProjectConfigImpl;
import org.eclipse.che.api.workspace.server.model.impl.WorkspaceConfigImpl;
import org.eclipse.che.api.workspace.server.model.impl.devfile.DevfileImpl;
import org.eclipse.che.api.workspace.server.model.impl.devfile.ProjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

/**
 * Helps to convert Devfile to workspace config and back.
 *
 * @author Max Shaposhnyk
 * @author Sergii Leshchenko
 */
public class DevfileConverter {

	  private static final Logger LOG = LoggerFactory.getLogger(DevfileConverter.class);
  private final ProjectConverter projectConverter;
  private final CommandConverter commandConverter;
  private final Map<String, ComponentToWorkspaceApplier> componentTypeToApplier;
  private final DefaultEditorProvisioner defaultEditorProvisioner;
  private final URLFileContentProvider urlFileContentProvider;

  @Inject
  public DevfileConverter(
      ProjectConverter projectConverter,
      CommandConverter commandConverter,
      Map<String, ComponentToWorkspaceApplier> componentTypeToApplier,
      DefaultEditorProvisioner defaultEditorProvisioner,
      URLFetcher urlFetcher) {
    this.projectConverter = projectConverter;
    this.commandConverter = commandConverter;
    this.componentTypeToApplier = componentTypeToApplier;
    this.defaultEditorProvisioner = defaultEditorProvisioner;
    this.urlFileContentProvider = new URLFileContentProvider(null, urlFetcher);
  }

  public WorkspaceConfig convert(Devfile devfile) throws ServerException {
    try {
      return devFileToWorkspaceConfig(
          new DevfileImpl(devfile), FileContentProvider.cached(urlFileContentProvider));
    } catch (DevfileException e) {
      throw new ServerException(e.getMessage(), e);
    }
  }

  /**
   * Converts given {@link Devfile} into {@link WorkspaceConfigImpl workspace config}.
   *
   * @param devfile initial devfile
   * @param contentProvider content provider for recipe-type component or plugin references
   * @return constructed workspace config
   * @throws DevfileException when general devfile error occurs
   * @throws DevfileException when devfile requires additional files content but the specified
   *     content provider does not support it
   * @throws DevfileFormatException when devfile format is invalid
   * @throws DevfileRecipeFormatException when content of the file specified in recipe type
   *     component is empty or its format is invalid
   */
  public WorkspaceConfigImpl devFileToWorkspaceConfig(
      DevfileImpl devfile, FileContentProvider contentProvider) throws DevfileException {
    checkArgument(devfile != null, "Devfile must not be null");
    checkArgument(contentProvider != null, "Content provider must not be null");

    // make copy to avoid modification of original devfile
    devfile = new DevfileImpl(devfile);

    validateCurrentVersion(devfile);

    defaultEditorProvisioner.apply(devfile, contentProvider);

    WorkspaceConfigImpl config = new WorkspaceConfigImpl();

    config.setName(devfile.getName());

    for (Command command : devfile.getCommands()) {
      CommandImpl com = commandConverter.toWorkspaceCommand(command, contentProvider);
      if (com != null) {
        config.getCommands().add(com);
      }
    }

    // note that component applier modifies commands in workspace config
    // so, commands should be already converted
    for (Component component : devfile.getComponents()) {

        LOG.error(
            "[YSH/DevfileConverter] cpuLimit:" + component.getCpuLimit());
        
      ComponentToWorkspaceApplier applier = componentTypeToApplier.get(component.getType());
      if (applier == null) {
        throw new DevfileException(
            String.format(
                "Devfile contains component `%s` with type `%s` that can not be converted to workspace",
                getIdentifiableComponentName(component), component.getType()));
      }
      applier.apply(config, component, contentProvider);
    }

    for (ProjectImpl project : devfile.getProjects()) {
      ProjectConfigImpl projectConfig = projectConverter.toWorkspaceProject(project);
      config.getProjects().add(projectConfig);
    }

    config.getAttributes().putAll(devfile.getAttributes());

    return config;
  }

  private static void validateCurrentVersion(Devfile devFile) throws DevfileFormatException {
    if (Strings.isNullOrEmpty(devFile.getApiVersion())) {
      throw new DevfileFormatException("Provided Devfile has no API version specified");
    }
    if (!CURRENT_API_VERSION.equals(devFile.getApiVersion())) {
      throw new DevfileFormatException(
          format(
              "Provided Devfile has unsupported version '%s'. The following versions are"
                  + " supported: %s",
              devFile.getApiVersion(), singleton(CURRENT_API_VERSION)));
    }
  }
}
