package foodforfun.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentDTO implements Serializable {

  private String commentID;//ID cua comment
  private String createrID;//ID cua nguoi tao comment
  private String nameOfCreater;//Ten cua nguoi tao comment
  private String detail;//Chi tiet cua comment
  private Timestamp dateCreated;//Ngay tao comment 
  private String postID;//ID post chua comment nay
  private String namePost;//Ten post chua comment nay

  public CommentDTO() {
  }

    public CommentDTO(String createrID, String detail, Timestamp dateCreated, String postID) {
        this.createrID = createrID;
        this.detail = detail;
        this.dateCreated = dateCreated;
        this.postID = postID;
    }

  public CommentDTO(String CommentID, String CreaterID, String nameOfCreater, String Detail, Timestamp DateCreated, String PostID, String NameOfPostHaveThisComent) {
    this.commentID = CommentID;
    this.createrID = CreaterID;
    this.nameOfCreater = nameOfCreater;
    this.detail = Detail;
    this.dateCreated = DateCreated;
    this.postID = PostID;
    this.namePost = NameOfPostHaveThisComent;
  }

  public String getCommentID() {
    return commentID;
  }

  public void setCommentID(String CommentID) {
    this.commentID = CommentID;
  }

  public String getCreaterID() {
    return createrID;
  }

  public void setCreaterID(String CreaterID) {
    this.createrID = CreaterID;
  }

  public String getNameOfCreater() {
    return nameOfCreater;
  }

  public void setNameOfCreater(String nameOfCreater) {
    this.nameOfCreater = nameOfCreater;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String Detail) {
    this.detail = Detail;
  }

  public Timestamp getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Timestamp DateCreated) {
    this.dateCreated = DateCreated;
  }

  public String getPostID() {
    return postID;
  }

  public void setPostID(String PostID) {
    this.postID = PostID;
  }

  public String getNamePost() {
    return namePost;
  }

  public void setNamePost(String namePost) {
    this.namePost = namePost;
  }

}
