package com.example.user.magicleapchallenge.view.coffeelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.user.magicleapchallenge.MagicLeapApplication;
import com.example.user.magicleapchallenge.R;
import com.example.user.magicleapchallenge.model.CoffeeItem;
import com.example.user.magicleapchallenge.view.base.BaseActivity;
import com.example.user.magicleapchallenge.view.coffeedetail.CoffeeDetailActivity;

import java.util.List;

import javax.inject.Inject;

public class CoffeeListActivity extends BaseActivity implements
        CoffeeListContract.View
        , CoffeeListAdapter.OnItemClickListener{


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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding coffees coming soon", Snackbar.LENGTH_LONG)
                        .setAction("Add Coffee", null).show();
            }
        });
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

        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(String coffee_id) {
        Intent intent = new Intent(getApplicationContext(), CoffeeDetailActivity.class);
        intent.putExtra("coffee", coffee_id);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
        MagicLeapApplication.get(this).clearCoffeeListComponent();

    }
}
