package org.rapidpm.event.desktop.to.web.api.login;

import java.util.List;

public interface LoginService {
//  Optional<User> authenticate(String username , String password);
  //List is easier for JSON and Legacy pre Java8
  List<SessionUser> authenticate(String username , String password);
}
