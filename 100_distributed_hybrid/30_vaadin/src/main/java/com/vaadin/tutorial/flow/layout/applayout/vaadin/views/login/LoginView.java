/**
 * Copyright © 2018 Sven Ruppert (sven.ruppert@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vaadin.tutorial.flow.layout.applayout.vaadin.views.login;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.main.MainView;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.api.login.LoginService;
import org.rapidpm.event.desktop.to.web.api.login.LoginServiceProvider;
import org.rapidpm.event.desktop.to.web.api.login.SessionUser;

import java.util.List;

import static com.vaadin.flow.theme.lumo.Lumo.DARK;
import static com.vaadin.flow.theme.lumo.Lumo.LIGHT;
import static com.vaadin.tutorial.flow.layout.applayout.vaadin.views.login.LoginView.NAV_LOGIN_VIEW;

@Route(NAV_LOGIN_VIEW)
@Theme(value = Lumo.class)
public class LoginView
    extends Composite<HorizontalLayout>
    implements HasLogger {

  public static final String NAV_LOGIN_VIEW = "login";

  public static final String USERNAME   = "username";
  public static final String PASSWORD   = "password";
  public static final String BTN_LOGIN  = "btnLogin";
  public static final String BTN_CANCEL = "btnCancel";


  private final TextField     username   = new TextField();
  private final PasswordField password   = new PasswordField();
  private final Checkbox      rememberMe = new Checkbox();
  private final Button        btnLogin   = new Button();
  private final Button        btnCancel  = new Button();

  private final RadioButtonGroup<String> switchTheme = new RadioButtonGroup<>();

  private final LoginService loginService = new LoginServiceProvider().load()
                                                                      .ifAbsent(() -> logger().warning(
                                                                          "LoginService not available"))
                                                                      .ifAbsent(() -> {
                                                                        throw new RuntimeException();
                                                                      })
                                                                      .get();

  public LoginView() {

    HorizontalLayout input = new HorizontalLayout(username, password);
//    VerticalLayout   input   = new VerticalLayout(username, password);
    HorizontalLayout buttons = new HorizontalLayout(btnLogin, btnCancel);
    VerticalLayout   groupV  = new VerticalLayout(input, new HorizontalLayout(rememberMe, switchTheme), buttons);
//    VerticalLayout groupV = new VerticalLayout(input, buttons);
    groupV.setDefaultHorizontalComponentAlignment(Alignment.START);
    groupV.setSizeUndefined();

    //I18N
    username.setId(USERNAME);
    username.setPlaceholder(getTranslation("login.username.placeholder"));
    username.setValue("admin");
    username.setLabel(getTranslation("login.username.placeholder"));
    username.setClearButtonVisible(true);

    password.setId(PASSWORD);
    password.setPlaceholder(getTranslation("login.password.placeholder"));
    password.setValue("admin");
    password.setLabel(getTranslation("login.password.placeholder"));
    password.setClearButtonVisible(true);

    btnLogin.setId(BTN_LOGIN);
    btnLogin.setText(getTranslation("login.button.ok.text"));

    btnCancel.setId(BTN_CANCEL);
    btnCancel.setText(getTranslation("login.button.cancel.text"));

    HorizontalLayout content = getContent();

    content.setDefaultVerticalComponentAlignment(Alignment.CENTER);
    content.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    content.setSizeFull();
    content.add(groupV);

    //Actions
    btnCancel.addClickListener(e -> {
      username.clear();
      password.clear();
      rememberMe.clear();
    });

    btnLogin.addClickListener(e -> {
      final List<SessionUser> authenticate = loginService.authenticate(username.getValue(), password.getValue());
      if((authenticate.size() == 1)) {
        UI.getCurrent()
          .getSession()
          .setAttribute(SessionUser.class, authenticate.get(0));
        UI.getCurrent()
          .navigate(MainView.class);
      } else {
        UI.getCurrent()
          .getSession()
          .setAttribute(SessionUser.class, null);
        logger().info("login failed..");
      }
    });

    rememberMe.setLabel(getTranslation("login.rememberme.label"));
    switchTheme.setItems(LIGHT, DARK);
    switchTheme.setValue(LIGHT);
    switchTheme.addValueChangeListener(event -> {
      getUI().ifPresent(ui -> {
        String value = event.getValue();
        logger().info("switchTheme value - " + value);
        Page page = ui.getPage();
        page.executeJavaScript("document.documentElement.setAttribute(\"theme\",\" " + value + "\")");
      });
    });
  }

}
