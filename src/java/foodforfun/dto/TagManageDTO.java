package foodforfun.dto;

import java.io.Serializable;

public class TagManageDTO implements Serializable {

  private String TagManageID;
  private String PostID;
  private String TagID;

  public TagManageDTO() {
  }

  public TagManageDTO(String TagManageID, String PostID, String TagID) {
    this.TagManageID = TagManageID;
    this.PostID = PostID;
    this.TagID = TagID;
  }

  public String getTagManageID() {
    return TagManageID;
  }

  public void setTagManageID(String TagManageID) {
    this.TagManageID = TagManageID;
  }

  public String getPostID() {
    return PostID;
  }

  public void setPostID(String PostID) {
    this.PostID = PostID;
  }

  public String getTagID() {
    return TagID;
  }

  public void setTagID(String TagID) {
    this.TagID = TagID;
  }

}
