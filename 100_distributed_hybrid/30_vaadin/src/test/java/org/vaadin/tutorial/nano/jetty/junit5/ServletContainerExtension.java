package org.vaadin.tutorial.nano.jetty.junit5;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.rapidpm.event.desktop.to.web.api.Constants;
import org.vaadin.tutorial.nano.jetty.CoreUIService;

import static org.vaadin.tutorial.nano.jetty.junit5.BackendExtension.MAPPED_HOST;
import static org.vaadin.tutorial.nano.jetty.junit5.BackendExtension.MAPPED_PORT;


public class ServletContainerExtension
    implements BeforeEachCallback, AfterEachCallback {

  @Override
  public void beforeEach(ExtensionContext ctx) throws Exception {
//    Stagemonitor.init();


    final Integer mappedPort = ctx.getStore(ExtensionContext.Namespace.GLOBAL)
                               .get(MAPPED_PORT, Integer.class);
    final String mappedHost = ctx.getStore(ExtensionContext.Namespace.GLOBAL)
                        .get(MAPPED_HOST, String.class);

    System.setProperty(Constants.PORT, String.valueOf(mappedPort));
    System.setProperty(Constants.HOST, mappedHost);


    final CoreUIService uiService = new CoreUIService();
    uiService.startup();
    ctx.getStore(ExtensionContext.Namespace.GLOBAL)
       .put(CoreUIService.class.getSimpleName(), uiService);
  }


  @Override
  public void afterEach(ExtensionContext ctx) throws Exception {
    ctx.getStore(ExtensionContext.Namespace.GLOBAL)
       .get(CoreUIService.class.getSimpleName(), CoreUIService.class).jetty.ifPresent(server -> {
      try {
        server.stop();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
