package org.rapidpm.event.desktop.to.web.api.usermanagement;

import java.util.List;

public interface UserRepository {

  List<User> loadUser(String username, String password);

  void storeUser(User user);

  void deleteUser(User user);
}
