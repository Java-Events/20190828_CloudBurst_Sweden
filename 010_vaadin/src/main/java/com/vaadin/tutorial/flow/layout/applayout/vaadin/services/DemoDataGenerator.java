package com.vaadin.tutorial.flow.layout.applayout.vaadin.services;


import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks.Task;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks.TaskRepository;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks.TaskRepositoryProvider;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks.TaskState;

public class DemoDataGenerator {

  private DemoDataGenerator() {
  }

  private static final TaskRepository TASK_REPOSITORY = new TaskRepositoryProvider().load()
                                                                                    .ifAbsent(() -> {
                                                                                     throw new RuntimeException(
                                                                                         "no TaskRepository Implementation");
                                                                                   })
                                                                                    .get();


  public static void generateData() {

    TASK_REPOSITORY.store(new Task(-1, "project A", "Task A", "Description A", TaskState.OPEN));
    TASK_REPOSITORY.store(new Task(-1, "project A", "Task B", "Description B", TaskState.ON_GOING));
    TASK_REPOSITORY.store(new Task(-1, "project A", "Task C", "Description C", TaskState.DONE));
    TASK_REPOSITORY.store(new Task(-1, "project A", "Task D", "Description D", TaskState.ON_GOING));
    TASK_REPOSITORY.store(new Task(-1, "project A", "Task E", "Description E", TaskState.OPEN));

  }

}
