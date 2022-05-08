package com.arpitsardana.model;

/**
 * Model object to represent a User.
 */
public class User {
  private String userName;
  private String bankName;

  public String getUserName() {
    return userName;
  }

  public String getBankName() {
    return bankName;
  }

  public User(final String bankName, final String userName) {
    this.bankName = bankName;
    this.userName = userName;
  }
}
