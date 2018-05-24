package com.example.user.magicleapchallenge.model.source;

import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

public class CoffeeRepository {

    CoffeeDataSource remoteCoffeeSource;
    CoffeeDataSource localCoffeeSource;


    public CoffeeRepository(CoffeeDataSource remoteCoffeeSource, CoffeeDataSource localCoffeeSource) {
        this.remoteCoffeeSource = remoteCoffeeSource;
        this.localCoffeeSource = localCoffeeSource;
    }

    public void getCoffeeItems(final CoffeeDataSource.LoadCoffeeItemsCallBack callBack) {

        remoteCoffeeSource.getCoffeeItems(new CoffeeDataSource.LoadCoffeeItemsCallBack() {
            @Override
            public void onCoffeeItemsLoaded(List<CoffeeItem> coffeeItems) {
                callBack.onCoffeeItemsLoaded(coffeeItems);
            }

            @Override
            public void onLoadingFailed(String error) {
                callBack.onLoadingFailed(error);
            }
        });

    }

}
