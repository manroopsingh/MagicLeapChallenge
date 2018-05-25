package com.example.user.magicleapchallenge.view.coffeedetail;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.view.base.BasePresenter;
import com.example.user.magicleapchallenge.view.base.BaseView;

/**
 * Author: singh on: 24-May-18.
 */
public interface CoffeeDetailContract {

    interface View extends BaseView{

        void onCoffeeDetailsLoaded(Coffee coffee);
    }

    interface Presenter extends BasePresenter<View> {

        void getCoffeeDetails(String coffee_id);
    }

}
