package com.example.foodapp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.models.Item_ANC;

import java.util.ArrayList;
import java.util.Objects;

public class RecycleAdapter_ANC extends RecyclerView.Adapter<RecycleAdapter_ANC.ViewHolder>
{

    private ArrayList<Item_ANC> items;
    private SharedPreferences sharedPreferences;

    public RecycleAdapter_ANC(ArrayList<Item_ANC> items, Context context)
    {
        this.items = items;
        sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_layout,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        //Set Item Name
        viewHolder.name.setText(items.get(i).getName());

        //Set vegetarian icon
        if(items.get(i).getType())
            viewHolder.veg_icn.setImageResource(R.drawable.veg);
        else
            viewHolder.veg_icn.setImageResource(R.drawable.non_veg);

        if (sharedPreferences.getInt(items.get(i).getCode(), 0) == 0) {
            viewHolder.add_butt.setVisibility(View.VISIBLE);
            viewHolder.item_count_layout.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.add_butt.setVisibility(View.INVISIBLE);
            viewHolder.item_count_layout.setVisibility(View.VISIBLE);
            viewHolder.countView.setText(Integer.toString(sharedPreferences.getInt(items.get(i).getCode(), 1)));

        }

        viewHolder.add_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.add_butt.setVisibility(View.INVISIBLE);
                viewHolder.item_count_layout.setVisibility(View.VISIBLE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt(items.get(i).getCode(), 1);
                edit.apply();
                viewHolder.countView.setText(Integer.toString(sharedPreferences.getInt(items.get(i).getCode(), 0 )));

            }
        });

        viewHolder.plus_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editPlus = sharedPreferences.edit();
                editPlus.putInt(items.get(i).getCode(), sharedPreferences.getInt(items.get(i).getCode(),0)+1);
                editPlus.apply();
                viewHolder.countView.setText(Integer.toString(sharedPreferences.getInt(items.get(i).getCode(), 0 )));
            }
        });
        viewHolder.minus_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editMinus = sharedPreferences.edit();
                editMinus.putInt(items.get(i).getCode(), sharedPreferences.getInt(items.get(i).getCode(),0)-1);
                editMinus.apply();
                if(sharedPreferences.getInt(items.get(i).getCode(), 0 ) <= 0)
                {
                    viewHolder.add_butt.setVisibility(View.VISIBLE);
                    viewHolder.item_count_layout.setVisibility(View.INVISIBLE);
                }
                else {
                    viewHolder.countView.setText(Integer.toString(sharedPreferences.getInt(items.get(i).getCode(), 0)));
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,countView;
        ImageView veg_icn;
        Button add_butt,plus_butt,minus_butt;
        LinearLayout item_count_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item);
            veg_icn = itemView.findViewById(R.id.veg_icon);
            add_butt = itemView.findViewById(R.id.add_butt);
            item_count_layout = itemView.findViewById(R.id.item_count_layout);
            plus_butt = itemView.findViewById(R.id.plus_butt);
            minus_butt = itemView.findViewById(R.id.minus_butt);
            countView = itemView.findViewById(R.id.countView);
        }
    }
}
