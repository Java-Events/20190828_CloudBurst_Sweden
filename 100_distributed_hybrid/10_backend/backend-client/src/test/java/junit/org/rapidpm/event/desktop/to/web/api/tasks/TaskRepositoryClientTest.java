package junit.org.rapidpm.event.desktop.to.web.api.tasks;

import junit.org.rapidpm.event.desktop.to.web.api.ServerExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rapidpm.event.desktop.to.web.api.tasks.Task;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepositoryProvider;

import java.util.List;

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
}
