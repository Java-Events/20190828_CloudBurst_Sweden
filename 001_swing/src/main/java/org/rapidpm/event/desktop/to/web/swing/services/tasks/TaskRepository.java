package org.rapidpm.event.desktop.to.web.swing.services.tasks;

import java.util.List;

public interface TaskRepository {
  Task taskFor(Integer id, String project);

  void store(Task task);

  void remove(Task task);

  List<Task> loadProject(String project);

  int elementCount();

  List<String> projects();

  int projectCount();
}
