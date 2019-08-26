package junit.org.rapidpm.event.desktop.to.web.api.login;

import junit.org.rapidpm.event.desktop.to.web.api.ServerExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.api.login.LoginService;
import org.rapidpm.event.desktop.to.web.api.login.LoginServiceProvider;
import org.rapidpm.event.desktop.to.web.api.login.SessionUser;

import java.util.List;

@ExtendWith(ServerExtension.class)
public class LoginServiceClientTest
    implements HasLogger {


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


  @Test
  void test003() {
    LoginService service = service();
    Assertions.assertThrows(NullPointerException.class, () -> {
      service.authenticate(null, null);
    });

  }

  private LoginService service() {
    return new LoginServiceProvider().load()
                                     .ifAbsent(() -> logger().warning("LoginService not available"))
                                     .ifAbsent(() -> {
                                       throw new RuntimeException();
                                     })
                                     .get();
  }

  @Test
  void test004() {
    final LoginService      service = service();
    final List<SessionUser> result  = service.authenticate("", "");
    Assertions.assertTrue(result.size() == 0);
  }

  @Test
  void test005() {
    final LoginService      service = service();
    final List<SessionUser> result  = service.authenticate("xx", "xx");
    Assertions.assertTrue(result.size() == 0);
  }

  @Test
  void test006() {
    final LoginService      service = service();
    final List<SessionUser> result  = service.authenticate("admin", "admin");
    Assertions.assertTrue(result.size() == 1);
    final SessionUser user = result.get(0);
    Assertions.assertEquals("Jon Doe", user.getUsername());
  }
}
