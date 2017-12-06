
package foodforfun.dto;

import java.io.Serializable;


public class ImageDTO implements Serializable{
   private String ImageID;
    private String details;
    private String PostID;

  public ImageDTO() {
  }

  public ImageDTO(String ImageID, String details, String PostID) {
    this.ImageID = ImageID;
    this.details = details;
    this.PostID = PostID;
  }

  public String getImageID() {
    return ImageID;
  }

  public void setImageID(String ImageID) {
    this.ImageID = ImageID;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getPostID() {
    return PostID;
  }

  public void setPostID(String PostID) {
    this.PostID = PostID;
  }
    
}
