package com.example.user.magicleapchallenge.view.addCoffee;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.view.base.BasePresenter;
import com.example.user.magicleapchallenge.view.base.BaseView;

public interface AddCoffeeContract {

    interface View extends BaseView {

        void addCoffee(Coffee coffee);
    }

    interface Presenter extends BasePresenter<View> {

        void onCoffeeAdded(boolean isAdded);
    }

}
