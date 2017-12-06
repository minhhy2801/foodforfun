
package foodforfun.errorobj;

import foodforfun.dto.*;
import java.io.Serializable;
import java.sql.Timestamp;


public class AllPostError implements Serializable{
  private String title, author,categories, editor, status;
  private int comments;
  private Timestamp dateAccepted;

  public AllPostError() {
  }

  public AllPostError(String title, String author, String categories, String editor, String status, int comments, Timestamp dateAccepted) {
    this.title = title;
    this.author = author;
    this.categories = categories;
    this.editor = editor;
    this.status = status;
    this.comments = comments;
    this.dateAccepted = dateAccepted;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }

  public String getEditor() {
    return editor;
  }

  public void setEditor(String editor) {
    this.editor = editor;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getComments() {
    return comments;
  }

  public void setComments(int comments) {
    this.comments = comments;
  }

  public Timestamp getDateAccepted() {
    return dateAccepted;
  }

  public void setDateAccepted(Timestamp dateAccepted) {
    this.dateAccepted = dateAccepted;
  }
  
}
