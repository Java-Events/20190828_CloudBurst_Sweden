package junit.org.rapidpm.event.desktop.to.web.api.login;

import org.rapidpm.event.desktop.to.web.api.login.LoginService;
import org.rapidpm.event.desktop.to.web.api.login.SessionUser;

import java.util.Collections;
import java.util.List;

public class LoginService_MOCK implements LoginService {
  @Override
  public List<SessionUser> authenticate(String username, String password) {
    return Collections.emptyList();
  }
//  @Override
//  public Optional<User> authenticate(String username, String password) {
//    return Optional.empty();
//  }
}
