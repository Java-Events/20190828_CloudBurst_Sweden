package com.vaadin.tutorial.flow.layout.applayout.vaadin.views.usermanagement;


import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.MainLayout;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.vaadin.tutorial.flow.layout.applayout.vaadin.views.usermanagement.UserManagementView.NAV;


@Route(value = NAV, layout = MainLayout.class)
public class UserManagementView
    extends Composite<HorizontalLayout> {
  public static final String NAV = "usermanagement";

  private final TextField        last_name   = new TextField("Last Name");
  private final TextField        first_name  = new TextField("First Name");
  private final TextField        phone       = new TextField("Phone");
  private final TextField        email       = new TextField("Email");
  private final TextField        addressOne  = new TextField("Address 1");
  private final TextField        addressTwo  = new TextField("Address 2");
  private final TextField        city        = new TextField("City");
  private final TextField        state       = new TextField("State");
  private final TextField        postal_code = new TextField("Postal Code");
  private final ComboBox<String> countrys    = new ComboBox<>("Country", "United States", "Russia", "New Zealand");

  private final Button           aNew       = new Button("New");
  private final Button           delete     = new Button("Delete");
  private final Button           edit       = new Button("Edit");
  private final Button           save       = new Button("Save");
  private final Button           cancel     = new Button("Cancel");
  private final HorizontalLayout userBtnBar = new HorizontalLayout(aNew, delete, edit, save, cancel);

  private final FormLayout formLayoutUser = new FormLayout(last_name, first_name, phone, email, addressOne, addressTwo,
                                                           city, state, postal_code, countrys, userBtnBar);

  private final TextField     username       = new TextField("Username");
  private final PasswordField password       = new PasswordField("Password");
  private final PasswordField password_again = new PasswordField("Password again");

  private final Button           btnPasswordNew    = new Button("New");
  private final Button           btnPasswordSave   = new Button("Save");
  private final Button           btnPasswordCancel = new Button("Cancel");
  private final HorizontalLayout passwdBtnBar      = new HorizontalLayout(btnPasswordNew, btnPasswordSave,
                                                                          btnPasswordCancel);
  private final FormLayout       formLayoutLogin   = new FormLayout(username, password, password_again, passwdBtnBar);

  private final VerticalLayout inputLayout = new VerticalLayout(formLayoutUser, formLayoutLogin);

  public UserManagementView() {

    getContent().setDefaultVerticalComponentAlignment(FlexComponent.Alignment.STRETCH);
    getContent().setAlignItems(FlexComponent.Alignment.STRETCH);
    getContent().setHeightFull();

    formLayoutLogin.setColspan(username, 2);
    formLayoutLogin.setColspan(passwdBtnBar, 2);

    formLayoutUser.setColspan(addressOne, 2);
    formLayoutUser.setColspan(addressTwo, 2);
    formLayoutUser.setColspan(userBtnBar, 2);

    inputLayout.setHeightFull();
    inputLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
    inputLayout.setAlignItems(FlexComponent.Alignment.STRETCH);


    final Grid<User> userGrid = userNameGrid();
    userGrid.setHeightFull();
    userGrid.setMinWidth("250px");
    getContent().add(userGrid, inputLayout);
    getContent().setFlexGrow(1,userGrid );
    getContent().setFlexGrow(2, inputLayout);
  }

  @NotNull
  private Grid<User> userNameGrid() {
    final Grid<User> grid = new Grid<>();

    final List<User> userList = new ArrayList<>();
    userList.add(nextUser("Hans Wurst", "von Otternase", "admin", "admin"));
    userList.add(nextUser("First B", "Family B", "user B", "passwd"));
    userList.add(nextUser("First C", "Family C", "user C", "passwd"));

    grid.setHeightByRows(false);
    grid.setVerticalScrollingEnabled(true);
    grid.setSelectionMode(Grid.SelectionMode.SINGLE);
    grid.addColumn(User::getUsername)
        .setHeader("Username")
    .setFooter("Sum: " + userList.size()); //TODO I18N

//        .setAutoWidth(true);
//    grid.addColumn(User::getLast_name)
//        .setHeader("Last Name")
//        .setAutoWidth(true);
    grid.setItems(userList);
    return grid;


  }

  @NotNull
  private User nextUser(String firstName, String lastName, String user, String password) {
    return new User(lastName, firstName, "phone", "xx.xx(a)xx.xx", "AddressOne", "AddressTwo", "City", "State", null,
                    "Country", user, password);
  }

}
