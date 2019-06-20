package com.example.khang.sharecar;

import android.net.Uri;
import android.widget.ImageView;

import com.google.firebase.storage.UploadTask;

import java.io.Serializable;

public class RentManagers implements Serializable {
    private String id;
    private String startdate;
    private String style;
     private String enddate;
    private String location;
    private String price;
    private ImageView picture;
    private String url;
    private String intro;
    private String UserId;
    private String userIdBook;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getUserIdBook() {
        return userIdBook;
    }

    public void setUserIdBook(String userIdBook) {
        this.userIdBook = userIdBook;
    }

    public RentManagers() {
        this.id=id;
        this.startdate = startdate;
        this.enddate = enddate;
        this.location = location;
        this.price = price;

    }
}
