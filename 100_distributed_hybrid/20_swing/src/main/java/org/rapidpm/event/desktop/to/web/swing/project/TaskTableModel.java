package org.rapidpm.event.desktop.to.web.swing.project;

import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.event.desktop.to.web.api.tasks.Task;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepository;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepositoryProvider;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskState;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskTableModel
    extends AbstractTableModel
    implements HasLogger {
  private final String[]       columnNames = {
//      "Task Nr", "Project Nr", "Description", "Summary", "State"
"Task Nr", "State", "Summary"
  };
  private final TaskRepository repository  = new TaskRepositoryProvider().load()
                                                                         .ifAbsent(() -> logger().warning(
                                                                             "TaskRepository could not be loaded"))
                                                                         .ifAbsent(() -> {
                                                                           throw new RuntimeException(
                                                                               "no TaskRepository Implementation");
                                                                         })
                                                                         .get();

  //not effective - waste of memory
  private final ArrayList<Task> tasks = new ArrayList<>();

  private String activeProject = "closed";

  public String getActiveProject() {
    return activeProject;
  }

  public void loadProject(String project) {
    tasks.clear();
    activeProject = project;
    refreshData();
  }

  public void closeProject() {
    tasks.clear();
  }

  public void reloadProject() {
    loadProject(activeProject);
  }

  @Override
  public int getRowCount() {
    return repository.elementCount();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  public Task getRow(int rowIndex) {
    return tasks.get(rowIndex);
  }

  public void persistTask(Task task) {
    repository.store(task);
    refreshData();
  }

  private void refreshData() {
    final List<Task> tasks = repository.loadProject(activeProject);
    tasks.sort(Comparator.comparingInt(Task::getId));
    this.tasks.addAll(tasks);
    fireTableDataChanged();
  }

  public void removeTask(Task task) {
    repository.remove(task);
    refreshData();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (tasks.isEmpty()) return null;

    final Task task = tasks.get(rowIndex);
    if (columnIndex == 0) return task.getId();
    if (columnIndex == 1) return task.getState();
    if (columnIndex == 2) return task.getSummary();
//    if (columnIndex == 1) return task.getProject();
//    if (columnIndex == 2) return task.getDescription();
    throw new RuntimeException("shit happend");
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    if (columnIndex == 0) return Integer.class;
    if (columnIndex == 1) return String.class;
    if (columnIndex == 2) return TaskState.class;
//    if (columnIndex == 2) return String.class;
//    if (columnIndex == 3) return String.class;
    throw new RuntimeException("shit happend");
  }
}
