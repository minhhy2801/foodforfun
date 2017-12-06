package foodforfun.errorobj;

import foodforfun.dto.*;
import java.io.Serializable;
import java.sql.Timestamp;

public class PostError implements Serializable{

  private String id, title;
  private Timestamp dateAccepted;
  private String createrID; //, acceptedID
  //private String createrName, acceptedName;
  private String detail, imgMain;
  private String state;
  private int likeNum;
  private String placeID;
//  private String categoryID, tagID, comtID;
//  private String categoryName, tagName;

  public PostError(){
  }

  public PostError(String id, String title, Timestamp dateAccepted, String detail) {
    this.id = id;
    this.title = title;
    this.dateAccepted = dateAccepted;
    this.detail = detail;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Timestamp getDateAccepted() {
    return dateAccepted;
  }

  public void setDateAccepted(Timestamp dateAccepted) {
    this.dateAccepted = dateAccepted;
  }

  public String getCreaterID() {
    return createrID;
  }

  public void setCreaterID(String createrID) {
    this.createrID = createrID;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getImgMain() {
    return imgMain;
  }

  public void setImgMain(String imgMain) {
    this.imgMain = imgMain;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public int getLikeNum() {
    return likeNum;
  }

  public void setLikeNum(int likeNum) {
    this.likeNum = likeNum;
  }

  public String getPlaceID() {
    return placeID;
  }

  public void setPlaceID(String placeID) {
    this.placeID = placeID;
  }

  public PostError(String id, String title, Timestamp dateAccepted, String createrID, String detail, String imgMain, String state, int likeNum, String placeID) {
    this.id = id;
    this.title = title;
    this.dateAccepted = dateAccepted;
    this.createrID = createrID;
    this.detail = detail;
    this.imgMain = imgMain;
    this.state = state;
    this.likeNum = likeNum;
    this.placeID = placeID;
  }

}