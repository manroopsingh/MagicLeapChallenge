package com.example.user.magicleapchallenge.view.coffeelist;

import com.example.user.magicleapchallenge.model.CoffeeItem;
import com.example.user.magicleapchallenge.model.source.CoffeeDataSource;
import com.example.user.magicleapchallenge.model.source.CoffeeRepository;

import java.util.List;

/**
 * Author: singh on: 24-May-18.
 */
public class CoffeeListPresenter implements
        CoffeeListContract.Presenter,
        CoffeeDataSource.LoadCoffeeItemsCallBack{

    CoffeeListContract.View view;
    CoffeeRepository coffeeRepository;

    public CoffeeListPresenter(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public void getCoffeeItems() {
        coffeeRepository.getCoffeeItems(this);
    }

    @Override
    public void attachView(CoffeeListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onCoffeeItemsLoaded(List<CoffeeItem> coffeeItems) {
        view.onCoffeeItemsLoaded(coffeeItems);
    }

    @Override
    public void onLoadingFailed(String error) {
        view.showError(error);
    }
}
