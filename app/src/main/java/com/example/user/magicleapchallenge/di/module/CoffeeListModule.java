package com.example.user.magicleapchallenge.di.module;

import com.example.user.magicleapchallenge.di.scope.ActivityScoped;
import com.example.user.magicleapchallenge.model.source.CoffeeRepository;
import com.example.user.magicleapchallenge.view.coffeelist.CoffeeListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Author: user on: 25-May-18.
 */
@Module
public class CoffeeListModule {

    @ActivityScoped
    @Provides
    CoffeeListPresenter providesCoffeeListPresenter(CoffeeRepository coffeeRepository) {
        return new CoffeeListPresenter(coffeeRepository);
    }
}
