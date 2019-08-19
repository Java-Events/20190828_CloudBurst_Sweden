package com.vaadin.tutorial.flow.layout.applayout.vaadin.services;

import org.rapidpm.frp.model.serial.Pair;

import java.time.LocalDateTime;

public class User
    extends Pair<String, LocalDateTime> {
  public User(String name , LocalDateTime timestamp) {
    super(name , timestamp);
  }
}
