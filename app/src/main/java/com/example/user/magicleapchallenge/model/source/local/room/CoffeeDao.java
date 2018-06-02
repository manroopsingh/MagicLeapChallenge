package com.example.user.magicleapchallenge.model.source.local.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CoffeeDao  {

    @Insert
    void saveCoffee(Coffee coffee);

    @Insert
    void insertAll(List<Coffee> coffees);

    @Query("SELECT * FROM Coffee")
    List<CoffeeItem> getAll();

    @Query("SELECT * FROM COFFEE where id LIKE :coffee_id")
    Single<Coffee> getCoffee(String coffee_id);




}
