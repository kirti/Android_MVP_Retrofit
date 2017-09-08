package com.kkmvp.kkmvp.Views;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kkmvp.kkmvp.Contract.UserContract;
import com.kkmvp.kkmvp.Model.UserResponse;
import com.kkmvp.kkmvp.Presenter.UserPresenter;
import com.kkmvp.kkmvp.R;
import com.kkmvp.kkmvp.UserAdaptor;

import java.util.List;

/**
 * Created by KK00120638 on 9/7/2017.
 */

public class SecondActivity extends AppCompatActivity implements UserContract.View {
    UserPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    public void addUserData(List<UserResponse> resultUser) {
        UserAdaptor userAdaptor= new UserAdaptor(resultUser);
        recyclerView.setAdapter(userAdaptor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Log.i("RETROFIT", "navigateToSecondScreen launch" + "\n");

        presenter = new UserPresenter(this);
        presenter.getUsers();
        initUserViews();
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {

    }


    public void initUserViews(){

        recyclerView=(RecyclerView)findViewById(R.id.secondRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }

}
