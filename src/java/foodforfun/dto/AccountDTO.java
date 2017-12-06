package foodforfun.dto;

import java.io.Serializable;
import java.sql.Date;

public class AccountDTO implements Serializable {

  private String accountID;
    private String password;
    private String role;
    private String name;
    private boolean sex;
    private String email;
    private String phone;
    private String address;
    private Date dob;
    private int expYear;
    private String worledPlace;

  
  public AccountDTO() {
  }

    public AccountDTO(String accountID, String role, String name) {
        this.accountID = accountID;
        this.role = role;
        this.name = name;
    }
  

  public AccountDTO(String accountID, String password, String role, String name, boolean sex, String email, String phone, String address, Date dob, int expYear, String worledPlace) {
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

  public AccountDTO(String accountID, String role, String name, boolean sex, String email, String phone, String address, Date dob, int expYear, String worledPlace) {
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

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
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

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public int getExpYear() {
    return expYear;
  }

  public void setExpYear(int expYear) {
    this.expYear = expYear;
  }

  public String getWorledPlace() {
    return worledPlace;
  }

  public void setWorledPlace(String worledPlace) {
    this.worledPlace = worledPlace;
  }

  

}
