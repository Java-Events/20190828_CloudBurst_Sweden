package junit.org.rapidpm.event.desktop.to.web.api.tasks;

import junit.org.rapidpm.event.desktop.to.web.api.ServerExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rapidpm.event.desktop.to.web.api.tasks.Task;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepository;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepositoryProvider;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskState;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(ServerExtension.class)
public class TaskRepositoryClientTest {


  @Test
  void test001() {
    final Task task = new TaskRepositoryProvider().load()
                                                  .get()
                                                  .taskFor(3, "project A");
    Assertions.assertTrue(task != null);
    Assertions.assertEquals(3, task.getId());
    Assertions.assertEquals("project A", task.getProject());
  }

  @Test
  void test002() {
    final List<Task> tasks = new TaskRepositoryProvider().load()
                                                         .get()
                                                         .loadProject("project A");
    Assertions.assertTrue(tasks != null);
    Assertions.assertFalse(tasks.isEmpty());

  }

  @Test
  void test003() {
    final TaskRepository taskRepository = new TaskRepositoryProvider().load()
                                                                      .get();
    final String projectName = "project A";
    final String summary     = "AEAEAE";
    final String description = "EAEAEA";
    final Task   task        = new Task(-1, projectName, summary, description, TaskState.DONE);
    taskRepository.store(task);

    final List<Task> taskList = taskRepository.loadProject(projectName)
                                              .stream()
                                              .filter(t -> t.getSummary()
                                                            .contains(summary))
                                              .collect(Collectors.toList());
    Assertions.assertEquals(1, taskList.size());
    Assertions.assertEquals(description, taskList.get(0)
                                                 .getDescription());
  }

  @Test
  void test004() {
    final TaskRepository taskRepository = new TaskRepositoryProvider().load()
                                                                      .get();
    final String projectName = "project A";

    final List<Task> tasksOne = taskRepository.loadProject(projectName);

    final Task task = tasksOne.get(0);
    taskRepository.remove(task);

    final List<Task> tasksTwo = taskRepository.loadProject(projectName);
    Assertions.assertFalse(tasksTwo.contains(task));
    Assertions.assertNotEquals(tasksOne.size(), tasksTwo.size());
  }
}
