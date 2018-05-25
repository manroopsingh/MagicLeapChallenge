package com.example.user.magicleapchallenge.model.source;

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

}
