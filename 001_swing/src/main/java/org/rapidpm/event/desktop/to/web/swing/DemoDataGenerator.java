package org.rapidpm.event.desktop.to.web.swing;

import org.rapidpm.event.desktop.to.web.swing.services.tasks.Task;
import org.rapidpm.event.desktop.to.web.swing.services.tasks.TaskRepository;
import org.rapidpm.event.desktop.to.web.swing.services.tasks.TaskRepositoryProvider;
import org.rapidpm.event.desktop.to.web.swing.services.tasks.TaskState;

public class DemoDataGenerator {

  private static final TaskRepository taskRepository = new TaskRepositoryProvider().load()
                                                                        .ifAbsent(() -> {
                                                                          throw new RuntimeException(
                                                                              "no TaskRepository Implementation");
                                                                        })
                                                                        .get();




  public static void generateData(){

    taskRepository.store(new Task(-1, "project A", "Task A", "Description A", TaskState.OPEN));
    taskRepository.store(new Task(-1, "project A", "Task B", "Description B", TaskState.ON_GOING));
    taskRepository.store(new Task(-1, "project A", "Task C", "Description C", TaskState.DONE));
    taskRepository.store(new Task(-1, "project A", "Task D", "Description D", TaskState.ON_GOING));
    taskRepository.store(new Task(-1, "project A", "Task E", "Description E", TaskState.OPEN));

  }

}
