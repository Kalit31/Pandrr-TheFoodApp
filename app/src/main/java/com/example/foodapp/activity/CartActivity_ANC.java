package com.example.foodapp.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.adapters.CartAdapter;
import com.example.foodapp.common.Common;
import com.example.foodapp.helpers.DatabaseHelper;
import com.example.foodapp.models.Item;

import java.util.ArrayList;

public class  CartActivity_ANC extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private ArrayList<Item> items;
    private ArrayList<String> uniqueItems;
    private DatabaseHelper db;
    private TextView tV_total;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_anc);

        int total = 0;
        db = new DatabaseHelper(this);
        Cursor c = db.getElements();
        tV_total = findViewById(R.id.tV_total_anc);
        items = new ArrayList<Item>();
        uniqueItems = new ArrayList<String>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_cart_anc);
        Button empty = findViewById(R.id.empty_anc);
         if(c.getCount()>0)
        {
            c.moveToFirst();
            do
            {
                String code= c.getString(1);
                String name = c.getString(2);
                String counter = c.getString(3);
                int qty = c.getInt(4);
                String price = c.getString(5);
                Item ob = new Item(name,price, false,counter,qty,code,-1);

                if(!uniqueItems.contains(code)) {
                    if(Integer.parseInt(code)<100) {
                        items.add(ob);
                        total = total + qty*Integer.parseInt(price);
                        uniqueItems.add(code);
                    }
                }
            }while(c.moveToNext());
        }

        empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAll();
                uniqueItems.clear();
                adapter.clear();
                tV_total.setText("Total: "+ 0);
            }
        });
         items = Common.sortList(items);
         items = Common.addCounters(items);
        adapter = new CartAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        tV_total.setText("Total: "+ total);
    }
}
