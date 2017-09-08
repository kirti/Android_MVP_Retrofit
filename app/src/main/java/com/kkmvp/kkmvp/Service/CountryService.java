package com.kkmvp.kkmvp.Service;

import com.kkmvp.kkmvp.Model.RestResponse;
import com.kkmvp.kkmvp.Model.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by KK00120638 on 9/6/2017.
 */

public class CountryService {

    public static String BASE_URL =  "http://services.groupkt.com/";

    public interface CountryAPI{
            @GET("country/get/all")
            Call<Result> getResults();

    }

    public CountryAPI getAPI(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(CountryAPI.class);
    }
}
