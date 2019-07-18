package com.example.foodapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import com.example.foodapp.helpers.DatabaseHelper;
import com.example.foodapp.models.Item;

import java.util.ArrayList;

public class RecycleAdapterItem extends RecyclerView.Adapter<RecycleAdapterItem.ViewHolder>
{

    private ArrayList<Item> items;
    private SharedPreferences sharedPreferences;
    private Context context;
    private DatabaseHelper db;
    public RecycleAdapterItem(ArrayList<Item> items, Context context)
    {
        this.items = items;
        this.context = context;
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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        db = new DatabaseHelper(context);

        //Set Item Name
        viewHolder.name.setText(items.get(i).getName());

        //Set vegetarian icon
        if(items.get(i).getType())
            viewHolder.veg_icn.setImageResource(R.drawable.veg);
        else
            viewHolder.veg_icn.setImageResource(R.drawable.non_veg);
        Cursor c = db.getElementByCode(items.get(i).getCode());
        if(c != null && c.moveToFirst())
        {
            if(c.getInt(4)>0)
            {
                viewHolder.add_butt.setVisibility(View.INVISIBLE);
                viewHolder.item_count_layout.setVisibility(View.VISIBLE);
                viewHolder.countView.setText(String.valueOf(c.getInt(4)));
            }
        }
        else
        {
            viewHolder.add_butt.setVisibility(View.VISIBLE);
            viewHolder.item_count_layout.setVisibility(View.INVISIBLE);
        }


        viewHolder.add_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.add_butt.setVisibility(View.INVISIBLE);
                viewHolder.item_count_layout.setVisibility(View.VISIBLE);
                db.putElement(items.get(i));
                viewHolder.countView.setText("1");
            }
        });

        viewHolder.plus_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c_plus = db.getElementByCode(items.get(i).getCode());

                    c_plus.moveToFirst();
                    int q = c_plus.getInt(4);


                db.updateItem(items.get(i).getCode(),q+1);
                viewHolder.countView.setText(String.valueOf(q+1));
            }
        });
        viewHolder.minus_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c_minus = db.getElementByCode(items.get(i).getCode());

                c_minus.moveToFirst();
                int q = c_minus.getInt(4);
                if(q == 1)
                {
                    viewHolder.add_butt.setVisibility(View.VISIBLE);
                    viewHolder.item_count_layout.setVisibility(View.INVISIBLE);
                    int s = db.deleteData(items.get(i).getCode());

                }
                db.updateItem(items.get(i).getCode(),q-1);
                viewHolder.countView.setText(String.valueOf(q-1));
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



//                SharedPreferences.Editor editMinus = sharedPreferences.edit();
//                editMinus.putInt(items.get(i).getCode(), sharedPreferences.getInt(items.get(i).getCode(),0)-1);
//                editMinus.apply();
//                if(sharedPreferences.getInt(items.get(i).getCode(), 0 ) <= 0)
//                {
//                    viewHolder.add_butt.setVisibility(View.VISIBLE);
//                    viewHolder.item_count_layout.setVisibility(View.INVISIBLE);
//                }
//                else {
//                    viewHolder.countView.setText(Integer.toString(sharedPreferences.getInt(items.get(i).getCode(), 0)));
//                }

//                SharedPreferences.Editor editPlus = sharedPreferences.edit();
//                editPlus.putInt(items.get(i).getCode(), sharedPreferences.getInt(items.get(i).getCode(),0)+1);
//                editPlus.apply();
//                viewHolder.countView.setText(Integer.toString(sharedPreferences.getInt(items.get(i).getCode(), 0 )));

//viewHolder.add_butt.setVisibility(View.INVISIBLE);
////                viewHolder.item_count_layout.setVisibility(View.VISIBLE);
////                SharedPreferences.Editor edit = sharedPreferences.edit();
////                edit.putInt(items.get(i).getCode(), 1);
////                edit.apply();
////                viewHolder.countView.setText(Integer.toString(sharedPreferences.getInt(items.get(i).getCode(), 0 )));

//if (sharedPreferences.getInt(items.get(i).getCode(), 0) == 0) {
////            viewHolder.add_butt.setVisibility(View.VISIBLE);
////            viewHolder.item_count_layout.setVisibility(View.INVISIBLE);
////        } else {
////            viewHolder.add_butt.setVisibility(View.INVISIBLE);
////            viewHolder.item_count_layout.setVisibility(View.VISIBLE);
////            viewHolder.countView.setText(Integer.toString(sharedPreferences.getInt(items.get(i).getCode(), 1)));
////
////        }