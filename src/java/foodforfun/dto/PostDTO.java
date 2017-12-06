package foodforfun.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostDTO implements Serializable {

    private String id;
    private String title;
    private Timestamp dateAccepted;
    private String createrID; //, acceptedID
    //private String createrName, acceptedName;
    private String detail, imgMain;
    private String state;
    private int likeNum;
    private int placeID;
    private String createrName;
    private int commentNum;
//  private String categoryID, tagID, comtID;
//  private String categoryName, tagName;

    public String getCreaterName() {
        return createrName;
    }

    public PostDTO(String id, String title, Timestamp dateAccepted, String createrID, String detail, String imgMain, String state, String createrName) {
        this.id = id;
        this.title = title;
        this.dateAccepted = dateAccepted;
        this.createrID = createrID;
        this.detail = detail;
        this.imgMain = imgMain;
        this.state = state;
        this.createrName = createrName;
    }
    
    
    
    public PostDTO(String id, String title, String detail, String name, Timestamp dateAccepted, String aId, String image) {
        this.id = id;
        this.title = title;
        this.dateAccepted = dateAccepted;
        this.detail = detail;
        this.createrName = name;
        this.createrID = aId;
        this.imgMain = image;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public PostDTO(String id, String title, Timestamp dateAccepted, String createrID, String state) {
        this.id = id;
        this.title = title;
        this.dateAccepted = dateAccepted;
        this.createrID = createrID;
        this.state = state;
    }

    public PostDTO() {
    }

    public PostDTO(String id, String title, Timestamp dateAccepted, String detail) {
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

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public PostDTO(String id, String title, Timestamp dateAccepted, String createrID, String detail, String imgMain, String state, int likeNum, int placeID) {
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

    public PostDTO(String id, String title, Timestamp dateAccepted, int likeNum) {
        this.id = id;
        this.title = title;
        this.dateAccepted = dateAccepted;
        this.likeNum = likeNum;
    }

}
