package foodforfun.errorobj;

import foodforfun.dto.*;
import java.io.Serializable;
import java.sql.Timestamp;

public class CommentError implements Serializable {

  private String CommentID;//ID cua comment
  private String CreaterID;//ID cua nguoi tao comment
  private String nameOfCreater;//Ten cua nguoi tao comment
  private String Detail;//Chi tiet cua comment
  private Timestamp DateCreated;//Ngay tao comment 
  private String PostID;//ID post chua comment nay
  private String NameOfPostHaveThisComent;//Ten post chua comment nay

  public CommentError() {
  }

  public CommentError(String CommentID, String CreaterID, String nameOfCreater, String Detail, Timestamp DateCreated, String PostID, String NameOfPostHaveThisComent) {
    this.CommentID = CommentID;
    this.CreaterID = CreaterID;
    this.nameOfCreater = nameOfCreater;
    this.Detail = Detail;
    this.DateCreated = DateCreated;
    this.PostID = PostID;
    this.NameOfPostHaveThisComent = NameOfPostHaveThisComent;
  }

  public String getCommentID() {
    return CommentID;
  }

  public void setCommentID(String CommentID) {
    this.CommentID = CommentID;
  }

  public String getCreaterID() {
    return CreaterID;
  }

  public void setCreaterID(String CreaterID) {
    this.CreaterID = CreaterID;
  }

  public String getNameOfCreater() {
    return nameOfCreater;
  }

  public void setNameOfCreater(String nameOfCreater) {
    this.nameOfCreater = nameOfCreater;
  }

  public String getDetail() {
    return Detail;
  }

  public void setDetail(String Detail) {
    this.Detail = Detail;
  }

  public Timestamp getDateCreated() {
    return DateCreated;
  }

  public void setDateCreated(Timestamp DateCreated) {
    this.DateCreated = DateCreated;
  }

  public String getPostID() {
    return PostID;
  }

  public void setPostID(String PostID) {
    this.PostID = PostID;
  }

  public String getNameOfPostHaveThisComent() {
    return NameOfPostHaveThisComent;
  }

  public void setNameOfPostHaveThisComent(String NameOfPostHaveThisComent) {
    this.NameOfPostHaveThisComent = NameOfPostHaveThisComent;
  }

}
