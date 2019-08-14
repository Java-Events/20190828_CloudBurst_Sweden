package org.rapidpm.event.desktop.to.web.swing.services.tasks;

import org.rapidpm.dependencies.core.logger.HasLogger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

public class TaskRepositorySwing
    implements TaskRepository, HasLogger {

  // as Singleton as a database
  private static final Map<Key, Task> taskMap = new ConcurrentHashMap<>();
  private static AtomicInteger counter = new AtomicInteger(-1);

  @Override
  public Task taskFor(Integer id, String project) {
    return taskMap.get(new Key(id, project));
  }

  @Override
  public void store(Task task) {
    logger().info("store - " + task);

    final int id  = task.getId();
    if(id == -1) task.setId(counter.incrementAndGet());

    final Key key = new Key(task.getId(), task.getProject());
    taskMap.put(key, task);
  }

  @Override
  public void remove(Task task) {
    final Key key = new Key(task.getId(), task.getProject());
    taskMap.remove(key);
  }

  @Override
  public List<Task> loadProject(String project) {
    return taskMap.values()
                  .stream()
                  .filter(task -> task.getProject()
                                      .equals(project))
                  .collect(toList());

  }

  @Override
  public int elementCount() {
    return taskMap.size();
  }

  @Override
  public List<String> projects() {
    return taskMap.values()
                  .stream()
                  .map(Task::getProject)
                  .distinct()
                  .collect(toList());
  }

  @Override
  public int projectCount() {
    return projects().size();
  }
}