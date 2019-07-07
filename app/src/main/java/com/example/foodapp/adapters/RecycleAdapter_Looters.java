package com.example.foodapp.adapters;

import android.support.annotation.NonNull;
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
import com.example.foodapp.models.Item_Looters;

import java.util.ArrayList;

public class RecycleAdapter_Looters extends RecyclerView.Adapter<RecycleAdapter_Looters.ViewHolder> {

    private ArrayList<Item_Looters> items;


    public RecycleAdapter_Looters(ArrayList<Item_Looters> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_layout, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.name.setText(items.get(i).getName());
        // viewHolder.price.setText(items.get(i).getPrice());
        // viewHolder.counter.setText(items.get(i).getCounter());
        if (items.get(i).getType())
            viewHolder.veg_icn.setImageResource(R.drawable.veg);
        else
            viewHolder.veg_icn.setImageResource(R.drawable.non_veg);

            viewHolder.add_butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewHolder.add_butt.setVisibility(Button.INVISIBLE);
                    viewHolder.item_count_layout.setVisibility(View.VISIBLE);
                    String count = viewHolder.countView.getText().toString();
                    int c = Integer.parseInt(count);
                    c = c + 1;
                    viewHolder.countView.setText(Integer.toString(c));

                }
            });
        viewHolder.plus_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = viewHolder.countView.getText().toString();
                int c = Integer.parseInt(count);
                c = c + 1;
                viewHolder.countView.setText(Integer.toString(c));
                            }
        });
        viewHolder.minus_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String count = viewHolder.countView.getText().toString();
                if(count.equals("1"))
                {
                    viewHolder.item_count_layout.setVisibility(View.INVISIBLE);
                    viewHolder.add_butt.setVisibility(View.VISIBLE);
                }
                else{
                    int c = Integer.parseInt(count);
                    c = c - 1;
                    viewHolder.countView.setText(Integer.toString(c));

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, countView, price, counter;
        ImageView veg_icn;
        LinearLayout item_count_layout;
        Button add_butt, plus_butt, minus_butt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item);
            //    price = itemView.findViewById(R.id.price);
            //  counter = itemView.findViewById(R.id.counter);
            veg_icn = itemView.findViewById(R.id.veg_icon);
            add_butt = itemView.findViewById(R.id.add_butt);
            plus_butt = itemView.findViewById(R.id.plus_butt);
            minus_butt = itemView.findViewById(R.id.minus_butt);
            countView = itemView.findViewById(R.id.countView);
            item_count_layout = itemView.findViewById(R.id.item_count_layout);

        }
    }
}
