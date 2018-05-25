package com.example.user.magicleapchallenge.di.component;

import com.example.user.magicleapchallenge.di.module.AppModule;
import com.example.user.magicleapchallenge.di.module.CoffeeDetailModule;
import com.example.user.magicleapchallenge.di.module.CoffeeListModule;
import com.example.user.magicleapchallenge.di.module.CoffeeRepositoryModule;
import com.example.user.magicleapchallenge.di.scope.AppScoped;

import dagger.Component;

/**
 * Author: user on: 25-May-18.
 */
@AppScoped
@Component(modules = {CoffeeRepositoryModule.class, AppModule.class})
public interface AppComponent {

    CoffeeListComponent plus(CoffeeListModule coffeeListModule);

    CoffeeDetailComponent plus(CoffeeDetailModule coffeeDetailModule);

}
