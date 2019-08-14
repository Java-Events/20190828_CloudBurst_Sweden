package org.rapidpm.event.desktop.to.web.swing.usermanagement;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static org.rapidpm.event.desktop.to.web.swing.SwingUtils.createButton;


public class UserManagementView
    extends JPanel {

  public UserManagementView(MigLayout lm) {
    super(lm);
    setOpaque(true); //Something for a parent class or factory
    initUI();
  }

  private void initUI() {
    JScrollPane list2 = new JScrollPane(new JList<>(new String[]{"Mouse, Mickey"}));

    this.add(list2, "spany, growy, wmin 150");
    this.add(new JLabel("Last Name"));
    this.add(new JTextField());
    this.add(new JLabel("First Name"));
    this.add(new JTextField(), "wrap");

    this.add(new JLabel("Phone"));
    this.add(new JTextField());
    this.add(new JLabel("Email"));
    this.add(new JTextField(), "wrap");

    this.add(new JLabel("Address 1"));
    this.add(new JTextField(), "span");
    this.add(new JLabel("Address 2"));
    this.add(new JTextField(), "span");

    this.add(new JLabel("City"));
    this.add(new JTextField(), "wrap");

    this.add(new JLabel("State"));
    this.add(new JTextField());
    this.add(new JLabel("Postal Code"));
    this.add(new JTextField(10), "growx 0, wrap");
    this.add(new JLabel("Country"));
    this.add(new JComboBox<>(new String[]{"United States", "Russia", "New Zealand"}), "wrap");

    this.add(createButton("New"), "tag other, span, split");
    this.add(createButton("Delete"), "tag other");
    this.add(createButton("Edit"), "tag other");
    this.add(createButton("Save"), "tag other");
//    this.add(createButton("Cancel"), "tag cancel, wrap push");
    this.add(createButton("Cancel"), "tag cancel, wrap 15");

    //login infos
    this.add(new JLabel("Username"));
    this.add(new JTextField(), "wrap");

    this.add(new JLabel("Password"));
    this.add(new JPasswordField(), " growy, wmin 150");
    this.add(new JLabel("Password again"));
    this.add(new JPasswordField(), "wrap , growy, wmin 150");

    this.add(createButton("New"), "tag other, span, split");
    this.add(createButton("Save"), "tag other");
    this.add(createButton("Cancel"), "tag cancel, wrap push");

  }
}
