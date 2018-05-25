package com.example.user.magicleapchallenge.model.source.remote;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;
import com.example.user.magicleapchallenge.model.source.CoffeeDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements CoffeeDataSource {

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

    private Call<List<CoffeeItem>> getCoffeeItemCall() {
        return getService().getCoffeeItems();
    }

    private Call<Coffee> getCoffeeCall(String coffee_id) {
        return getService().getCoffee(coffee_id);
    }


    @Override
    public void getCoffeeItems(final LoadCoffeeItemsCallBack callBack) {


        getCoffeeItemCall().enqueue(new Callback<List<CoffeeItem>>() {
            @Override
            public void onResponse(Call<List<CoffeeItem>> call, final Response<List<CoffeeItem>> response) {
                callBack.onCoffeeItemsLoaded(response.body());

            }

            @Override
            public void onFailure(Call<List<CoffeeItem>> call, Throwable t) {
                callBack.onLoadingFailed(t.toString());
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
