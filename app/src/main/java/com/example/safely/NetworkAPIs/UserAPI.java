package com.example.safely.NetworkAPIs;

import com.example.safely.NetworkModels.FetchUserModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by luci4 on 31/3/18.
 */

public interface UserAPI {

    @GET("fetchDetails")
    Call<FetchUserModel> fetchDetails(
            @Header("x-access-token") String token
    );


}
