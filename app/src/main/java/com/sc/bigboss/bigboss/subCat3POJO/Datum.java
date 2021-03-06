package com.sc.bigboss.bigboss.subCat3POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("subcat_name")
    @Expose
    private String subcatName;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("space")
    @Expose
    private String space;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcatName() {
        return subcatName;
    }

    public void setSubcatName(String subcatName) {
        this.subcatName = subcatName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
