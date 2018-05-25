package com.example.user.magicleapchallenge.di.module;

import com.example.user.magicleapchallenge.di.scope.AppScoped;
import com.example.user.magicleapchallenge.model.source.CoffeeRepository;
import com.example.user.magicleapchallenge.model.source.local.LocalDataSource;
import com.example.user.magicleapchallenge.model.source.remote.RemoteDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Author: singh on: 25-May-18.
 */

@Module
public class CoffeeRepositoryModule {


    @AppScoped
    @Provides
    RemoteDataSource providesRemoteDataSource() {
        return new RemoteDataSource();
    }

    @AppScoped
    @Provides
    LocalDataSource providesLocalDataSource() {
        return new LocalDataSource();
    }

    @AppScoped
    @Provides
    CoffeeRepository providesCoffeeRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {

        return new CoffeeRepository(remoteDataSource, localDataSource);
    }
}
