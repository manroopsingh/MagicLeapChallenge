package com.example.user.magicleapchallenge.model.source.remote;

import android.util.Log;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;
import com.example.user.magicleapchallenge.model.source.CoffeeDataSource;
import com.example.user.magicleapchallenge.utils.TagUtils;

import java.util.List;

import static android.support.constraint.Constraints.TAG;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements CoffeeDataSource{

    private static final String COFFEE_URL = "https://demo6983184.mockable.io";

    private Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(COFFEE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private RemoteService getService() {
        return create().create(RemoteService.class);
    }

    public Call<List<CoffeeItem>> getCoffeeItemCall(){
        return getService().getCoffeeItems();
    }

    private Call<Coffee> getCoffeeCall(String coffee_id){
        return getService().getCoffee(coffee_id);
    }


    @Override
    public void getCoffeeItems(final LoadCoffeeItemsCallBack callBack) {

        Log.d(TagUtils.get(this), "getCoffeeItems: ");
        getCoffeeItemCall().enqueue(new Callback<List<CoffeeItem>>() {
            @Override
            public void onResponse(Call<List<CoffeeItem>> call, Response<List<CoffeeItem>> response) {
                callBack.onCoffeeItemsLoaded(response.body());
                Log.d(TagUtils.get(this), "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<CoffeeItem>> call, Throwable t) {
                callBack.onLoadingFailed(t.toString());
                Log.d(TagUtils.get(this), "onFailure: ");
            }
        });

    }

    @Override
    public void getCoffeeDetails(String coffee_id, final LoadCoffeeDetailCallBack callBack) {

        getCoffeeCall(coffee_id).enqueue(new Callback<Coffee>() {
            @Override
            public void onResponse(Call<Coffee> call, Response<Coffee> response) {
                callBack.onCoffeeDetailLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Coffee> call, Throwable t) {
                callBack.onLoadingFailed();
            }
        });
    }
}
