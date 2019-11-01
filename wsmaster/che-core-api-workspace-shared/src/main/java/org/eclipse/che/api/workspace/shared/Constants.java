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
package org.eclipse.che.api.workspace.shared;

import org.eclipse.che.api.core.model.workspace.Workspace;
import org.eclipse.che.api.core.model.workspace.WorkspaceConfig;
import org.eclipse.che.api.core.model.workspace.runtime.Machine;
import org.eclipse.che.api.core.model.workspace.runtime.Server;

/**
 * Constants for Workspace API
 *
 * @author Yevhenii Voevodin
 */
public final class Constants {

  public static final String LINK_REL_IDE_URL = "ide";
  public static final String LINK_REL_SELF = "self";
  public static final String LINK_REL_ENVIRONMENT_OUTPUT_CHANNEL = "environment/outputChannel";
  public static final String LINK_REL_ENVIRONMENT_STATUS_CHANNEL = "environment/statusChannel";

  public static final String WORKSPACE_STOPPED_BY = "stopped_by";
  public static final String WORKSPACE_STOP_REASON = "stop_reason";

  public static final String LINK_REL_CREATE_STACK = "create stack";
  public static final String LINK_REL_UPDATE_STACK = "update stack";
  public static final String LINK_REL_REMOVE_STACK = "remove stack";
  public static final String LINK_REL_GET_STACK_BY_ID = "get stack by id";
  public static final String LINK_REL_SEARCH_STACKS = "search stacks";

  public static final String LINK_REL_GET_ICON = "get icon link";
  public static final String LINK_REL_UPLOAD_ICON = "upload icon link";
  public static final String LINK_REL_DELETE_ICON = "delete icon link";

  public static final String CHE_WORKSPACE_AUTO_START = "che.workspace.auto_start";

  /** Property name for Che plugin registry url. */
  public static final String CHE_WORKSPACE_PLUGIN_REGISTRY_URL_PROPERTY =
      "che.workspace.plugin_registry_url";

  /** Property name for Che Devfile Registry URL. */
  public static final String CHE_WORKSPACE_DEVFILE_REGISTRY_URL_PROPERTY =
      "che.workspace.devfile_registry_url";

  /** Name for environment variable of machine name */
  public static final String CHE_MACHINE_NAME_ENV_VAR = "CHE_MACHINE_NAME";
  /**
   * Describes time when workspace was created. Should be set/read from {@link
   * Workspace#getAttributes}
   */
  public static final String CREATED_ATTRIBUTE_NAME = "created";
  /**
   * Describes time when workspace was last updated or started. Should be set/read from {@link
   * Workspace#getAttributes}
   */
  public static final String UPDATED_ATTRIBUTE_NAME = "updated";
  /**
   * Describes time when workspace was last stopped. Should be set/read from {@link
   * Workspace#getAttributes}
   */
  public static final String STOPPED_ATTRIBUTE_NAME = "stopped";
  /**
   * Indicates that last workspace stop was abnormal. Should be set/read from {@link
   * Workspace#getAttributes}
   */
  public static final String STOPPED_ABNORMALLY_ATTRIBUTE_NAME = "stoppedAbnormally";
  /**
   * Describes latest workspace runtime error message. Should be set/read from {@link
   * Workspace#getAttributes}
   */
  public static final String ERROR_MESSAGE_ATTRIBUTE_NAME = "errorMessage";

  /**
   * Contains an identifier of an editor that should be used in a workspace. Should be set/read from
   * {@link WorkspaceConfig#getAttributes}.
   *
   * <p>Value is plugin id.
   *
   * <p>Example of the attribute value: 'eclipse/super-editor/0.0.1'
   *
   * <p>This is beta constant that is subject to change or removal.
   */
  public static final String WORKSPACE_TOOLING_EDITOR_ATTRIBUTE = "editor";

  /**
   * The attribute allows to configure workspace to be ephemeral with no PVC attached on K8S /
   * OpenShift infrastructure. Should be set/read from {@link WorkspaceConfig#getAttributes}.
   *
   * <p>Value is expected to be boolean, and if set to 'false' regardless of the PVC strategy,
   * workspace volumes would be created as `emptyDir`. When a workspace Pod is removed for any
   * reason, the data in the `emptyDir` volume is deleted forever
   *
   * @see <a
   *     href="https://www.eclipse.org/che/docs/kubernetes-admin-guide.html#che-workspaces-storage">Che
   *     PVC strategies</a>
   * @see <a href="https://kubernetes.io/docs/concepts/storage/volumes/#emptydir">emptyDir</a>
   */
  public static final String PERSIST_VOLUMES_ATTRIBUTE = "persistVolumes";

