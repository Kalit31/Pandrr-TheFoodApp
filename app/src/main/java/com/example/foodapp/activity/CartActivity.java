package com.example.foodapp.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.foodapp.R;
import com.example.foodapp.adapters.CartAdapter;
import com.example.foodapp.helpers.DatabaseHelper;
import com.example.foodapp.models.Item;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private ArrayList<Item> items;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor c = db.getElements();

        items = new ArrayList<Item>();
        recyclerView = findViewById(R.id.recycler_cart);
        items.clear();
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
                items.add(ob);
            }
        }
        items.add(new Item("food","30",false,"6",1,"700"));
        adapter = new CartAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
