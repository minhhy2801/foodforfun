package foodforfun.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostModifyDTO implements Serializable {

  private int modifyID, postID;
  private String modifierID, state, postContent;
  private Timestamp modifyTime;

  public PostModifyDTO(int modifyID, int postID, String modifierID, String state, String postContent, Timestamp modifyTime) {
    this.modifyID = modifyID;
    this.postID = postID;
    this.modifierID = modifierID;
    this.state = state;
    this.postContent = postContent;
    this.modifyTime = modifyTime;
  }

  public int getModifyID() {
    return modifyID;
  }

  public PostModifyDTO() {
  }

  public void setModifyID(int modifyID) {
    this.modifyID = modifyID;
  }

  public int getPostID() {
    return postID;
  }

  public void setPostID(int postID) {
    this.postID = postID;
  }

  public String getModifierID() {
    return modifierID;
  }

  public void setModifierID(String modifierID) {
    this.modifierID = modifierID;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostContent() {
    return postContent;
  }

  public void setPostContent(String postContent) {
    this.postContent = postContent;
  }

  public Timestamp getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(Timestamp modifyTime) {
    this.modifyTime = modifyTime;
  }

  
}
