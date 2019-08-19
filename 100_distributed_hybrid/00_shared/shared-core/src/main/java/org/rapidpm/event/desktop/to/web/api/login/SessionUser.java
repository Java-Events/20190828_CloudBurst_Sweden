package org.rapidpm.event.desktop.to.web.api.login;

public class SessionUser {

  public SessionUser() {
  }

  public SessionUser(String username, String userGroup) {
    this.username  = username;
    this.userGroup = userGroup;
  }

  private String username;
  private String userGroup;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserGroup() {
    return userGroup;
  }

  public void setUserGroup(String userGroup) {
    this.userGroup = userGroup;
  }

  @Override
  public String toString() {
    return "User{" + "username='" + username + '\'' + ", userGroup=" + userGroup + '}';
  }


}
