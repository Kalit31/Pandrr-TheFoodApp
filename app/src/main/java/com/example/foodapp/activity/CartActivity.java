package com.example.foodapp.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.example.foodapp.R;
import com.example.foodapp.adapters.CartAdapter;
import com.example.foodapp.helpers.DatabaseHelper;
import com.example.foodapp.models.Item;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_cart);
        int total = 0;
        db = new DatabaseHelper(this);
        Cursor c = db.getElements();
        tV_total = findViewById(R.id.tV_total);
        items = new ArrayList<Item>();
        uniqueItems = new ArrayList<String>();
        recyclerView = findViewById(R.id.recycler_cart);

        if(c.getCount()>0)
        {
            c.moveToFirst();
            while(c.moveToNext())
            {
                String code= c.getString(1);
                String name = c.getString(2);
                String counter = c.getString(3);
                int qty = c.getInt(4);
                String price = c.getString(5);
                Item ob = new Item(name,price, false,counter,qty,code);
                total = total + qty*Integer.parseInt(price);
                if(!uniqueItems.contains(code)) {
                    items.add(ob);
                    uniqueItems.add(code);
                }
            }
        }
        adapter = new CartAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tV_total.setText("Total: "+ total);
    }
}
