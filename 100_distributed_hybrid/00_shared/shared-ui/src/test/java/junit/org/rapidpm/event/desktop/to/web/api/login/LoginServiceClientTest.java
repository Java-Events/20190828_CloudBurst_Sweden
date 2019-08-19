package junit.org.rapidpm.event.desktop.to.web.api.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.event.desktop.to.web.api.login.LoginServiceProvider;
import org.rapidpm.event.desktop.to.web.api.login.SessionUser;

import java.util.List;

public class LoginServiceClientTest {


  // TODO manage the BackEndService !!!!
//  @Disabled
  @Test
  void test001() {
    final List<SessionUser> sessionUser = new LoginServiceProvider().load()
                                                                    .get()
                                                                    .authenticate("admin", "admin");
    Assertions.assertTrue(sessionUser != null);
    Assertions.assertFalse(sessionUser.isEmpty());
    Assertions.assertFalse(sessionUser.size() > 1);

    Assertions.assertEquals("Jon Doe", sessionUser.get(0)
                                                  .getUsername());

  }  // TODO manage the BackEndService !!!!

  //  @Disabled
  @Test
  void test002() {
    final List<SessionUser> sessionUser = new LoginServiceProvider().load()
                                                                    .get()
                                                                    .authenticate("admin", "X");
    Assertions.assertTrue(sessionUser != null);
    Assertions.assertTrue(sessionUser.isEmpty());

  }
}
