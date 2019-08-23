package org.rapidpm.event.desktop.to.web.api.usermanagement;

import org.rapidpm.dependencies.core.logger.HasLogger;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class UserRepositoryServersideImpl
    implements UserRepository, HasLogger {

  private static final Map<Key, User> REPOSITORY = new ConcurrentHashMap<>();

  @Override
  public List<User> loadUser(String username, String password) {
    final Key key = new Key(username, password);
    return (REPOSITORY.containsKey(key))
           ? singletonList(REPOSITORY.get(key))
           : emptyList();
  }

  @Override
  public void storeUser(User user) {
    REPOSITORY.put(new Key(user.getUsername(), user.getPassword()), user);
  }

  @Override
  public void deleteUser(User user) {
    REPOSITORY.remove(new Key(user.getUsername(), user.getPassword()));
  }

  public static class Key {
    private String username;
    private String password;

    public Key(String username, String password) {
      this.username = username;
      this.password = password;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Key)) return false;
      Key key = (Key) o;
      return username.equals(key.username) && password.equals(key.password);
    }

    @Override
    public int hashCode() {
      return Objects.hash(username, password);
    }
  }
}
