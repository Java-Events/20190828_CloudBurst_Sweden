package org.rapidpm.event.desktop.to.web.swing.services.login;

import java.util.Optional;

public interface UserRepository {

  Optional<User> loadUser(String username, String password);

  void storeUser(User user);
  void deleteUser(User user);
}
