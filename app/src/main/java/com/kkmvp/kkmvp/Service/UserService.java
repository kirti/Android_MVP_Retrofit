package com.kkmvp.kkmvp.Service;

import com.kkmvp.kkmvp.Model.Result;
import com.kkmvp.kkmvp.Model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


 /* Created by KK00120638 on 9/8/2017.
 */

public class UserService {

    public static String BASE_URL =  "https://jsonplaceholder.typicode.com/";

    public interface UserAPI{
        @GET("posts")
        Call<List<UserResponse>> listOfUserResponse();

    }

    public UserAPI getAPI(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(UserAPI.class);
    }
}
