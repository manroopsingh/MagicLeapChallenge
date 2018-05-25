package com.example.user.magicleapchallenge.di.component;

import com.example.user.magicleapchallenge.di.module.CoffeeListModule;
import com.example.user.magicleapchallenge.di.scope.ActivityScoped;
import com.example.user.magicleapchallenge.view.coffeelist.CoffeeListActivity;

import dagger.Subcomponent;

/**
 * Author: singh on: 25-May-18.
 */
@ActivityScoped
@Subcomponent(modules = CoffeeListModule.class)
public interface CoffeeListComponent {

    void inject(CoffeeListActivity coffeeListActivity);
}
