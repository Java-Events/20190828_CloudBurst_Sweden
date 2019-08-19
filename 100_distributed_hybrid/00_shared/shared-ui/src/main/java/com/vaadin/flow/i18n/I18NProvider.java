package com.vaadin.flow.i18n;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

//TODO dirty but goood ;-)
public interface I18NProvider extends Serializable {
  List<Locale> getProvidedLocales();

  String getTranslation(String var1, Locale var2, Object... var3);
}
