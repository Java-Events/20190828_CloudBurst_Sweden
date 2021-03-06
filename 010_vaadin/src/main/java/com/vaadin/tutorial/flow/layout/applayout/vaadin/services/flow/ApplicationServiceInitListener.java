package com.vaadin.tutorial.flow.layout.applayout.vaadin.services.flow;

import static com.vaadin.tutorial.flow.layout.applayout.vaadin.views.login.LoginView.NAV_LOGIN_VIEW;
import static java.lang.System.setProperty;

import com.vaadin.tutorial.flow.layout.applayout.vaadin.services.I18NProviderImpl;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.services.User;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.model.Result;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.UIInitListener;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;

public class ApplicationServiceInitListener implements VaadinServiceInitListener, HasLogger {

  @Override
  public void serviceInit(ServiceInitEvent e) {

    setProperty("vaadin.i18n.provider", I18NProviderImpl.class.getName());

    e.getSource()
     .addUIInitListener((UIInitListener) uiInitEvent -> {
       logger().info("init SecurityListener for .. " + uiInitEvent.getUI());
       uiInitEvent.getUI().addBeforeEnterListener(new SecurityListener());
     });
  }

  private static class SecurityListener implements BeforeEnterListener, HasLogger {
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
      final UI ui = UI.getCurrent();
      final VaadinSession vaadinSession = ui.getSession();

      Result.ofNullable(vaadinSession
                            .getAttribute(User.class))
            .ifPresentOrElse(u -> {
                               logger().info("User is logged in : " + u);
                             } ,
                             failed -> {
                               logger().info("Anonymous User: redirecting to Login View");
                               if (! beforeEnterEvent.getNavigationTarget().equals(LoginView.class))
                                 beforeEnterEvent.rerouteTo(NAV_LOGIN_VIEW);
                             });

    }
  }
}
