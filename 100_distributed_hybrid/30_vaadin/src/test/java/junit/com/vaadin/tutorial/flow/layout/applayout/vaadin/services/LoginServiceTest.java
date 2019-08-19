package junit.com.vaadin.tutorial.flow.layout.applayout.vaadin.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.api.login.LoginService;
import org.rapidpm.event.desktop.to.web.api.login.LoginServiceProvider;
import org.rapidpm.event.desktop.to.web.api.login.SessionUser;

import java.util.List;


//TODO shift into other module to service impl
public class LoginServiceTest implements HasLogger {

  @Test
  void test001() {
    LoginService service = service();
    Assertions.assertEquals(0,service.authenticate(null, null).size());
  }

  private LoginService service() {
    return new LoginServiceProvider().load()
                                     .ifAbsent(() -> logger().warning(
                                                              "LoginService not available"))
                                     .ifAbsent(() -> {
                                                            throw new RuntimeException();
                                                          })
                                     .get();
  }

  @Test
  void test002() {
    final LoginService      service = service();
    final List<SessionUser> result  = service.authenticate("", "");
    Assertions.assertTrue(result.size() == 0);
  }

  @Test
  void test003() {
    final LoginService service = service();
    final List<SessionUser> result  = service.authenticate("xx", "xx");
    Assertions.assertTrue(result.size()==0);
  }

  @Test
  void test004() {
    final LoginService service = service();
    final List<SessionUser> result  = service.authenticate("admin", "admin");
    Assertions.assertTrue(result.size() == 1);
    final SessionUser user = result.get(0);
    Assertions.assertEquals("Jon Doe", user.getUsername());
  }
}






