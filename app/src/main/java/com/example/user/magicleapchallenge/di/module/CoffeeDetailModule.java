package com.example.user.magicleapchallenge.di.module;

import com.example.user.magicleapchallenge.di.scope.ActivityScoped;
import com.example.user.magicleapchallenge.model.source.CoffeeRepository;
import com.example.user.magicleapchallenge.view.coffeedetail.CoffeeDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Author: singh on: 25-May-18.
 */

@Module
public class CoffeeDetailModule {

    @ActivityScoped
    @Provides
    CoffeeDetailPresenter providesCoffeeDetailPresenter(CoffeeRepository coffeeRepository) {
        return new CoffeeDetailPresenter(coffeeRepository);
    }
}
