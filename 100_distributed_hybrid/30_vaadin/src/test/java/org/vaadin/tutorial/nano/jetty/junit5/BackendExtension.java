package org.vaadin.tutorial.nano.jetty.junit5;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

public class BackendExtension
    implements BeforeEachCallback, AfterEachCallback {

  public static final String BACKEND = "backend";
  public static final String MAPPED_PORT = "MAPPED_PORT";
  public static final String MAPPED_HOST = "MAPPED_HOST";

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {


    final GenericContainer container = new GenericContainer<>(
        "javaevents/desktop-to-web-backend:latest").withExposedPorts(7000);
    container.start();

    final Integer mappedPort = container.getMappedPort(7000);
    final String  ipAddress  = container.getIpAddress();

    context.getStore(ExtensionContext.Namespace.GLOBAL)
           .put(BACKEND, container);
    context.getStore(ExtensionContext.Namespace.GLOBAL)
           .put(MAPPED_PORT, mappedPort);
    context.getStore(ExtensionContext.Namespace.GLOBAL)
           .put(MAPPED_HOST, ipAddress);

  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    final GenericContainer container = context.getStore(ExtensionContext.Namespace.GLOBAL)
                                              .get(BACKEND, GenericContainer.class);
    if (container != null) container.stop();
  }


}
