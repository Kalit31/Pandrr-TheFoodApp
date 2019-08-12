package com.example.foodapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.common.Common;
import com.example.foodapp.models.Item;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Item> items;

    public CartAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType == Common.VIEWTYPE_GROUP)
        {
            ViewGroup group = (ViewGroup)inflater.inflate(R.layout.group_item_cart,viewGroup,false);
            return new GroupViewHolder(group);
        }
        else if(viewType == Common.VIEWTYPE_ITEM) {
            ViewGroup group = (ViewGroup)inflater.inflate(R.layout.cart_layout, viewGroup, false);
            return new ItemViewHolder(group);
        }
        else
        {
            ViewGroup group = (ViewGroup)inflater.inflate(R.layout.group_item_cart,viewGroup,false);
            return new GroupViewHolder(group);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(viewHolder instanceof GroupViewHolder)
        {
            GroupViewHolder groupViewHolder = (GroupViewHolder)viewHolder;
//            groupViewHolder.counter_title.setText(String.format("Counter Number: %s", items.get(i).getCounter()));
       }
        else if(viewHolder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            itemViewHolder.name.setText(items.get(i).getName());
            itemViewHolder.qty.setText(String.valueOf(items.get(i).getItemCount()));
            itemViewHolder.price.setText(String.valueOf(items.get(i).getItemCount()*Integer.parseInt(items.get(i).getPrice())));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public void clear() {
        int size = items.size();
        items.clear();
        notifyItemRangeRemoved(0, size);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

       TextView name, price, qty;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_item);
            price = itemView.findViewById(R.id.cart_price);
            qty = itemView.findViewById(R.id.cart_qty);
        }
    }

    private class GroupViewHolder extends RecyclerView.ViewHolder
    {
        TextView counter_title;
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            counter_title = itemView.findViewById(R.id.counter_grp_title);
        }
    }
}
