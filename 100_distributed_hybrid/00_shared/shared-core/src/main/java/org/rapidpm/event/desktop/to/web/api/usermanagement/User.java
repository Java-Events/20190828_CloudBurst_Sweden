package org.rapidpm.event.desktop.to.web.api.usermanagement;

import java.util.Objects;

public class User {

  private String lastName;
  private String firstName;
  private String phone;
  private String email;
  private String addressOne;
  private String addressTwo;
  private String city;
  private String state;
  private String postalCode;
  private String country;


  private String username;
  private String password;

  public User() {
  }

  public User(String lastName,
              String firstName,
              String phone,
              String email,
              String addressOne,
              String addressTwo,
              String city,
              String state,
              String postalCode,
              String country,
              String username,
              String password) {
    this.lastName   = lastName;
    this.firstName  = firstName;
    this.phone      = phone;
    this.email      = email;
    this.addressOne = addressOne;
    this.addressTwo = addressTwo;
    this.city       = city;
    this.state      = state;
    this.postalCode = postalCode;
    this.country    = country;
    this.username   = username;
    this.password   = password;
  }

  @Override
  public String toString() {
    return "User{"
           + "lastName='"
           + lastName
           + '\''
           + ", firstName='"
           + firstName
           + '\''
           + ", phone='"
           + phone
           + '\''
           + ", emial='"
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
           + ", postalCode='"
           + postalCode
           + '\''
           + ", country='"
           + country
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
    return lastName.equals(user.lastName)
           && firstName.equals(user.firstName)
           && Objects.equals(phone, user.phone)
           && email.equals(user.email)
           && Objects.equals(addressOne, user.addressOne)
           && Objects.equals(addressTwo, user.addressTwo)
           && Objects.equals(city, user.city)
           && Objects.equals(state, user.state)
           && Objects.equals(postalCode, user.postalCode)
           && Objects.equals(country, user.country)
           && username.equals(user.username)
           && password.equals(user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
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
