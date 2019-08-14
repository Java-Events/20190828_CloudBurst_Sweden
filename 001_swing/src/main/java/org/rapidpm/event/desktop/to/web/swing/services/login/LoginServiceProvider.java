package org.rapidpm.event.desktop.to.web.swing.services.login;

import org.rapidpm.event.desktop.to.web.swing.services.ServiceProvider;
import org.rapidpm.frp.model.Result;

public class LoginServiceProvider
    implements ServiceProvider<LoginService> {

  @Override
  public Result<LoginService> load() {
    return ServiceProvider.<LoginService>loadService().apply(LoginService.class);
  }
}
