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
package org.eclipse.che.workspace.infrastructure.kubernetes.namespace;

import io.fabric8.kubernetes.api.model.Event;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.che.api.workspace.server.spi.InfrastructureException;
import org.eclipse.che.workspace.infrastructure.kubernetes.KubernetesClientFactory;
import org.eclipse.che.workspace.infrastructure.kubernetes.KubernetesInfrastructureException;
import org.eclipse.che.workspace.infrastructure.kubernetes.util.PodEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergii Leshchenko
 */
public class KubernetesEvents {

  private static final Logger LOG = LoggerFactory.getLogger(KubernetesEvents.class);

  private final String namespace;
  private final String workspaceId;
  private final KubernetesClientFactory clientFactory;

  private Watch eventWatch;
  private Date watcherInitializationDate;
  private final ConcurrentLinkedQueue<Watcher<Event>> eventsHandlers = new ConcurrentLinkedQueue<>();

  public KubernetesEvents(String namespace, String workspaceId,
      KubernetesClientFactory clientFactory) {
    this.namespace = namespace;
    this.workspaceId = workspaceId;
    this.clientFactory = clientFactory;
  }

  public void watchEvents(Watcher<Event> eventWatcher) throws InfrastructureException {
    if (eventWatch == null) {
      final Watcher<Event> watcher =
          new Watcher<Event>() {
            @Override
            public void eventReceived(Action action, Event event) {
              try {
                if (happenedAfterWatcherInitialization(event)) {
                  eventsHandlers.forEach(h -> h.eventReceived(action, event));
                }
              } catch (ParseException | IllegalArgumentException e) {
                LOG.error(
                    "Failed to parse last timestamp of the event. Cause: {}. Event: {}",
                    e.getMessage(),
                    event);
              }
            }

            @Override
            public void onClose(KubernetesClientException ignored) {}

            /**
             * Returns true if 'lastTimestamp' of the event is *after* the time of the watcher
             * initialization
             */
            private boolean happenedAfterWatcherInitialization(Event event)
                throws ParseException {
              String eventLastTimestamp = event.getLastTimestamp();
              Date eventLastTimestampDate =
                  PodEvents.convertEventTimestampToDate(eventLastTimestamp);
              return eventLastTimestampDate.after(watcherInitializationDate);
            }
          };
      try {
        watcherInitializationDate = new Date();
        eventWatch = clientFactory.create(workspaceId).events().inNamespace(namespace).watch(watcher);
      } catch (KubernetesClientException ex) {
        throw new KubernetesInfrastructureException(ex);
      }
    }
    eventsHandlers.add(eventWatcher);
  }

  /** Stops watching the pods inside Kubernetes namespace. */
  public void stopWatch() {
    try {
      if (eventWatch != null) {
        eventWatch.close();
      }
    } catch (KubernetesClientException ex) {
      LOG.error(
          "Failed to stop pod watcher for namespace '{}'. Cause: '{}'", namespace, ex.getMessage(), ex);
    }
    eventsHandlers.clear();
  }

}
