package com.example.user.magicleapchallenge.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context providesContext() {
        return context;
    }
}
