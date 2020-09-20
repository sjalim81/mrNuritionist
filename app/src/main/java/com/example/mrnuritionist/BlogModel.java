package com.example.mrnuritionist;

public class BlogModel {

    String name, post,date,blogId,userImage;


    public BlogModel() {
    }

    public BlogModel(String name, String post, String date, String blogId, String userImage) {
        this.name = name;
        this.post = post;
        this.date = date;
        this.blogId = blogId;
        this.userImage = userImage;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
}

