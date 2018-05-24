package com.example.user.magicleapchallenge.model.source.remote;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RemoteService {

    @GET("coffees")
    Call<List<CoffeeItem>> getCoffeeItems();

    @GET("coffees/{coffee_id}")
    Call<Coffee> getCoffee(@Path("coffee_id")String coffee_id);

}
