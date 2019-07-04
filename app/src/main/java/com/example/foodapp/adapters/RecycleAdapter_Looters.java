package com.example.foodapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.models.Item_ANC;
import com.example.foodapp.models.Item_Looters;

import java.util.ArrayList;

public class RecycleAdapter_Looters extends RecyclerView.Adapter<RecycleAdapter_Looters.ViewHolder>
{

    private ArrayList<Item_Looters> items;


    public RecycleAdapter_Looters(ArrayList<Item_Looters> items)
    {
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_layout,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.name.setText(items.get(i).getName());
            viewHolder.price.setText(items.get(i).getPrice());
            viewHolder.counter.setText(items.get(i).getCounter());
            if(items.get(i).getType())
                viewHolder.veg_icn.setImageResource(R.drawable.veg);
            else
                viewHolder.veg_icn.setImageResource(R.drawable.non_veg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,counter;
        ImageView veg_icn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            counter = itemView.findViewById(R.id.counter);
            veg_icn = itemView.findViewById(R.id.vegetarian_image);
        }
    }
}
