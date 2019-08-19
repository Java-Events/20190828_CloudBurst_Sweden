package org.rapidpm.event.desktop.to.web.swing;

import org.rapidpm.event.desktop.to.web.swing.menubar.MainMenuBar;
import org.rapidpm.event.desktop.to.web.swing.project.ProjectView;
import org.rapidpm.event.desktop.to.web.swing.usermanagement.UserManagementView;

import javax.swing.*;
import java.awt.*;

import static org.rapidpm.event.desktop.to.web.swing.SwingMain.eventbusFor;
import static org.rapidpm.event.desktop.to.web.swing.SwingUtils.defaultLayoutManager;

public class SwingMainFrame
    extends JFrame {


  private final JMenuBar           mainMenu       = new MainMenuBar();
  private final UserManagementView usermanagement = new UserManagementView(defaultLayoutManager());
  private final ProjectView        projectView    = new ProjectView(defaultLayoutManager());
  private final JTabbedPane        workingArea    = new JTabbedPane();


  public SwingMainFrame(String title) throws HeadlessException {
    super(title);
  }

  public void initUI() {
    setJMenuBar(mainMenu);
    setContentPane(workingArea());

    eventbusFor(EventType.PROJECT_SELECTED).register((event) -> {
      workingArea.setEnabledAt(1, true);
      workingArea.setTitleAt(1, "Project: " + event);
    });

    eventbusFor(EventType.PROJECT_CLOSED).register((event) -> {
      workingArea.setEnabledAt(1, false);
      workingArea.setTitleAt(1, "Project: closed");
    });
  }

  //MainView - composition of the working area
  private JTabbedPane workingArea() {
    workingArea.addTab("Usermanagement", usermanagement);
    workingArea.setEnabledAt(0, true);

    workingArea.addTab("Project: closed", projectView);
    workingArea.setEnabledAt(1, false);


    return workingArea;
  }

  public void addComponent(Component c, String name) {
    getContentPane().add(c, name);
  }


}
