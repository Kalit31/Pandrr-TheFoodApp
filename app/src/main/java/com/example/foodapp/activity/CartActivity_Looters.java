package com.example.foodapp.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.foodapp.R;
import com.example.foodapp.adapters.CartAdapter;
import com.example.foodapp.helpers.DatabaseHelper;
import com.example.foodapp.models.Item;

import java.util.ArrayList;

public class CartActivity_Looters extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private ArrayList<Item> items;
    private ArrayList<String> uniqueItems;
    private DatabaseHelper db;
    private TextView tV_total;
    private TextView tV_cart_code;
    private int flag = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_looters);
        int total = 0;
        tV_total = findViewById(R.id.tV_total);
        items = new ArrayList<Item>();
        uniqueItems = new ArrayList<String>();
        tV_cart_code = findViewById(R.id.tV_cart_code);
        recyclerView = findViewById(R.id.recycler_cart);
        Button empty = findViewById(R.id.empty);
        db = new DatabaseHelper(this);
        Cursor c = db.getElements();

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
                Item ob = new Item(name,price, false,counter,qty,code);
                total = total + qty*Integer.parseInt(price);
                if(!uniqueItems.contains(code)) {
                    if(Integer.parseInt(code)>100) {
                        items.add(ob);
                        uniqueItems.add(code);
                    }
                }
            }while(c.moveToNext());
        }

        adapter = new CartAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tV_total.setText("Total: "+ total);
        empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAll();
                uniqueItems.clear();
                adapter.clear();
                tV_total.setText("Total: "+ 0);
            }
        });

        tV_cart_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                uniqueItems.clear();
                Cursor cursor = db.getElements();
                if(cursor.getCount()>0)
                {
                    cursor.moveToFirst();
                    do
                    {
                        String code= cursor.getString(1);
                        String name = cursor.getString(2);
                        String counter = cursor.getString(3);
                        int qty = cursor.getInt(4);
                        String price = cursor.getString(5);
                        Item ob;
                        if(flag == 0) {
                             ob = new Item(code, price, false, counter, qty, code);
                        }else {
                             ob = new Item(name, price, false, counter, qty, code);
                        }
                        if(!uniqueItems.contains(code)) {
                            if(Integer.parseInt(code)>100) {
                                items.add(ob);
                                uniqueItems.add(code);
                            }
                        }
                    }while(cursor.moveToNext());
                    if(flag == 0)
                        flag =1;
                    else
                        flag =0;
                }
                adapter = new CartAdapter(items);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });
    }
}
