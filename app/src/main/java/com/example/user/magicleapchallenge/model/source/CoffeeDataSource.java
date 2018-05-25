package com.example.user.magicleapchallenge.model.source;

import android.support.annotation.Nullable;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

public interface CoffeeDataSource {



    interface LoadCoffeeItemsCallBack{

        void onCoffeeItemsLoaded(List<CoffeeItem> coffeeItems);

        void onLoadingFailed(String error);
    }

    interface LoadCoffeeDetailCallBack{

        void onCoffeeDetailLoaded(Coffee coffee);

        void onLoadingFailed(String error);

    }

    void getCoffeeItems(LoadCoffeeItemsCallBack callBack);

    void getCoffeeDetails(String coffee_id, LoadCoffeeDetailCallBack callBack);

    void saveCoffeeItems(List<CoffeeItem> coffeeItems);

    void saveCoffee(Coffee coffee);

    void isCacheDirty(@Nullable String cacheType, @Nullable String coffee_id, CoffeeRepository.CheckCacheCallback cacheCallback);

    void updateCacheTime(@Nullable String cacheType, String time);

}
