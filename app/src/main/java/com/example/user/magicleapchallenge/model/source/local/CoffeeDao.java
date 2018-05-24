package com.example.user.magicleapchallenge.model.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

@Dao
public interface CoffeeDao  {

    @Insert
    void saveCoffee(Coffee coffee);

    @Insert
    void insertAll(Coffee... coffees);

    @Query("SELECT * FROM Coffee")
    List<CoffeeItem> getAll();


}
