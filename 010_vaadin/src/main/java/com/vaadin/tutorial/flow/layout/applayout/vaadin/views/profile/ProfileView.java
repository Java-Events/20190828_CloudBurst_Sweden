package com.vaadin.tutorial.flow.layout.applayout.vaadin.views.profile;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(value = ProfileView.NAV)
public class ProfileView extends Composite<Div> {
  public static final String NAV = "profile";

  public ProfileView() {
    getContent().add(new Span(NAV));
  }
}