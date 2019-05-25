package com.example.khang.sharecar;

public class RentManagers  {
    private String id;
    private String startdate;
    private String enddate;
    private String location;
    private String price;

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

    public RentManagers() {
        this.id=id;
        this.startdate = startdate;
        this.enddate = enddate;
        this.location = location;
        this.price = price;

    }
}
