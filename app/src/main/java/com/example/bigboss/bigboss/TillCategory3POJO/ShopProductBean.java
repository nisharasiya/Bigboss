package com.example.bigboss.bigboss.TillCategory3POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopProductBean {

    @SerializedName("product_info")
    @Expose
    private List<ProductInfo> productInfo = null;
    @SerializedName("thumb")
    @Expose
    private List<Thumb> thumb = null;

    public List<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }

    public List<Thumb> getThumb() {
        return thumb;
    }

    public void setThumb(List<Thumb> thumb) {
        this.thumb = thumb;
    }



}
