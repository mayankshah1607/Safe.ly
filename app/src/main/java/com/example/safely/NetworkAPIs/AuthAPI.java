package com.example.safely.NetworkAPIs;

import com.example.safely.NetworkModels.BasicModel;
import com.example.safely.NetworkModels.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by luci4 on 31/3/18.
 */

public interface AuthAPI {

    @POST("register")
    @FormUrlEncoded
    Call<BasicModel> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("contact") String contact,
            @Field("age") String age
    );

    @POST("login")
    @FormUrlEncoded
    Call<LoginModel> login(
            @Field("email") String email,
            @Field("password") String password
    );


}
