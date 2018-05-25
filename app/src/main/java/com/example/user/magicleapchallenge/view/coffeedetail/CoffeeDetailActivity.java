package com.example.user.magicleapchallenge.view.coffeedetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.magicleapchallenge.MagicLeapApplication;
import com.example.user.magicleapchallenge.R;
import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.view.base.BaseActivity;

import javax.inject.Inject;

public class CoffeeDetailActivity extends BaseActivity implements CoffeeDetailContract.View{

    @Inject
    CoffeeDetailPresenter presenter;

    String coffee_id;
    private TextView tvCoffeeName;
    private TextView tvCoffeeDesc;
    private ImageView ivCoffee;
    private TextView tvCoffeeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_detail);
        setupInjections();
        bindViews();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        coffee_id = getIntent().getStringExtra("coffee");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void bindViews() {
        tvCoffeeName = findViewById(R.id.tvCoffeeName);
        tvCoffeeDesc = findViewById(R.id.tvCoffeeDesc);
        ivCoffee = findViewById(R.id.ivCoffee);
        tvCoffeeTime = findViewById(R.id.tvCoffeeTime);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
        presenter.getCoffeeDetails(coffee_id);

    }

    private void setupInjections() {
        MagicLeapApplication.get(this).getCoffeeDetailComponent().inject(this);
    }

    @Override
    public void onCoffeeDetailsLoaded(Coffee coffee) {
        tvCoffeeName.setText(coffee.getName());
        tvCoffeeDesc.setText(coffee.getDesc());
        if (!coffee.getImageUrl().isEmpty()) {
            Glide.with(this).load(coffee.getImageUrl()).into(ivCoffee);
        }
        tvCoffeeTime.setText(coffee.getLastUpdatedAt());
    }

    @Override
    public void showError(String error) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_coffee_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}
