package com.example.user.magicleapchallenge.model.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

@Dao
public interface CoffeeItemDao {

    @Insert
    void saveCoffeeItem(CoffeeItem coffeeItem);

    @Insert
    void insertAll(CoffeeItem... coffeeItems);

    @Query("SELECT * FROM CoffeeItem")
    List<CoffeeItem> getAll();


}
