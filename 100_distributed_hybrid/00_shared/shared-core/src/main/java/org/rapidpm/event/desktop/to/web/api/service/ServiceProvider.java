package org.rapidpm.event.desktop.to.web.api.service;

import org.rapidpm.frp.model.Result;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public interface ServiceProvider<T> {
  static <T> Function<Class<T>, Result<T>> loadService() {
    return (service) -> {
      Iterator<T> iterator = ServiceLoader.load(service)
                                          .iterator();
      Iterable<T> iterable = () -> iterator;
      return Result.fromOptional(StreamSupport.stream(iterable.spliterator(), false)
                                              .findFirst());
    };
  }

  Result<T> load();
}
