package com.example.user.magicleapchallenge.view.coffeelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.user.magicleapchallenge.MagicLeapApplication;
import com.example.user.magicleapchallenge.R;
import com.example.user.magicleapchallenge.model.CoffeeItem;

import com.example.user.magicleapchallenge.utils.IntentBuilder;
import com.example.user.magicleapchallenge.utils.Utils;
import com.example.user.magicleapchallenge.view.base.BaseActivity;
import com.example.user.magicleapchallenge.view.coffeedetail.CoffeeDetailActivity;

import java.util.List;

import javax.inject.Inject;

public class CoffeeListActivity extends BaseActivity implements
        CoffeeListContract.View,
        CoffeeListAdapter.OnItemClickListener,
        View.OnClickListener {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CoffeeListAdapter coffeeListAdapter;

    @Inject
    CoffeeListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list);

        setupInjections();
        bindViews();
        setupToolbar();
        setupFabButton();
    }

    private void setupFabButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.rvCoffeeList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
        presenter.getCoffeeItems();

    }

    private void setupInjections() {
        MagicLeapApplication.get(this).getCoffeeListComponent().inject(this);
    }

    @Override
    public void onCoffeeItemsLoaded(List<CoffeeItem> coffeeItems) {
        coffeeListAdapter = new CoffeeListAdapter(coffeeItems, this);
        recyclerView.setAdapter(coffeeListAdapter);
    }

    @Override
    public void showError(String error) {
        Utils.showToast(this, error);
    }

    @Override
    public void onItemClicked(String coffee_id) {
        IntentBuilder.createWith(this)
                .setTarget(CoffeeDetailActivity.class)
                .addExtra("coffee", coffee_id)
                .startActivity();

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
        MagicLeapApplication.get(this).clearCoffeeListComponent();

    }

    @Override
    public void onClick(View view) {
        Utils.showSnack(view, "Adding coffees coming soon", "Add coffee");
    }
}
