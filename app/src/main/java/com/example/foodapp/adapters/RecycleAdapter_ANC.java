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

import java.util.ArrayList;

public class RecycleAdapter_ANC extends RecyclerView.Adapter<RecycleAdapter_ANC.ViewHolder>
{

    private ArrayList<Item_ANC> items;


    public RecycleAdapter_ANC(ArrayList<Item_ANC> items)
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
      //      viewHolder.price.setText("Price: "+items.get(i).getPrice());
        //    viewHolder.counter.setText("Counter: "+items.get(i).getCounter());
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
            name = itemView.findViewById(R.id.item);
           // price = itemView.findViewById(R.id.price);
            //counter = itemView.findViewById(R.id.counter);
            veg_icn = itemView.findViewById(R.id.veg_icon);
        }
    }
}
