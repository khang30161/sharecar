package com.example.khang.sharecar;

public class User {
    private String email;
    private String userId;
    private String username;
    private int age;
    private int gender;
    private String numberphone;


    public User(String email, String userId, String username, int age, int gender, String numberphone) {
        this.email = email;
        this.userId = userId;
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.numberphone = numberphone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
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
}
