package org.rapidpm.event.desktop.to.web.api.tasks;

import org.rapidpm.event.desktop.to.web.api.service.ServiceProvider;
import org.rapidpm.frp.model.Result;

public class TaskRepositoryProvider
    implements ServiceProvider<TaskRepository> {

  @Override
  public Result<TaskRepository> load() {
    return ServiceProvider.<TaskRepository>loadService().apply(TaskRepository.class);
  }
}