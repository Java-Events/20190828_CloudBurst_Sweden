package org.rapidpm.event.desktop.to.web.swing.services.login;

public class LoginServiceSwing
    implements LoginService {
  @Override
  public boolean authenticate(String username, String password) {
    if (checkNullOrEmpty(username)) return false;
    if (checkNullOrEmpty(password)) return false;

    //TODO simple Solution
    if (username.equals("admin") && password.equals("admin")) return true;

    return false;
  }

  private boolean checkNullOrEmpty(String username) {
    if (username == null) return true;
    return username.isEmpty();
  }
}
