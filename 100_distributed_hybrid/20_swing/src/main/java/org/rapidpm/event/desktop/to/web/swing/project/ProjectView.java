package org.rapidpm.event.desktop.to.web.swing.project;

import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.api.tasks.Task;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskState;
import org.rapidpm.event.desktop.to.web.swing.EventType;
import org.rapidpm.frp.model.Result;

import javax.swing.*;
import java.awt.*;

import static org.rapidpm.event.desktop.to.web.swing.SwingMain.eventbusFor;
import static org.rapidpm.event.desktop.to.web.swing.SwingUtils.createButton;

public class ProjectView
    extends JPanel
    implements HasLogger {

  private final TaskTableModel       tableModel    = new TaskTableModel();
  private final JTable               table         = new JTable(tableModel);
  private final JScrollPane          scrollPane    = new JScrollPane(table);
  private final JTextField           taskNumber    = new JTextField();
  private final JTextField           projectNumber = new JTextField();
  private final JTextField           summary       = new JTextField();
  private final JTextArea            description   = new JTextArea();
  private final JComboBox<TaskState> state         = new JComboBox<>(TaskState.values());
  private final JButton buttonNew    = createButton("New");
  private final JButton buttonDelete = createButton("Delete");
  private final JButton buttonEdit   = createButton("Edit");
  private final JButton buttonSave   = createButton("Save");
  private final JButton buttonCancel = createButton("Cancel");

  public ProjectView(LayoutManager layout, boolean isDoubleBuffered) {
    super(layout, isDoubleBuffered);
    setOpaque(true);
    initUI();
  }
  public ProjectView(LayoutManager layout) {
    super(layout);
    setOpaque(true);
    initUI();
  }
  public ProjectView(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
    setOpaque(true);
    initUI();
  }
  public ProjectView() {
    super();
    setOpaque(true);
    initUI();
  }

  private void registerForProjectEvents() {
    eventbusFor(EventType.PROJECT_SELECTED).register((event) -> {
      tableModel.loadProject((String) event);
    });
    eventbusFor(EventType.PROJECT_RELOADED).register((event) -> {
      tableModel.reloadProject();
    });
    eventbusFor(EventType.PROJECT_CLOSED).register((event) -> {
      tableModel.closeProject();
      clearSwing();
    });
  }

  private void initUI() {
    registerForProjectEvents();
    initTable();
    switchButtonModes(true, false, false, false, false);
    editModeOff();

    buttonNew.addActionListener(e -> {
      clearSwing();
      table.getSelectionModel()
           .clearSelection();
      editModeOn();
      switchButtonModes(false, false, false, true, true);
    });

    buttonCancel.addActionListener(e -> {
      clearSwing();
      table.getSelectionModel()
           .clearSelection();
      editModeOff();
      switchButtonModes(true, true, true, false, false);
    });

    buttonDelete.addActionListener(e -> {
      selectedTaskFromTable().ifPresent(tableModel::removeTask);
      clearSwing();
      logger().warning("Delete Button was pressed for element " + selectedTaskFromTable());
      editModeOff();
      switchButtonModes(true, false, false, false, false);
    });

    buttonEdit.addActionListener(e -> {
      editModeOn();
      switchButtonModes(false, true, false, true, true);
    });

    buttonSave.addActionListener(e -> {
      selectedTaskFromTable().ifPresentOrElse(t -> {
        final Task task = fromSwing(t);
        tableModel.persistTask(task);
        convertToSwing(task);
      }, failed -> {
        final Task task = newFromSwing();
        tableModel.persistTask(task);
        convertToSwing(task);
      });
      editModeOff();
      switchButtonModes(true, false, true, false, false);
    });

    this.add(scrollPane, "spany, growy, wmin 150");
    add(new JLabel("ProjectNumber"));
    add(projectNumber);
    add(new JLabel("TaskNumber"));
    add(taskNumber, "wrap");

    add(new JLabel("State"));
    add(state, "wrap");
    add(new JLabel("Summary"));
    add(summary, "span, wrap");
    add(new JLabel("Descripion"));
    add(description, "span, wrap, push, grow");

    add(buttonNew, "tag other, span, split");
    add(buttonDelete, "tag other");
    add(buttonEdit, "tag other");
    add(buttonSave, "tag other");
    add(buttonCancel, "tag cancel, wrap");
  }

  private void initTable() {
    tableModel.addTableModelListener(e -> table.repaint());
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    final ListSelectionModel selectionModel = table.getSelectionModel();
    selectionModel.addListSelectionListener(e -> {
      selectedTaskFromTable().ifPresentOrElse(task -> {
        logger().info(" selected row : " + task);
        convertToSwing(task);
      }, (failed) -> {
        logger().info(" selected row : " + failed);
      });
      switchButtonModes(false, true, true, false, false);
    });
  }

  private Result<Task> selectedTaskFromTable() {
    final int selectedRow = table.getSelectedRow();
    return (selectedRow != -1)
           ? Result.ofNullable(tableModel.getRow(selectedRow))
           : Result.failure("Row index was -1");
  }


  private void editMode(Boolean b) {
    projectNumber.setEditable(false);
    taskNumber.setEditable(false);
    state.setEnabled(b);
    summary.setEditable(b);
    description.setEditable(b);
  }

  private void editModeOn() {
    editMode(Boolean.TRUE);
  }

  private void editModeOff() {
    editMode(Boolean.FALSE);
  }

  private void switchButtonModes(boolean btnNew,
                                 boolean btnDelete,
                                 boolean btnEdit,
                                 boolean btnSave,
                                 boolean btnCancel) {
    buttonNew.setEnabled(btnNew);
    buttonDelete.setEnabled(btnDelete);
    buttonEdit.setEnabled(btnEdit);
    buttonSave.setEnabled(btnSave);
    buttonCancel.setEnabled(btnCancel);
  }

  private Task newFromSwing() {
    return new Task(-1, tableModel.getActiveProject(), summary.getText(), description.getText(),
                    (TaskState) state.getSelectedItem());
  }

  private Task fromSwing(Task task) {

    try {
      task.setId(Integer.parseInt(taskNumber.getText()));
    } catch (Exception e) {
      e.printStackTrace();
      task.setId(-1);
    }
//    task.setProject(projectNumber.getText());
    task.setDescription(description.getText());
    task.setSummary(summary.getText());
    task.setState((TaskState) state.getSelectedItem());
    return task;
  }

  private void convertToSwing(Task task) {
    projectNumber.setText(task.getProject());
    taskNumber.setText(String.valueOf(task.getId()));
    state.setSelectedItem(task.getState());
    summary.setText(task.getSummary());
    description.setText(task.getDescription());
  }

  private void clearSwing() {
    projectNumber.setText("");
    taskNumber.setText("");
    state.setSelectedItem(TaskState.OPEN);
    summary.setText("");
    description.setText("");
  }
}
