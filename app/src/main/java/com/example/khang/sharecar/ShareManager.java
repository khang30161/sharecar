package com.example.khang.sharecar;

import java.io.Serializable;

public class ShareManager implements Serializable {
   private String startday;
   private int starttimeh;
   private int starttimem;
   private int endtimeh;
   private int endtimem;
   private String localcity;
   private String localquan;
   private String localphuong;
   private String seat;
   private String loaixe;
   private String intro;
   private String sovitri;
   private String note;
   private String url;
   private String startpoint;
   private String endpoint;
   private String price;
   private String uid;
    private String isbooking1;
   private String isBooking2;
   private String isBooking3;
   private String isBooking4;
   private String isBooking5;
   private String idShare;

    public String getLocalphuong() {
        return localphuong;
    }

    public void setLocalphuong(String localphuong) {
        this.localphuong = localphuong;
    }

    public String getIdShare() {
        return idShare;
    }

    public void setIdShare(String idShare) {
        this.idShare = idShare;
    }

    public String getIsbooking1() {
        return isbooking1;
    }

    public void setIsbooking1(String isbooking1) {
        this.isbooking1 = isbooking1;
    }

    public String getIsBooking2() {
        return isBooking2;
    }

    public void setIsBooking2(String isBooking2) {
        this.isBooking2 = isBooking2;
    }

    public String getIsBooking3() {
        return isBooking3;
    }

    public void setIsBooking3(String isBooking3) {
        this.isBooking3 = isBooking3;
    }

    public String getIsBooking4() {
        return isBooking4;
    }

    public void setIsBooking4(String isBooking4) {
        this.isBooking4 = isBooking4;
    }

    public String getIsBooking5() {
        return isBooking5;
    }

    public void setIsBooking5(String isBooking5) {
        this.isBooking5 = isBooking5;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStarttimeh() {
        return starttimeh;
    }

    public void setStarttimeh(int starttimeh) {
        this.starttimeh = starttimeh;
    }

    public int getStarttimem() {
        return starttimem;
    }

    public void setStarttimem(int starttimem) {
        this.starttimem = starttimem;
    }

    public int getEndtimeh() {
        return endtimeh;
    }

    public void setEndtimeh(int endtimeh) {
        this.endtimeh = endtimeh;
    }

    public int getEndtimem() {
        return endtimem;
    }

    public void setEndtimem(int endtimem) {
        this.endtimem = endtimem;
    }

    public String getStartday() {
        return startday;
    }

    public void setStartday(String startday) {
        this.startday = startday;
    }


    public String getLocalcity() {
        return localcity;
    }

    public void setLocalcity(String localcity) {
        this.localcity = localcity;
    }

    public String getLocalquan() {
        return localquan;
    }

    public void setLocalquan(String localquan) {
        this.localquan = localquan;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getSovitri() {
        return sovitri;
    }

    public void setSovitri(String sovitri) {
        this.sovitri = sovitri;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(String startpoint) {
        this.startpoint = startpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public ShareManager (){
        this.localcity=localcity;
        this.starttimeh=starttimeh;
        this.starttimem=starttimem;
        this.endtimeh=endtimeh;
        this.endtimem=endtimem;
        this.price=price;
        this.startday=startday;

    }
}
