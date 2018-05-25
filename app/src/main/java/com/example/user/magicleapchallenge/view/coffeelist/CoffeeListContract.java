package com.example.user.magicleapchallenge.view.coffeelist;

import com.example.user.magicleapchallenge.model.CoffeeItem;
import com.example.user.magicleapchallenge.view.base.BasePresenter;
import com.example.user.magicleapchallenge.view.base.BaseView;

import java.util.List;

/**
 * Author: user on: 24-May-18.
 */
public interface CoffeeListContract {

    interface View extends BaseView{

        void onCoffeeItemsLoaded(List<CoffeeItem> coffeeItems);
    }

    interface Presenter extends BasePresenter<View> {

        void getCoffeeItems();

    }

}
