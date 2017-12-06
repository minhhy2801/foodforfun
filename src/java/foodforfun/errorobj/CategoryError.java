package foodforfun.errorobj;

import foodforfun.dto.*;
import java.io.Serializable;
import java.sql.Timestamp;

public class CategoryError implements Serializable {

  private String id, name, createrID, des;
  private Timestamp date;
  private String createrName;
  private int count;

  public CategoryError() {
  }

  public CategoryError(String id, String name, String des, Timestamp date, String createrName, int count) {
    this.id = id;
    this.name = name;
    this.des = des;
    this.date = date;
    this.createrName = createrName;
    this.count = count;
  }

  public CategoryError(String id, String name, String createrID, String des, Timestamp date, String createrName, int count) {
    this.id = id;
    this.name = name;
    this.createrID = createrID;
    this.des = des;
    this.date = date;
    this.createrName = createrName;
    this.count = count;
  }

  public String getCreaterName() {
    return createrName;
  }

  public void setCreaterName(String createrName) {
    this.createrName = createrName;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public CategoryError(String id, String name, String createrID, Timestamp date) {
    this.id = id;
    this.name = name;
    this.createrID = createrID;
    this.date = date;
  }

  public CategoryError(String id, String name, String createrID, String des, Timestamp date) {
    this.id = id;
    this.name = name;
    this.createrID = createrID;
    this.des = des;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreaterID() {
    return createrID;
  }

  public void setCreaterID(String createrID) {
    this.createrID = createrID;
  }

  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }

}
