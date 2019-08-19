package org.rapidpm.event.desktop.to.web.swing;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SwingUtils {

  private SwingUtils() {
  }

  public static JButton createButton(String text) {
    return createButton(text, false);
  }

  public static JButton createButton(String text, boolean bold) {
    JButton b = new JButton(text) {
      public void addNotify() {
        super.addNotify();
        if (getText().length() == 0) {
          String lText = (String) ((MigLayout) getParent().getLayout()).getComponentConstraints(this);
          setText(lText != null && lText.length() > 0
                  ? lText
                  : "<Empty>");
        }
      }
    };

    if (bold) b.setFont(b.getFont()
                         .deriveFont(Font.BOLD));
    b.setOpaque(true); // Or window's buttons will have strange border
    b.setContentAreaFilled(true);

    return b;
  }

  public static MigLayout defaultLayoutManager() {
    return new MigLayout("", "[]15[][grow,fill]15[][grow,fill]");
  }

}
