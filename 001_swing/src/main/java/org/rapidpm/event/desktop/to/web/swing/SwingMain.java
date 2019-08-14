package org.rapidpm.event.desktop.to.web.swing;

import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.swing.login.LoginDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.stream;
import static javax.swing.SwingUtilities.invokeLater;

public class SwingMain
    implements HasLogger {

  private SwingMain() {
  }

  private static final Map<EventType, EventBus<?>> eventBusMap = new ConcurrentHashMap<>();

  public static <T> EventBus<T> eventbusFor(EventType eventType) {
    return (EventBus<T>) eventBusMap.get(eventType);
  }

  private static void initEventBusMap() {
    eventBusMap.clear();
    stream(EventType.values()).forEach(et -> eventBusMap.put(et, new EventBus<>()));
  }

  //Typical Swing static pattern - remove for web !!
  private static SwingMainFrame frame;

  public static SwingMainFrame instance() {
    return frame;
  }

  public static void main(String[] args) {
    initEventBusMap();

    DemoDataGenerator.generateData();

    invokeLater(() -> {
      final SwingMain swingMain = new SwingMain();
//      swingMain.startLoginProcess();
      swingMain.initUI();
    });
  }

  private void initUI() {
    frame = new SwingMainFrame("Swing Application");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.setSize(1024, 768);
    frame.setPreferredSize(new Dimension(1024, 768));
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
    frame.initUI();
    frame.pack();
    frame.setVisible(true);
  }

  private void startLoginProcess() {
    final LoginDialog loginDialog = new LoginDialog(frame);
    loginDialog.setVisible(true);
    if (loginDialog.isSucceeded()) {
      logger().info("Login process was successfully.");
    } else {
      logger().warning("Login Process canceled. Will shut down now.");
      System.exit(0);
    }
  }


}