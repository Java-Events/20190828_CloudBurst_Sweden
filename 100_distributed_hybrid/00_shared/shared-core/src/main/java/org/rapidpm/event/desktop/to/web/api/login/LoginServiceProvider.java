package org.rapidpm.event.desktop.to.web.api.login;

import org.rapidpm.event.desktop.to.web.api.service.ServiceProvider;
import org.rapidpm.frp.model.Result;

public class LoginServiceProvider implements ServiceProvider<LoginService> {
  @Override
  public Result<LoginService> load() {
    return ServiceProvider.<LoginService>loadService().apply(LoginService.class);
  }
}
