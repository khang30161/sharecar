package com.example.khang.sharecar;

import java.io.Serializable;

public class User extends Object {
    private String email;
    private String userId;
    private String username;
    private String age;
    private String gender;
    private String numberphone;
    private String local;
    private String job;
    private String facebook;



    public User(String email, String username, String age, String number, String gender, String local, String job, String facebook) {
        this.email=email;
        this.username=username;
        this.age=age;
        this.numberphone=number;
        this.gender=gender;
        this.local=local;
        this.job=job;
        this.facebook=facebook;
    }

    public User() {
        this.email=email;
        this.username=username;
        this.age=age;
        this.numberphone=numberphone;
        this.gender=gender;
        this.local=local;
        this.job=job;
        this.facebook=facebook;


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}

