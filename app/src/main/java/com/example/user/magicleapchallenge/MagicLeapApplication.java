package com.example.user.magicleapchallenge;

import android.app.Application;
import android.content.Context;

import com.example.user.magicleapchallenge.di.component.AppComponent;
import com.example.user.magicleapchallenge.di.component.CoffeeDetailComponent;
import com.example.user.magicleapchallenge.di.component.CoffeeListComponent;
import com.example.user.magicleapchallenge.di.component.DaggerAppComponent;
import com.example.user.magicleapchallenge.di.module.CoffeeDetailModule;
import com.example.user.magicleapchallenge.di.module.CoffeeListModule;

/**
 * Author: singh on: 25-May-18.
 */
public class MagicLeapApplication extends Application {

    private AppComponent appComponent;
    private CoffeeListComponent coffeeListComponent;
    private CoffeeDetailComponent coffeeDetailComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .build();

    }

    public static MagicLeapApplication get(Context context) {
        return (MagicLeapApplication) context.getApplicationContext();
    }

    public CoffeeListComponent getCoffeeListComponent() {
        coffeeListComponent = appComponent.plus(new CoffeeListModule());
        return coffeeListComponent;
    }
    public CoffeeDetailComponent getCoffeeDetailComponent() {
        coffeeDetailComponent = appComponent.plus(new CoffeeDetailModule());
        return coffeeDetailComponent;
    }

    public void clearCoffeeListComponent() {
        coffeeListComponent = null;
    }

    public void clearCoffeeDetailComponent() {
        coffeeDetailComponent = null;
    }

}

