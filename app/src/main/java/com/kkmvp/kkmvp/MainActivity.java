package com.kkmvp.kkmvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kkmvp.kkmvp.Model.RestResponse;
import com.kkmvp.kkmvp.Model.obj;
import com.kkmvp.kkmvp.Presenter.CountryPresenter;
import com.kkmvp.kkmvp.Views.SecondActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryPresenter.CountryPresenterListener {
    private CountryPresenter countryPresenter;
    private RecyclerView recyclerView;

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
        countryPresenter.getCountries(recyclerView);

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

    @Override
    public void countriesReady(List<obj> countries) {

        DataSource dataSource = new DataSource(countries);

        recyclerView.setAdapter(dataSource);

//       for(obj country : countries){
//            Log.i("RETROFIT", country.getName() + "\n"
//                + " - Alpha2:  " + country.getAlpha2_code() +" \n"
//                   + " - Alpha3: " + country.getAlpha3_code());
//        }
    }
}
