package com.example.user.magicleapchallenge.view.coffeedetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.magicleapchallenge.MagicLeapApplication;
import com.example.user.magicleapchallenge.R;
import com.example.user.magicleapchallenge.model.Coffee;
import com.example.user.magicleapchallenge.utils.ToastIt;
import com.example.user.magicleapchallenge.utils.Utils;
import com.example.user.magicleapchallenge.view.base.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

public class CoffeeDetailActivity extends BaseActivity
        implements CoffeeDetailContract.View, View.OnClickListener {

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
        setupToolbar();
        getCoffeeId();
        setupFabButton();
    }

    private void getCoffeeId() {
        coffee_id = getIntent().getStringExtra("coffee");
    }

    private void setupFabButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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
        Utils.showToast(this, error);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
        MagicLeapApplication.get(this).clearCoffeeDetailComponent();
    }

    @Override
    public void onClick(View view) {
        Utils.showSnack(view, "Loading more information", "More info");
    }
}
