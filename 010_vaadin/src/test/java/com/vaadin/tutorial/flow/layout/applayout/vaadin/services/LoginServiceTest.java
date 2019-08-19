package junit.com.vaadin.tutorial.flow.layout.applayout.vaadin.services;

import com.vaadin.tutorial.flow.layout.applayout.vaadin.services.LoginService;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.services.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Result;

public class LoginServiceTest {

  @Test
  void test001() {
    final LoginService service = new LoginService();
    Assertions.assertThrows(NullPointerException.class, () -> {
      final Result<User> result = service.authenticate(null, null);
    });
  }

  @Test
  void test002() {
    final LoginService service = new LoginService();
    final Result<User> result  = service.authenticate("", "");
    Assertions.assertTrue(result.isAbsent());
  }

  @Test
  void test003() {
    final LoginService service = new LoginService();
    final Result<User> result  = service.authenticate("xx", "xx");
    Assertions.assertTrue(result.isAbsent());
  }

  @Test
  void test004() {
    final LoginService service = new LoginService();
    final Result<User> result  = service.authenticate("admin", "admin");
    Assertions.assertTrue(result.isPresent());
    final User user = result.get();
    Assertions.assertEquals("Jon Doe", user.getT1());
  }
}






