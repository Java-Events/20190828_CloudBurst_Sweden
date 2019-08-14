package org.rapidpm.event.desktop.to.web.swing;

import org.rapidpm.dependencies.core.logger.HasLogger;

import java.util.Set;
import java.util.function.Consumer;

import static java.util.concurrent.ConcurrentHashMap.newKeySet;

public class EventBus<T>
    implements HasLogger {

  public interface Registration {
    void remove();
  }

  private Set<Consumer<T>> listeners = newKeySet();

  public Registration register(Consumer<T> listener) {
    listeners.add(listener);
    return () -> listeners.remove(listener);
  }

  public void fireMessageEvent(T event) {
    logger().info("fireMessageEvent - " + event);
    listeners.forEach(c -> c.accept(event));
  }

}
