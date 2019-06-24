package com.example.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

public class HomeActivity extends AppCompatActivity {


    Button info_butt;
    Button others_butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        info_butt = findViewById(R.id.info_button);
        others_butt= findViewById(R.id.others_butt);

        info_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infointent = new Intent(HomeActivity.this, InfoActivity.class);
                startActivity(infointent);
            }
        });
        others_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent others = new Intent(HomeActivity.this, OthersAct.class);
                startActivity(others);
            }
        });

    }
}
