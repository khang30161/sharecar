package com.example.khang.sharecar;

import android.widget.ImageView;

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
    private String key;
    private String categogy;
    private String loaixe;
    private String dongxe;

    public RentManagers() {
        this.id = id;
        this.startdate = startdate;
        this.enddate = enddate;
        this.location = location;
        this.price = price;

    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public String getDongxe() {
        return dongxe;
    }

    public void setDongxe(String dongxe) {
        this.dongxe = dongxe;
    }

    public String getCategogy() {
        return categogy;
    }

    public void setCategogy(String categogy) {
        this.categogy = categogy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserIdBook() {
        return userIdBook;
    }

    public void setUserIdBook(String userIdBook) {
        this.userIdBook = userIdBook;
    }
}
