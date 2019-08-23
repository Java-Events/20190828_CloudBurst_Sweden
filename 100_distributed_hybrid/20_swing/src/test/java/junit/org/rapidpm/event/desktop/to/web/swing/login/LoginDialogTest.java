package junit.org.rapidpm.event.desktop.to.web.swing.login;

import org.assertj.swing.core.matcher.DialogMatcher;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.jupiter.api.*;
import org.rapidpm.event.desktop.to.web.swing.login.LoginDialog;

import javax.swing.*;

//@ExtendWith(ServerExtension.class)
public class LoginDialogTest {

  private DialogFixture fixture;

  @BeforeAll
  static void beforeAll() {
//    FailOnThreadViolationRepaintManager.install();
  }

  @BeforeEach
  void setUp() {
//    LoginDialog component = GuiActionRunner.execute(() -> new LoginDialog(new JFrame()));
//    fixture = new DialogFixture(component);
//    fixture.show();
  }

  @AfterEach
  void tearDown() {
//    fixture.cleanUp();
  }

  @Test
  @Disabled
  void testLogin001() {
    fixture.textBox(LoginDialog.ID_USERNAME)
           .setText("admin");
    fixture.textBox(LoginDialog.ID_PASSWORD)
           .setText("admin");
    fixture.button(LoginDialog.ID_BTN_LOGIN)
           .click();

    fixture.dialog(DialogMatcher.withTitle("Login OK"))
           .button(JButtonMatcher.withText("OK"))
           .click();
  }

  @Test
  @Disabled
  void testLogin002() {
    fixture.textBox(LoginDialog.ID_USERNAME)
           .setText("admin");
    fixture.textBox(LoginDialog.ID_PASSWORD)
           .setText("xx");
    fixture.button(LoginDialog.ID_BTN_LOGIN)
           .click();
    fixture.dialog(DialogMatcher.withTitle("Login not allowed"))
           .button(JButtonMatcher.withText("OK"))
           .click();
  }
}
