/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.dto;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class PlaceDTO implements Serializable{
    private String placeId;
    private String address;
    private String phone;
    private String placeName;
    private String placeType;

  public PlaceDTO() {
  }

  public PlaceDTO(String placeId, String address, String phone, String placeName, String placeType) {
    this.placeId = placeId;
    this.address = address;
    this.phone = phone;
    this.placeName = placeName;
    this.placeType = placeType;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPlaceName() {
    return placeName;
  }

  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }

  public String getPlaceType() {
    return placeType;
  }

  public void setPlaceType(String placeType) {
    this.placeType = placeType;
  }
    
    
}
