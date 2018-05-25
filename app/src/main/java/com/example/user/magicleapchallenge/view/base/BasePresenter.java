package com.example.user.magicleapchallenge.view.base;

/**
 * Author: user on: 24-May-18.
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
