package com.example.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LootersActivity extends AppCompatActivity {

    private Button cart_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looters);


        cart_butt= findViewById(R.id.cart_butt);
        cart_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(LootersActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });
    }
}
