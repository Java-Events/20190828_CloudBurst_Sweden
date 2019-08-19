package junit.org.rapidpm.event.desktop.to.web.api;

import org.junit.jupiter.api.extension.*;
import org.rapidpm.event.desktop.to.web.BackendService;

public class ServerExtension
    implements BeforeAllCallback, AfterAllCallback {

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    context.getStore(ExtensionContext.Namespace.GLOBAL)
           .get("backend", BackendService.class)
           .stop();
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    final BackendService backendService = new BackendService();
    backendService.start(null);

    context.getStore(ExtensionContext.Namespace.GLOBAL)
           .put("backend", backendService);
  }
}
