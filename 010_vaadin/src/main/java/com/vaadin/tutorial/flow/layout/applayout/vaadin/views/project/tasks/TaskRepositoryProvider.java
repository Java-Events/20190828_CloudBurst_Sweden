package com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks;

import com.vaadin.tutorial.flow.layout.applayout.vaadin.services.ServiceProvider;
import org.rapidpm.frp.model.Result;

public class TaskRepositoryProvider
    implements ServiceProvider<TaskRepository> {

  @Override
  public Result<TaskRepository> load() {
    return ServiceProvider.<TaskRepository>loadService().apply(TaskRepository.class);
  }
}