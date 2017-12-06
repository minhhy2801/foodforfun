package foodforfun.dto;

import java.io.Serializable;

public class CateManageDTO implements Serializable{
  private String cateManageID, CateID, PostID;

  public CateManageDTO(String cateManageID, String CateID, String PostID) {
    this.cateManageID = cateManageID;
    this.CateID = CateID;
    this.PostID = PostID;
  }

  public CateManageDTO() {
  }

  public String getCateManageID() {
    return cateManageID;
  }

  public void setCateManageID(String cateManageID) {
    this.cateManageID = cateManageID;
  }

  public String getCateID() {
    return CateID;
  }

  public void setCateID(String CateID) {
    this.CateID = CateID;
  }

  public String getPostID() {
    return PostID;
  }

  public void setPostID(String PostID) {
    this.PostID = PostID;
  }
  
}
