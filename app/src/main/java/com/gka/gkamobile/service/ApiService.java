package com.gka.gkamobile.service;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/auth/get_token")
    @FormUrlEncoded
    Call<ResponseBody> postToken(@Field("app_id") String appId, @Field("app_key") String appKey);

    @POST("/login")
    @FormUrlEncoded
    Call<ResponseBody> postLogin(@Header("Authorization") String auth, @Field("email") String email, @Field("password") String login);

    @POST("/login/status")
    Call<ResponseBody> postStatus(@Header("Authorization") String auth);

    @POST("/register")
    @FormUrlEncoded
    Call<ResponseBody> postRegister(@Header("Authorization") String auth, @Field("email") String email, @Field("password") String password, @Field("full_name") String fullName, @Field("username") String username, @Field("mobile_phone") String mobilePhone);
}
