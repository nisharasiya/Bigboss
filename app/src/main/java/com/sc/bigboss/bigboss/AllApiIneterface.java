package com.sc.bigboss.bigboss;

import com.sc.bigboss.bigboss.PlaySliderPOJO.PlayBean;
import com.sc.bigboss.bigboss.SearchPojo.SearchBean;
import com.sc.bigboss.bigboss.ShopTillPOJO.TillBean;
import com.sc.bigboss.bigboss.ShoptabPOJO.ShopBean;
import com.sc.bigboss.bigboss.TabCategoryPOJO.TabBean;
import com.sc.bigboss.bigboss.TillCategory3POJO.ShopProductBean;
import com.sc.bigboss.bigboss.TillSubCategory2.TillSubCatBean;
import com.sc.bigboss.bigboss.VideoGenralPOJO.GenralBean;
import com.sc.bigboss.bigboss.VideoUrlPOJO.VideourlBean;
import com.sc.bigboss.bigboss.getPlayPOJO.getPlayBean;
import com.sc.bigboss.bigboss.locationPOJO.locationBean;
import com.sc.bigboss.bigboss.matchingPOJO.matchingBean;
import com.sc.bigboss.bigboss.playDataPOJO.playDataBean;
import com.sc.bigboss.bigboss.prodList2POJO.prodList2Bean;
import com.sc.bigboss.bigboss.registerPlayPOJO.registerPlayBean;
import com.sc.bigboss.bigboss.subCat3POJO.subCat3Bean;
import com.sc.bigboss.bigboss.winnersPOJO.winnersBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AllApiIneterface {


    @Multipart
    @POST("bigboss/?rest_route=/GetVideoByCategory/v1/video-by-category")
    Call<GenralBean> genra(
            @Part("catId") String catid,
            @Part("locationId") String locationId
    );


    @GET("bigboss/?rest_route=/GetVideoCategory/v1/video-category")
    Call<TabBean> tabbean();


   /* @GET("bigboss/?rest_route=/GetShopCategory/v1/get-shop-category")
    Call<ShopBean> sho();
*/

    @Multipart
    @POST("bigboss/?rest_route=/GetShopCategory/v1/get-shop-category")
    Call<ShopBean> sho(
            @Part("locationId") String catssid);


    @GET("bigboss/?rest_route=/GetLocationCategory/v1/location-list")
    Call<locationBean> getLocations();


    @Multipart
    @POST("bigboss/?rest_route=/GetShopsubCategory1/v1/get-shop-sub-category1")
    Call<TillBean> till(
            @Part("catId") String catid ,
            @Part("locationId") String catssid);


    @Multipart
    @POST("bigboss/?rest_route=/GetShopsubCategory2/v1/get-shop-sub-category2")
    Call<TillSubCatBean> tillcat2(
            @Part("subCatId") String catid ,
            @Part("locationId") String ctid );

    @Multipart
    @POST("bigboss/api/getSubCat3.php")
    Call<subCat3Bean> subCat3(
            @Part("subcat1_id") String catid);

    @Multipart
    @POST("bigboss/api/getProd2.php")
    Call<prodList2Bean> getProd2(
            @Part("sub_category2") String catid,
            @Part("locationId") String location
    );

    @Multipart
    @POST("bigboss/?rest_route=/GetProductByid/v1/get-product")
    Call<PlayBean> play(
            @Part("productId") String catid,
            @Part("locationId") String locationId
    );


    @Multipart
    @POST("bigboss/?rest_route=/GetProductBysubcatid/v1/get-product-by-subcat")
    Call<ShopProductBean> shopproduct(
            @Part("subCatId") String subcatid,
            @Part("locationId") String locationId
    );


    @Multipart
    @POST("bigboss/?rest_route=/GetVideoByCategory/v1/video-by-category")
    Call<VideourlBean> video(
            @Part("catId") String cat);


    @Multipart
    @POST("bigboss/?rest_route=/GetMatchingProduct/v1/matching-product")
    Call<matchingBean> getMatchingData(
            @Part("matchingId") String cat);


    @Multipart
    @POST("bigboss/?rest_route=/SearchProduct/v1/get-product")
    Call<SearchBean> search(
            @Part("searchVal") String cat ,
            @Part("locationId") String c

    );

    @Multipart
    @POST("bigboss/api/registerPlay.php")
    Call<registerPlayBean> registerPlay(
            @Part("playId") String playId,
            @Part("name") String name,
            @Part("phone") String phone,
            @Part("deviceId") String deviceId,
            @Part("ipaddress") String ipaddress,
            @Part("token") String token,
            @Part("sku") String sku,
            @Part MultipartBody.Part file1
    );

    @Multipart
    @POST("bigboss/api/playBid.php")
    Call<registerPlayBean> playBid(
            @Part("playId") String playId,
            @Part("userId") String userId,
            @Part("bid") String bid
    );

    @Multipart
    @POST("bigboss/api/getPlayData.php")
    Call<playDataBean> getPlayData(
            @Part("playId") String playId
    );

    @Multipart
    @POST("bigboss/api/getPlay.php")
    Call<getPlayBean> getPlay(
            @Part("locationId") String c
    );

    @Multipart
    @POST("bigboss/api/endPlay.php")
    Call<registerPlayBean> endPlay(
            @Part("playId") String playId,
            @Part("wid") String wid
    );


    @GET("bigboss/api/getWinners.php")
    Call<winnersBean> getWiners();

    @Multipart
    @POST("bigboss/api/getPerks.php")
    Call<winnersBean> getPerks(
            @Part("phone") String phone
    );

}