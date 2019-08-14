package org.rapidpm.event.desktop.to.web.swing.services.tasks;

import java.util.Objects;

public class Key {
  private int    taskID;
  private String project;

  public Key(int taskID, String project) {
    this.taskID  = taskID;
    this.project = project;
  }

  @Override
  public String toString() {
    return "Key{" + "taskID=" + taskID + ", project='" + project + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Key)) return false;
    Key key = (Key) o;
    return taskID == key.taskID && project.equals(key.project);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskID, project);
  }
}
