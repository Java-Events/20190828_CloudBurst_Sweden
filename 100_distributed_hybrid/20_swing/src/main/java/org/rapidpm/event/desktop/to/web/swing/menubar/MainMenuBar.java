package org.rapidpm.event.desktop.to.web.swing.menubar;

import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepository;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepositoryProvider;
import org.rapidpm.event.desktop.to.web.swing.EventType;
import org.rapidpm.event.desktop.to.web.swing.SwingMain;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showConfirmDialog;
import static org.rapidpm.event.desktop.to.web.swing.SwingMain.eventbusFor;

public class MainMenuBar
    extends JMenuBar
    implements HasLogger {


  private final TaskRepository repository = new TaskRepositoryProvider().load()
                                                                        .ifAbsent(() -> logger().warning(
                                                                            "TaskRepository could not be loaded"))
                                                                        .ifAbsent(() -> {
                                                                          throw new RuntimeException(
                                                                              "no TaskRepository Implementation");
                                                                        })
                                                                        .get();

  public MainMenuBar() throws HeadlessException {
    this.add(menuTasks());
    this.add(menuHelp());
    this.add(logOut());
  }

  private Component logOut() {
    final JMenuItem jMenuItem = new JMenuItem("LogOut");
    jMenuItem.addActionListener(e -> {
      //Typical Swing static pattern
      int input = showConfirmDialog(SwingMain.instance(), "Are you sure you are done with your work?",
                                    "Logout Confirmation", WARNING_MESSAGE);
      if (input == 0) {
        logger().info("Yes the work is done for today.");
        System.exit(0);
      }

      if (input == 2) {
        logger().warning("Still stuff to do.. back to work");
      }
    });
    return jMenuItem;
  }

  private JMenu menuHelp() {
    JMenu helpMenu = new JMenu("Help");
    return helpMenu;
  }

  private JMenu menuTasks() {
    JMenuItem openProject = new JMenuItem("Open Project");
    openProject.addActionListener(e -> {
      List<String> projects = repository.projects();
      Collections.sort(projects);
      final Object selectedProject = JOptionPane.showInputDialog(SwingMain.instance(), "Please Select a project",
                                                                 "Project Selector", JOptionPane.INFORMATION_MESSAGE,
                                                                 null, projects.toArray(), projects.get(0));
      eventbusFor(EventType.PROJECT_SELECTED).fireMessageEvent(selectedProject);
    });

    JMenuItem reloadProject = new JMenuItem("Reload Project");
    reloadProject.addActionListener(e -> eventbusFor(EventType.PROJECT_RELOADED).fireMessageEvent("reload"));
    JMenuItem closeProject = new JMenuItem("Close Project");
    closeProject.addActionListener(e -> {
      eventbusFor(EventType.PROJECT_CLOSED).fireMessageEvent("close");
    });

    JMenu tasksMenu = new JMenu("Projects");
    tasksMenu.add(openProject);
    tasksMenu.add(reloadProject);
    tasksMenu.add(closeProject);

    return tasksMenu;
  }

}
