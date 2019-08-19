package org.rapidpm.event.desktop.to.web.swing.login;

import net.miginfocom.swing.MigLayout;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.api.login.LoginService;
import org.rapidpm.event.desktop.to.web.api.login.LoginServiceProvider;
import org.rapidpm.event.desktop.to.web.api.login.SessionUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.JOptionPane.*;

public class LoginDialog
    extends JDialog
    implements HasLogger {

  public static final String ID_USERNAME   = "username";
  public static final String ID_PASSWORD   = "password";
  public static final String ID_BTN_LOGIN  = "btnLogin";
  public static final String ID_BTN_CANCEL = "btnCancel";

  private final LoginService   loginService = new LoginServiceProvider().load()
                                                                        .ifAbsent(() -> logger().warning(
                                                                            "LoginService not available"))
                                                                        .ifAbsent(() -> {
                                                                          throw new RuntimeException();
                                                                        })
                                                                        .get();
  private       JTextField     tfUsername   = new JTextField();
  private       JPasswordField pfPassword   = new JPasswordField();
  private       JButton        btnLogin     = new JButton();
  private       JButton        btnCancel    = new JButton();
  private       boolean        succeeded;

  public LoginDialog(Frame parent) {
    super(parent, "Login", true);

    tfUsername.setName(ID_USERNAME);
    pfPassword.setName(ID_PASSWORD);
    btnLogin.setName(ID_BTN_LOGIN);
    btnCancel.setName(ID_BTN_CANCEL);
    //
    setLayout(new MigLayout("", "[grow]", "[grow]"));
    add(new JLabel("Username:"), "right");
    add(tfUsername, "growx, left, wrap, w 100");
    add(new JLabel("Password:"), "right");
    add(pfPassword, "growx, left, wrap, w 100");
//    add(new JCheckBox("Remember Me"), "center, wrap, span");
    add(btnLogin, "split 2, span 2, center");
//    add(btnCancel, "split 2, span 2, center");
    add(btnCancel);

    btnLogin.setText("Login");
    btnLogin.addActionListener(e -> {
      final List<SessionUser> authenticate = loginService.authenticate(getUsername(), getPassword());
      if (authenticate.size() == 1) {
        showMessageDialog(LoginDialog.this, "Hi " + getUsername() + "! You have successfully logged in.", "Login OK",
                          INFORMATION_MESSAGE);
        succeeded = true;
        dispose();
      } else {
        showMessageDialog(LoginDialog.this, "Invalid username or password", "Login not allowed", ERROR_MESSAGE);
        // reset username and password
        tfUsername.setText("");
        pfPassword.setText("");
        succeeded = false;

      }
    });

    btnCancel.setText("Cancel");
    btnCancel.addActionListener(e -> dispose());

    pack();
    setResizable(false);
    setLocationRelativeTo(parent);
  }

  public String getUsername() {
    return tfUsername.getText()
                     .trim();
  }

  public String getPassword() {
    return new String(pfPassword.getPassword());
  }

  public boolean isSucceeded() {
    return succeeded;
  }
}
