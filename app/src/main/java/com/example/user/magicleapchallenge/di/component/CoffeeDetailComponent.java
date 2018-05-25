package com.example.user.magicleapchallenge.di.component;

import com.example.user.magicleapchallenge.di.module.CoffeeDetailModule;
import com.example.user.magicleapchallenge.di.scope.ActivityScoped;
import com.example.user.magicleapchallenge.view.coffeedetail.CoffeeDetailActivity;

import dagger.Subcomponent;

/**
 * Author: user on: 25-May-18.
 */
@ActivityScoped
@Subcomponent(modules = CoffeeDetailModule.class)
public interface CoffeeDetailComponent {
    void inject(CoffeeDetailActivity coffeeDetailActivity);
}
