package com.example.bigboss.bigboss.playDataPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bid {
    @SerializedName("bid")
    @Expose
    private String bid;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
