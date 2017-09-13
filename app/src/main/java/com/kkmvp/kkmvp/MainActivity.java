package com.kkmvp.kkmvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.kkmvp.kkmvp.Model.RestResponse;
import com.kkmvp.kkmvp.Model.Result;
import com.kkmvp.kkmvp.Model.obj;
import com.kkmvp.kkmvp.Presenter.CountryPresenter;
import com.kkmvp.kkmvp.Views.SecondActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryPresenter.CountryPresenterListener {
    private CountryPresenter countryPresenter;
    private RecyclerView recyclerView;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyCountryPrefs" ;
    public static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(); // Recycler view handler and setting
        navigateToSecondScreen(); // function used to navigate to sexond screen
    }

    private void initViews() {
        recyclerView= (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        countryPresenter = new CountryPresenter(this, this); // presenter of countries

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        if(sharedPreferences.contains("jsonCountry")){
            Log.i("shaedCountry","jsonCountry");
            showProgressDialog();
            getSharedPrefCounryData(); // if data stored in shared preference
        }else {
            showProgressDialog();
            countryPresenter.getCountries(recyclerView); // api call for countries

        }


    }

    public void showProgressDialog(){

        progressDialog= new ProgressDialog(MainActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading Please wait ");
        progressDialog.setTitle("Loading ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

    }

    public void hideProgressDialog(){
    progressDialog.dismiss();

    }
    private void navigateToSecondScreen() {
     final Button button = (Button)findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //startActivity(new Intent(this,SecondActivity.class));
                Intent myIntent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(myIntent);
                Log.i("RETROFIT","navigateToSecondScreen" + "\n");
            }
        });

    }

     public void getSharedPrefCounryData() {

         SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
         String strCountyJson = sharedPreferences.getString("jsonCountry","");
         if(strCountyJson !=null){
            Gson gson= new Gson();
             com.kkmvp.kkmvp.Model.obj[] countrydata  =gson.fromJson(strCountyJson, obj[].class);
             countriesReady(Arrays.asList(countrydata));
         }


     }
    @Override
    public void countriesReady(List<obj> countries) {

        DataSource dataSource = new DataSource(countries);

        recyclerView.setAdapter(dataSource);

         hideProgressDialog();

//       for(obj country : countries){
//            Log.i("RETROFIT", country.getName() + "\n"
//                + " - Alpha2:  " + country.getAlpha2_code() +" \n"
//                   + " - Alpha3: " + country.getAlpha3_code());
//        }
    }

    @Override
    public void setSharedPrefCountry(List<obj> resultCounry) {
            SharedPreferences sharedPreferencesC=getApplicationContext().getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferencesC.edit();
            Gson gson=new Gson();
            String jsonCountry=gson.toJson(resultCounry);
            editor.putString("jsonCountry",jsonCountry);
            editor.commit();

    }
}
