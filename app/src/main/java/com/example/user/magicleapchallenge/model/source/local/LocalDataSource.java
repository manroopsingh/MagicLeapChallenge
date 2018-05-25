package com.example.user.magicleapchallenge.model.source.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;
import com.example.user.magicleapchallenge.model.source.CoffeeDataSource;
import com.example.user.magicleapchallenge.model.source.CoffeeRepository;
import com.example.user.magicleapchallenge.model.source.local.room.CoffeeDao;
import com.example.user.magicleapchallenge.model.source.local.room.CoffeeDatabase;
import com.example.user.magicleapchallenge.model.source.local.room.CoffeeItemDao;
import com.example.user.magicleapchallenge.utils.TagUtils;

import java.util.List;

import static android.arch.persistence.room.Room.databaseBuilder;

public class LocalDataSource implements CoffeeDataSource {

    public static final String COFFEE_ITEM_CACHE_TIME = "coffeeItemCache";
    public static final String COFFEE_ITEM_DEF = "default";
    public static final String COFFEE_CACHE_TIME = "coffeeCache";
    public static final String COFFEE_DEF = "default";
    public static final String MY_SHARED_PREF = "mySharedPref";

    private final CoffeeDatabase db;
    private CoffeeDao coffeeDao;
    private CoffeeItemDao coffeeItemDao;
    private Context context;

    public LocalDataSource(Context context) {

        this.context = context;
        db = databaseBuilder(context,
                CoffeeDatabase.class, "database-name").build();
        coffeeDao = db.coffeeDao();
        coffeeItemDao = db.coffeeItemDao();
    }

    @Override
    public void getCoffeeItems(final LoadCoffeeItemsCallBack callBack) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<CoffeeItem> coffeeItems = coffeeItemDao.getAll();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onCoffeeItemsLoaded(coffeeItems);
                    }
                });
            }
        }).start();
    }

    @Override
    public void getCoffeeDetails(final String coffee_id, final LoadCoffeeDetailCallBack callBack) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Coffee coffee = coffeeDao.getCoffee(coffee_id);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onCoffeeDetailLoaded(coffee);
                    }
                });
            }
        }).start();

    }

    @Override
    public void saveCoffeeItems(final List<CoffeeItem> coffeeItems) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.coffeeItemDao().insertAll(coffeeItems);
                updateCacheTime(COFFEE_ITEM_CACHE_TIME, getCurrentTime());
            }
        }).start();

    }

    @Override
    public void saveCoffee(final Coffee coffee) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.coffeeDao().saveCoffee(coffee);
                updateCacheTime(COFFEE_CACHE_TIME, getCurrentTime());
            }
        }).start();

    }

    @Override
    public void isCacheDirty(String cacheType, @Nullable String coffee_id, final CoffeeRepository.CheckCacheCallback cacheCallback) {

        // TODO: 5/25/18 Check dirty cache as per decay time

        String cacheTime;

        switch (cacheType) {
            case CoffeeRepository.COFFEE_ITEMS:

                cacheTime = context
                        .getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE)
                        .getString(COFFEE_ITEM_CACHE_TIME, COFFEE_ITEM_DEF);


                if (cacheTime.equals("latest")) cacheCallback.cacheDirtyResults(false);
                else cacheCallback.cacheDirtyResults(true);

                break;

            case CoffeeRepository.COFFEE_DETAIL:

                cacheTime = context
                        .getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE)
                        .getString(COFFEE_CACHE_TIME, COFFEE_DEF);


                final String finalCacheTime = cacheTime;
                checkIfCoffeeExists(coffee_id, new CheckIfExistsCallback() {
                    @Override
                    public void onCoffeeExistsResult(boolean exists) {

                        if (exists && finalCacheTime.equals("latest")) {

                            cacheCallback.cacheDirtyResults(false);

                        }else cacheCallback.cacheDirtyResults(true);
                    }
                });
                break;
        }

    }

    private void checkIfCoffeeExists(final String coffee_id, final CheckIfExistsCallback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                final Coffee coffee = coffeeDao.getCoffee(coffee_id);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        callback.onCoffeeExistsResult(coffee != null);
                    }
                });

            }
        }).start();
    }

    @Override
    public void updateCacheTime(String cacheType, String time) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (cacheType) {
            case COFFEE_ITEM_CACHE_TIME:
                editor.putString(cacheType, time);
                break;
            case COFFEE_CACHE_TIME:
                editor.putString(cacheType, time);
                break;

        }
        editor.commit();
    }


    private String getCurrentTime() {
        // TODO: 5/25/18 Implement current time
        return "latest";
    }

    interface CheckIfExistsCallback {
        void onCoffeeExistsResult(boolean exists);
    }

}