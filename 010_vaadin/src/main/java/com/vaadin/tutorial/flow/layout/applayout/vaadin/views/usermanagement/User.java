package com.vaadin.tutorial.flow.layout.applayout.vaadin.views.usermanagement;

import java.util.Objects;

public class User {

  private String last_name;
  private String first_name;
  private String phone;
  private String email;
  private String addressOne;
  private String addressTwo;
  private String city;
  private String state;
  private String postal_code;
  private String countrys;
  private String username;
  private String password;


  public User() {
  }

  public User(String last_name,
              String first_name,
              String phone,
              String email,
              String addressOne,
              String addressTwo,
              String city,
              String state,
              String postal_code,
              String countrys,
              String username,
              String password) {
    this.last_name   = last_name;
    this.first_name  = first_name;
    this.phone       = phone;
    this.email       = email;
    this.addressOne  = addressOne;
    this.addressTwo  = addressTwo;
    this.city        = city;
    this.state       = state;
    this.postal_code = postal_code;
    this.countrys    = countrys;
    this.username    = username;
    this.password    = password;
  }

  @Override
  public String toString() {
    return "User{"
           + "last_name='"
           + last_name
           + '\''
           + ", first_name='"
           + first_name
           + '\''
           + ", phone='"
           + phone
           + '\''
           + ", email='"
           + email
           + '\''
           + ", addressOne='"
           + addressOne
           + '\''
           + ", addressTwo='"
           + addressTwo
           + '\''
           + ", city='"
           + city
           + '\''
           + ", state='"
           + state
           + '\''
           + ", postal_code='"
           + postal_code
           + '\''
           + ", countrys='"
           + countrys
           + '\''
           + ", username='"
           + username
           + '\''
           + ", password='"
           + password
           + '\''
           + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(last_name, user.last_name)
           && Objects.equals(first_name, user.first_name)
           && Objects.equals(phone, user.phone)
           && Objects.equals(email, user.email)
           && Objects.equals(addressOne, user.addressOne)
           && Objects.equals(addressTwo, user.addressTwo)
           && Objects.equals(city, user.city)
           && Objects.equals(state, user.state)
           && Objects.equals(postal_code, user.postal_code)
           && Objects.equals(countrys, user.countrys)
           && Objects.equals(username, user.username)
           && Objects.equals(password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(last_name, first_name, phone, email, addressOne, addressTwo, city, state, postal_code, countrys,
                        username, password);
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddressOne() {
    return addressOne;
  }

  public void setAddressOne(String addressOne) {
    this.addressOne = addressOne;
  }

  public String getAddressTwo() {
    return addressTwo;
  }

  public void setAddressTwo(String addressTwo) {
    this.addressTwo = addressTwo;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostal_code() {
    return postal_code;
  }

  public void setPostal_code(String postal_code) {
    this.postal_code = postal_code;
  }

  public String getCountrys() {
    return countrys;
  }

  public void setCountrys(String countrys) {
    this.countrys = countrys;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
