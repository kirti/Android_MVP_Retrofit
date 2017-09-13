package com.kkmvp.kkmvp.Views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.kkmvp.kkmvp.Contract.UserContract;
import com.kkmvp.kkmvp.MainActivity;
import com.kkmvp.kkmvp.Model.UserResponse;
import com.kkmvp.kkmvp.Presenter.UserPresenter;
import com.kkmvp.kkmvp.R;
import com.kkmvp.kkmvp.UserAdaptor;

import java.util.Arrays;
import java.util.List;

/**
 * Created by KK00120638 on 9/7/2017.
 */

public class SecondActivity extends AppCompatActivity implements UserContract.View {
    UserPresenter presenter;
    private RecyclerView recyclerView;
    SharedPreferences sharedpreferences;
    public static final String MyUserPREFERENCES = "UserDetail" ;
    public static ProgressDialog progressDialog;
    @Override
    public void addUserData(List<UserResponse> resultUser) {
        UserAdaptor userAdaptor= new UserAdaptor(resultUser);
        recyclerView.setAdapter(userAdaptor);
        hideProgressDialog();
    }

    @Override
    public void setSharedPref(List<UserResponse> resultUser) {
        SharedPreferences sharedPref= getApplicationContext().getSharedPreferences(MyUserPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Log.i("strJson", "setSharedPref launch" + resultUser  + "\n");
        Gson gson = new Gson();
        String json = gson.toJson(resultUser);
        editor.putString("jsondata", json);
        editor.commit();
        getSharedPrefUserData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Log.i("RETROFIT", "navigateToSecondScreen launch" + "\n");
        initUserViews();
        presenter = new UserPresenter(this);
       // presenter.getUsers();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MyUserPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.contains("jsondata")){
            Log.i("sharedPrefere","cccvcv");
            showProgressDialog();
            getSharedPrefUserData();
        }else {
            showProgressDialog();
            presenter.getUsers();
        }


    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {

    }

    public void showProgressDialog(){

        progressDialog= new ProgressDialog(SecondActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading Please wait ");
        progressDialog.setTitle("Loading ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

    }

    public void hideProgressDialog(){
        progressDialog.dismiss();

    }
    public void initUserViews(){

        recyclerView=(RecyclerView)findViewById(R.id.secondRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }

    public void getSharedPrefUserData() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MyUserPREFERENCES, Context.MODE_PRIVATE);

        String strJson = sharedPreferences.getString("jsondata","");//second parameter is necessary ie.,Value to return if this preference does not exist.

        if(strJson != null){
            Gson gson = new Gson();
            com.kkmvp.kkmvp.Model.UserResponse[] userResponses = gson.fromJson(strJson, UserResponse[].class);
            addUserData(Arrays.asList(userResponses));

        }
    }

}
