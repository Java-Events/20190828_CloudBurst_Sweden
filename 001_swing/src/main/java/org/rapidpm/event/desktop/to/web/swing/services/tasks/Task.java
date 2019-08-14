package org.rapidpm.event.desktop.to.web.swing.services.tasks;

import java.util.Objects;

public class Task {

  private int       id;
  private String    project;
  private String    summary;
  private String    description;
  private TaskState state;

  public Task() {
  }

  public Task(int id, String project, String summary, String description, TaskState state) {
    this.id          = id;
    this.project     = project;
    this.summary     = summary;
    this.description = description;
    this.state       = state;
  }

  @Override
  public String toString() {
    return "Task{"
           + "id="
           + id
           + ", project='"
           + project
           + '\''
           + ", summary='"
           + summary
           + '\''
           + ", description='"
           + description
           + '\''
           + ", state="
           + state
           + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Task)) return false;
    Task task = (Task) o;
    return id == task.id && project.equals(task.project);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, project);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskState getState() {
    return state;
  }

  public void setState(TaskState state) {
    this.state = state;
  }
}
