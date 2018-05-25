package com.example.user.magicleapchallenge.view.coffeelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.magicleapchallenge.R;
import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

/**
 * Author: singh on: 24-May-18.
 */
public class CoffeeListAdapter extends RecyclerView.Adapter<CoffeeListAdapter.ViewHolder> {

    List<CoffeeItem> coffeeItems;
    Context context;

    public CoffeeListAdapter(List<CoffeeItem> coffeeItems, Context context) {
        this.coffeeItems = coffeeItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coffee_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CoffeeItem coffeeItem = coffeeItems.get(position);
        holder.tvCoffeeName.setText(coffeeItem.getName());
        holder.tvCoffeeDesc.setText(coffeeItem.getDesc());
        //Glide.with(context).load(coffeeItem.getImageUrl()).into(holder.ivCoffee);
    }

    @Override
    public int getItemCount() {
        return coffeeItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCoffeeName;
        TextView tvCoffeeDesc;
        ImageView ivCoffee;

        ViewHolder(View itemView) {
            super(itemView);
            tvCoffeeName = itemView.findViewById(R.id.tvCoffeeName);
            tvCoffeeDesc = itemView.findViewById(R.id.tvCoffeeDesc);
            ivCoffee = itemView.findViewById(R.id.ivCoffee);
        }
    }
}
