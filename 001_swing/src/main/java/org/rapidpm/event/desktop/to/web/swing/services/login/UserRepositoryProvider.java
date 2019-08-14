package org.rapidpm.event.desktop.to.web.swing.services.login;

import org.rapidpm.event.desktop.to.web.swing.services.ServiceProvider;
import org.rapidpm.frp.model.Result;

public class UserRepositoryProvider
    implements ServiceProvider<UserRepository> {

  @Override
  public Result<UserRepository> load() {
    return ServiceProvider.<UserRepository>loadService().apply(UserRepository.class);
  }
}