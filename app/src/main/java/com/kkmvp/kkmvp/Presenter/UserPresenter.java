package com.kkmvp.kkmvp.Presenter;

import android.content.Context;
import android.util.Log;

import com.kkmvp.kkmvp.Contract.UserContract;
import com.kkmvp.kkmvp.Model.Result;
import com.kkmvp.kkmvp.Model.UserResponse;
import com.kkmvp.kkmvp.Model.UserRespoonseBase;
import com.kkmvp.kkmvp.Service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KK00120638 on 9/8/2017.
 */

public class UserPresenter implements UserContract.Presenter {

    private final UserContract.View userView;
    private final UserService userService;



    @Override
    public void start() {

    }

    public UserPresenter(UserContract.View userView) {
        this.userView = userView;
        this.userService = new UserService();

        userView.setPresenter(this);

    }

    public void getUsers() {
        userService.getAPI().listOfUserResponse().enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                Log.i("RETROFIT",response + "\n");

                List<UserResponse> resultUser = response.body();

                  userView.addUserData(resultUser);
                  userView.setSharedPref(resultUser);
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {

            }
        });
    }
}
