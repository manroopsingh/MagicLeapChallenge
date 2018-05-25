package com.example.user.magicleapchallenge.model.source;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.model.CoffeeItem;
import com.example.user.magicleapchallenge.utils.TagUtils;

import java.util.List;

public class CoffeeRepository {

    private CoffeeDataSource remoteCoffeeSource;
    private CoffeeDataSource localCoffeeSource;
    public static final String COFFEE_ITEMS = "coffee_items";
    public static final String COFFEE_DETAIL = "coffee_detail";

    public CoffeeRepository(CoffeeDataSource remoteCoffeeSource, CoffeeDataSource localCoffeeSource) {
        this.remoteCoffeeSource = remoteCoffeeSource;
        this.localCoffeeSource = localCoffeeSource;
    }

    public void getCoffeeItems(final CoffeeDataSource.LoadCoffeeItemsCallBack callBack) {

        checkCache(COFFEE_ITEMS, null, new CheckCacheCallback() {
            @Override
            public void cacheDirtyResults(boolean isCacheDirty) {
                if (isCacheDirty) {

                    //load from the server
                    Log.d(TagUtils.get(this), "getCoffeeItems: loaded from server");
                    remoteCoffeeSource.getCoffeeItems(new CoffeeDataSource.LoadCoffeeItemsCallBack() {
                        @Override
                        public void onCoffeeItemsLoaded(List<CoffeeItem> coffeeItems) {
                            callBack.onCoffeeItemsLoaded(coffeeItems);
                            localCoffeeSource.saveCoffeeItems(coffeeItems);
                        }

                        @Override
                        public void onLoadingFailed(String error) {
                            callBack.onLoadingFailed(error);
                        }
                    });

                } else {
                    Log.d(TagUtils.get(this), "getCoffeeItems: loaded from database");
                    localCoffeeSource.getCoffeeItems(new CoffeeDataSource.LoadCoffeeItemsCallBack() {
                        @Override
                        public void onCoffeeItemsLoaded(List<CoffeeItem> coffeeItems) {
                            callBack.onCoffeeItemsLoaded(coffeeItems);

                        }

                        @Override
                        public void onLoadingFailed(String error) {
                            callBack.onLoadingFailed(error);
                        }
                    });
                }
            }
        });


    }

    public void getCoffeeDetails(final String coffee_id, final CoffeeDataSource.LoadCoffeeDetailCallBack callBack) {


        checkCache(COFFEE_DETAIL, coffee_id, new CheckCacheCallback() {
            @Override
            public void cacheDirtyResults(boolean isCacheDirty) {

                Log.d(TagUtils.get(this), "cacheDirtyResults: " + isCacheDirty);
                if (isCacheDirty) {
                    Log.d(TagUtils.get(this), "cacheDirtyResults: from server");
                    remoteCoffeeSource.getCoffeeDetails(coffee_id, new CoffeeDataSource.LoadCoffeeDetailCallBack() {
                        @Override
                        public void onCoffeeDetailLoaded(Coffee coffee) {
                            callBack.onCoffeeDetailLoaded(coffee);
                            localCoffeeSource.saveCoffee(coffee);
                        }

                        @Override
                        public void onLoadingFailed(String error) {
                            callBack.onLoadingFailed(error);

                        }
                    });
                } else {
                    Log.d(TagUtils.get(this), "cacheDirtyResults: from db");
                    localCoffeeSource.getCoffeeDetails(coffee_id, new CoffeeDataSource.LoadCoffeeDetailCallBack() {
                        @Override
                        public void onCoffeeDetailLoaded(Coffee coffee) {
                            callBack.onCoffeeDetailLoaded(coffee);
                        }

                        @Override
                        public void onLoadingFailed(String error) {
                            callBack.onLoadingFailed(error);
                        }
                    });
                }

            }
        });

    }

    private void checkCache(String cacheType, @Nullable String coffee_id, @Nullable CheckCacheCallback cacheCallback) {

        localCoffeeSource.isCacheDirty(cacheType, coffee_id, cacheCallback);
    }

    public interface CheckCacheCallback {
        void cacheDirtyResults(boolean isCacheDirty);
    }


}
