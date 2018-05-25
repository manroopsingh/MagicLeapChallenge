package com.example.user.magicleapchallenge.view.coffeedetail;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.source.CoffeeDataSource;
import com.example.user.magicleapchallenge.model.source.CoffeeRepository; /**
 * Author: user on: 24-May-18.
 */
public class CoffeeDetailPresenter implements CoffeeDetailContract.Presenter, CoffeeDataSource.LoadCoffeeDetailCallBack{

    CoffeeRepository coffeeRepository;
    CoffeeDetailContract.View view;

    public CoffeeDetailPresenter(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public void getCoffeeDetails(String coffee_id) {

        coffeeRepository.getCoffeeDetails(coffee_id,this);
    }

    @Override
    public void attachView(CoffeeDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onCoffeeDetailLoaded(Coffee coffee) {
        view.onCoffeeDetailsLoaded(coffee);
    }

    @Override
    public void onLoadingFailed(String error) {
        view.showError(error);

    }
}
