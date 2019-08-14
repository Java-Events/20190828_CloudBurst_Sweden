package org.rapidpm.event.desktop.to.web.swing.login;

import net.miginfocom.swing.MigLayout;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.swing.services.login.LoginService;
import org.rapidpm.event.desktop.to.web.swing.services.login.LoginServiceProvider;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.*;

public class LoginDialog
    extends JDialog
    implements HasLogger {

  private JTextField     tfUsername = new JTextField();
  private JPasswordField pfPassword = new JPasswordField();
  private JButton        btnLogin   = new JButton();
  private JButton        btnCancel  = new JButton();
  private boolean        succeeded;

  private final LoginService loginService = new LoginServiceProvider().load()
                                                                      .ifAbsent(() -> logger().warning(
                                                                          "LoginService not available"))
                                                                      .ifAbsent(() -> {
                                                                        throw new RuntimeException();
                                                                      })
                                                                      .get();
  public LoginDialog(Frame parent) {
    super(parent, "Login", true);
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
      if (loginService.authenticate(getUsername(), getPassword())) {
        showMessageDialog(LoginDialog.this, "Hi " + getUsername() + "! You have successfully logged in.", "Login",
                          INFORMATION_MESSAGE);
        succeeded = true;
        dispose();
      } else {
        showMessageDialog(LoginDialog.this, "Invalid username or password", "Login", ERROR_MESSAGE);
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
