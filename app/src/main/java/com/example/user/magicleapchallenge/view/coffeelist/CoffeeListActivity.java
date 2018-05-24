package com.example.user.magicleapchallenge.view.coffeelist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.magicleapchallenge.R;
import com.example.user.magicleapchallenge.model.CoffeeItem;
import com.example.user.magicleapchallenge.model.source.CoffeeDataSource;
import com.example.user.magicleapchallenge.model.source.CoffeeRepository;
import com.example.user.magicleapchallenge.model.source.local.LocalDataSource;
import com.example.user.magicleapchallenge.model.source.remote.RemoteDataSource;
import com.example.user.magicleapchallenge.model.source.remote.RemoteService;
import com.example.user.magicleapchallenge.utils.TagUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoffeeListActivity extends AppCompatActivity implements CoffeeDataSource.LoadCoffeeItemsCallBack{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list);


        RemoteDataSource remoteDataSource = new RemoteDataSource();
        LocalDataSource localDataSource = new LocalDataSource();

        CoffeeRepository coffeeRepository = new CoffeeRepository(remoteDataSource, localDataSource);
//        coffeeRepository.getCoffeeItems(this);


        remoteDataSource.getCoffeeItemCall().enqueue(new Callback<List<CoffeeItem>>() {
            @Override
            public void onResponse(Call<List<CoffeeItem>> call, Response<List<CoffeeItem>> response) {
                Log.d(TagUtils.get(this), "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<CoffeeItem>> call, Throwable t) {

                Log.d(TagUtils.get(this), "onFailure: ");
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coffee_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCoffeeItemsLoaded(List<CoffeeItem> coffeeItems) {
        Log.d("MYTAG", "onCoffeeItemsLoaded: "+ coffeeItems.size());
    }

    @Override
    public void onLoadingFailed(String error) {

        Log.d("MYTAG", "onLoadingFailed: "+ error);
    }
}
