package com.example.user.magicleapchallenge.model.source.local.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CoffeeItemDao {

    @Insert
    void saveCoffeeItem(CoffeeItem coffeeItem);

    @Insert
    void insertAll(List<CoffeeItem> coffeeItems);

    @Query("SELECT * FROM CoffeeItem")
    Flowable<List<CoffeeItem>> getAll();



}
