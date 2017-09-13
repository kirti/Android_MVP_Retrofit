package com.kkmvp.kkmvp.Presenter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.kkmvp.kkmvp.Model.Result;
import com.kkmvp.kkmvp.Model.obj;
import com.kkmvp.kkmvp.Service.CountryService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by KK00120638 on 9/6/2017.
 * hjfhfjdh
 */
public class CountryPresenter {
    private final Context context;
    private final CountryPresenterListener mListener;
    private final CountryService countryService;


    public CountryPresenter(CountryPresenterListener listener, Context context){
        this.mListener = listener;
        this.context = context;
        this.countryService = new CountryService();
    }

    public void getCountries(final RecyclerView recyclerView){
        countryService.getAPI().getResults().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();



                if(result !=null){

                    //
                    List<obj> result1 = result.getRestResponse().getResult();
                    mListener.countriesReady(result1);
                    mListener.setSharedPrefCountry(result1);


                }
                /*if(result != null){

                    mListener.countriesReady(result.);

                }*/
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                try {
                    throw  new InterruptedException("Erro na comunicação com o servidor!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public interface CountryPresenterListener {
        void countriesReady(List<obj> countries);
        void setSharedPrefCountry(List<obj> resultUser);
    }
}
