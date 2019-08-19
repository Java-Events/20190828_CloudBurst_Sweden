package org.rapidpm.event.desktop.to.web.api.login;

import java.time.Instant;

public class LoginRequestResponse {
  private boolean authorized;
  private String  message;
  private Instant timestamp;


  public boolean isAuthorized() {
    return authorized;
  }

  public void setAuthorized(boolean authorized) {
    this.authorized = authorized;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }
}
