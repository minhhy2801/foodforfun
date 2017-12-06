
package foodforfun.errorobj;

import foodforfun.dto.*;
import java.io.Serializable;


public class ImageError implements Serializable{
   private String ImageID;
    private String details;
    private String PostID;

  public ImageError() {
  }

  public ImageError(String ImageID, String details, String PostID) {
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
