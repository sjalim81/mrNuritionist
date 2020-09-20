package com.example.mrnuritionist;

public class UserModel {

    String name,fullName,country,imageId;

    public UserModel() {
    }

    public UserModel(String name, String fullName, String country, String imageId) {
        this.name = name;
        this.fullName = fullName;
        this.country = country;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
