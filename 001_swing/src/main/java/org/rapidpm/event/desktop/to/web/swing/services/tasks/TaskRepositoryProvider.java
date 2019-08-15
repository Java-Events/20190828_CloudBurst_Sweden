package org.rapidpm.event.desktop.to.web.swing.services.tasks;

import org.rapidpm.event.desktop.to.web.swing.services.ServiceProvider;
import org.rapidpm.frp.model.Result;

public class TaskRepositoryProvider
    implements ServiceProvider<TaskRepository> {

  @Override
  public Result<TaskRepository> load() {
    return ServiceProvider.<TaskRepository>loadService().apply(TaskRepository.class);
  }
}