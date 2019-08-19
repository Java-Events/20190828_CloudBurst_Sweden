package org.rapidpm.event.desktop.to.web.api.usermanagement;

import org.rapidpm.event.desktop.to.web.api.service.ServiceProvider;
import org.rapidpm.frp.model.Result;

public class UserRepositoryProvider
    implements ServiceProvider<UserRepository> {

  @Override
  public Result<UserRepository> load() {
    return ServiceProvider.<UserRepository>loadService().apply(UserRepository.class);
  }
}