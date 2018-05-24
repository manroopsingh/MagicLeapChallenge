package com.example.user.magicleapchallenge.model.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;

@Database(entities = {CoffeeItem.class, Coffee.class}, version = 1)
public abstract class CoffeeDatabase extends RoomDatabase{


}
