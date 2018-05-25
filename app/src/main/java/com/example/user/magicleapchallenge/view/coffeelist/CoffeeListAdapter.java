package com.example.user.magicleapchallenge.view.coffeelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.magicleapchallenge.R;
import com.example.user.magicleapchallenge.model.CoffeeItem;

import java.util.List;

/**
 * Author: singh on: 24-May-18.
 */
public class CoffeeListAdapter extends RecyclerView.Adapter<CoffeeListAdapter.ViewHolder> {

    private List<CoffeeItem> coffeeItems;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public CoffeeListAdapter(List<CoffeeItem> coffeeItems, Context context) {
        this.coffeeItems = coffeeItems;
        this.context = context;
        this.onItemClickListener = (OnItemClickListener) context;
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
        setCoffeeImage(holder.ivCoffee, coffeeItem);

    }

    private void setCoffeeImage(ImageView ivCoffee, CoffeeItem coffeeItem) {
        if(!coffeeItem.getImageUrl().isEmpty())
        Glide.with(context).load(coffeeItem.getImageUrl()).into(ivCoffee);
    }

    @Override
    public int getItemCount() {
        return coffeeItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvCoffeeName;
        TextView tvCoffeeDesc;
        ImageView ivCoffee;

        ViewHolder(View itemView) {
            super(itemView);
            tvCoffeeName = itemView.findViewById(R.id.tvCoffeeName);
            tvCoffeeDesc = itemView.findViewById(R.id.tvCoffeeDesc);
            ivCoffee = itemView.findViewById(R.id.ivCoffee);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClicked(coffeeItems.get(getLayoutPosition()).getId());
        }
    }

    interface OnItemClickListener{
        void onItemClicked(String coffee_id);
    }
}
