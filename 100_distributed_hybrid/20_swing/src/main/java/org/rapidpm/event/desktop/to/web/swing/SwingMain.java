package org.rapidpm.event.desktop.to.web.swing;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.api.Constants;
import org.rapidpm.event.desktop.to.web.swing.login.LoginDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;
import static java.util.Arrays.stream;
import static javax.swing.SwingUtilities.invokeLater;

public class SwingMain
    implements HasLogger {

  public static final String CLI_HOST = Constants.HOST;
  public static final String CLI_PORT = Constants.PORT;

  private static final Map<EventType, EventBus<?>> EVENT_BUS_MAP = new ConcurrentHashMap<>();
  //Typical Swing static pattern - remove for web !!

  private static SwingMainFrame frame;

  private SwingMain() {
  }

  public static <T> EventBus<T> eventbusFor(EventType eventType) {
    return (EventBus<T>) EVENT_BUS_MAP.get(eventType);
  }

  private static void initEventBusMap() {
    EVENT_BUS_MAP.clear();
    stream(EventType.values()).forEach(et -> EVENT_BUS_MAP.put(et, new EventBus<>()));
  }

  public static SwingMainFrame instance() {
    return frame;
  }

  public static void main(String[] args) throws ParseException {

    configBackendCoordinates(args);
    System.out.println(Constants.HOST +  " = " + getProperty(Constants.HOST));
    System.out.println(Constants.PORT + " = " + getProperty(Constants.PORT));

    initEventBusMap();

    invokeLater(() -> {
      final SwingMain swingMain = new SwingMain();
      swingMain.startLoginProcess();
      swingMain.initUI();
    });
  }

  private static void configBackendCoordinates(String[] args) throws ParseException {
    setProperty(CLI_HOST, "127.0.0.1");
    setProperty(CLI_PORT, "7000");

    final Options options = new Options();
    options.addOption(CLI_HOST, true, "host to use");
    options.addOption(CLI_PORT, true, "port to use");

    DefaultParser parser = new DefaultParser();
    CommandLine   cmd    = parser.parse(options, args);
    if (cmd.hasOption(CLI_HOST)) {
      setProperty(CLI_HOST, cmd.getOptionValue(CLI_HOST));
    }
    if (cmd.hasOption(CLI_PORT)) {
      setProperty(CLI_PORT, cmd.getOptionValue(CLI_PORT));
    }
  }

  private void initUI() {
    frame = new SwingMainFrame("Swing Application");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.setSize(1024, 768);
    frame.setPreferredSize(new Dimension(1024, 768));
    Dimension dimension = Toolkit.getDefaultToolkit()
                                 .getScreenSize();
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