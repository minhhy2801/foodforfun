package foodforfun.errorobj;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostModifyError implements Serializable {

  private String modifyID, postID, modifierID, state, postContent;
  private Timestamp modifyTime;

  public PostModifyError(String modifyID, String postID, String modifierID, String state, String postContent, Timestamp modifyTime) {
    this.modifyID = modifyID;
    this.postID = postID;
    this.modifierID = modifierID;
    this.state = state;
    this.postContent = postContent;
    this.modifyTime = modifyTime;
  }

  public PostModifyError() {
  }

  public String getModifyID() {
    return modifyID;
  }

  public void setModifyID(String modifyID) {
    this.modifyID = modifyID;
  }

  public String getPostID() {
    return postID;
  }

  public void setPostID(String postID) {
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