  /**
   * Contains a list of workspace tooling plugins that should be used in a workspace. Should be
   * set/read from {@link WorkspaceConfig#getAttributes}.
   *
   * <p>Value is comma separated list of plugins in a format: '< plugin1ID >,<plugin2ID >'<br>
   * Spaces around commas are trimmed. <br>
   *
   * <p>This is beta constant that is subject to change or removal.
   *
   * <p>Example of the attribute value: 'eclipse/plugin1/0.0.1, redhat/plugin2/1.0.0'
   */
  public static final String WORKSPACE_TOOLING_PLUGINS_ATTRIBUTE = "plugins";

  /**
   * Template for workspace attribute key that sets sidecar limit in a plugin. %s should be replaced
   * with pluginPublisher/pluginName. When plugin provides several sidecars this property sets the
   * same limit for each sidecar, so is not that useful in such a case. Value format see {@link
   * KubernetesSize}
   */
  public static final String SIDECAR_MEMORY_LIMIT_ATTR_TEMPLATE = "sidecar.%s.memory_limit";
  public static final String SIDECAR_CPU_LIMIT_ATTR_TEMPLATE = "sidecar.%s.cpu_limit";

  /**
   * Describes workspace runtimes which perform start/stop of this workspace. Should be set/read
   * from {@link Workspace#getAttributes}
   */
  public static final String WORKSPACE_RUNTIMES_ID_ATTRIBUTE = "org.eclipse.che.runtimes_id";

  public static final String COMMAND_PREVIEW_URL_ATTRIBUTE_NAME = "previewUrl";
  public static final String COMMAND_GOAL_ATTRIBUTE_NAME = "goal";

  public static final String WORKSPACE_STATUS_CHANGED_METHOD = "workspace/statusChanged";
  public static final String MACHINE_STATUS_CHANGED_METHOD = "machine/statusChanged";
  public static final String SERVER_STATUS_CHANGED_METHOD = "server/statusChanged";

  public static final String RUNTIME_LOG_METHOD = "runtime/log";

  /**
   * JSON RPC methods for listening to machine logs.
   *
   * @deprecated use {@link #RUNTIME_LOG_METHOD} instead
   */
  @Deprecated public static final String MACHINE_LOG_METHOD = "machine/log";

  public static final String INSTALLER_LOG_METHOD = "installer/log";
  public static final String INSTALLER_STATUS_CHANGED_METHOD = "installer/statusChanged";
  public static final String BOOTSTRAPPER_STATUS_CHANGED_METHOD = "bootstrapper/statusChanged";

  public static final String SERVER_WS_AGENT_HTTP_REFERENCE = "wsagent/http";
  public static final String SERVER_WS_AGENT_WEBSOCKET_REFERENCE = "wsagent/ws";
  public static final String SERVER_TERMINAL_REFERENCE = "terminal";
  public static final String SERVER_SSH_REFERENCE = "ssh";
  public static final String SERVER_EXEC_AGENT_HTTP_REFERENCE = "exec-agent/http";
  public static final String SERVER_EXEC_AGENT_WEBSOCKET_REFERENCE = "exec-agent/ws";

  public static final String WS_AGENT_PORT = "4401/tcp";

  public static final String SUPPORTED_RECIPE_TYPES = "supportedRecipeTypes";

  public static final String NO_ENVIRONMENT_RECIPE_TYPE = "no-environment";

  /** Attribute of {@link Machine} to mark source of the container. */
  public static final String CONTAINER_SOURCE_ATTRIBUTE = "source";

  /**
   * Attribute of {@link Machine} that indicates by which plugin this machines is provisioned
   *
   * <p>It contains plugin id, like "plugin": "eclipse/che-theia/master"
   */
  public static final String PLUGIN_MACHINE_ATTRIBUTE = "plugin";

  /** Mark containers applied to workspace with help recipe definition. */
  public static final String RECIPE_CONTAINER_SOURCE = "recipe";

  /** Mark containers created workspace api like tooling for user development. */
  public static final String TOOL_CONTAINER_SOURCE = "tool";

  /** The projects volume has a standard name used in a couple of locations. */
  public static final String PROJECTS_VOLUME_NAME = "projects";

  /** Attribute of {@link Server} that specifies exposure of which port created the server */
  public static final String SERVER_PORT_ATTRIBUTE = "port";

  /** When generating workspace name from generateName, append this many characters. */
  public static final int WORKSPACE_GENERATE_NAME_CHARS_APPEND = 5;

  private Constants() {}
}
