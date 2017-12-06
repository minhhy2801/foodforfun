package foodforfun.errorobj;

import foodforfun.dto.*;
import java.io.Serializable;
import java.sql.Timestamp;

public class TagError implements Serializable {

  private String id, name, CreaterID, des;
  private Timestamp createDate;
  private int count;

  public TagError(String id, String name, String CreaterID, Timestamp createDate) {
    this.id = id;
    this.name = name;
    this.CreaterID = CreaterID;
    this.createDate = createDate;
  }

  public TagError(String id, String name, String CreaterID, String des, Timestamp createDate, int count) {
    this.id = id;
    this.name = name;
    this.CreaterID = CreaterID;
    this.des = des;
    this.createDate = createDate;
    this.count = count;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }

  public TagError(String id, String name, String CreaterID, String des, Timestamp createDate) {
    this.id = id;
    this.name = name;
    this.CreaterID = CreaterID;
    this.des = des;
    this.createDate = createDate;
  }

  public TagError() {
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
    return CreaterID;
  }

  public void setCreaterID(String CreaterID) {
    this.CreaterID = CreaterID;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

}
