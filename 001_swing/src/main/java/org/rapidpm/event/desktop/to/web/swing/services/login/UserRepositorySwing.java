package org.rapidpm.event.desktop.to.web.swing.services.login;

import org.rapidpm.dependencies.core.logger.HasLogger;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositorySwing
    implements UserRepository, HasLogger {

  private static final Map<Key, User> REPOSITORY = new ConcurrentHashMap<>();

  @Override
  public Optional<User> loadUser(String username, String password) {
    final Key key = new Key(username, password);
    return (REPOSITORY.containsKey(key))
           ? Optional.of(REPOSITORY.get(key))
           : Optional.empty();
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
