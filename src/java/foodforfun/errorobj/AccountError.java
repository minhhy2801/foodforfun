package foodforfun.errorobj;

import foodforfun.dto.*;
import java.io.Serializable;
import java.sql.Date;

public class AccountError implements Serializable {

  private String accountID;
  private String password;
  private String role;
  private String name;
  private String sex;
  private String email;
  private String phone;
  private String address;
  private String dob;
  private String expYear;
  private String worledPlace;
  private String confirmPassword;
  private String agreePolicy;

  
  public String getAgreePolicy() {
        return agreePolicy;
    }

    public void setAgreePolicy(String agreePolicy) {
        this.agreePolicy = agreePolicy;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

  public AccountError() {
  }

  public AccountError(String accountID, String password, String role, String name, String sex, String email, String phone, String address, String dob, String expYear, String worledPlace) {
    this.accountID = accountID;
    this.password = password;
    this.role = role;
    this.name = name;
    this.sex = sex;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.dob = dob;
    this.expYear = expYear;
    this.worledPlace = worledPlace;
  }

  public AccountError(String accountID, String role, String name, String sex, String email, String phone, String address, String dob, String expYear, String worledPlace) {
    this.accountID = accountID;
    this.role = role;
    this.name = name;
    this.sex = sex;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.dob = dob;
    this.expYear = expYear;
    this.worledPlace = worledPlace;
  }

  public String getAccountID() {
    return accountID;
  }

  public void setAccountID(String accountID) {
    this.accountID = accountID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

 

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getExpYear() {
    return expYear;
  }

  public void setExpYear(String expYear) {
    this.expYear = expYear;
  }


  public String getWorledPlace() {
    return worledPlace;
  }

  public void setWorledPlace(String worledPlace) {
    this.worledPlace = worledPlace;
  }


}
