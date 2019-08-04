package com.example.foodapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;


public class HomeActivity extends AppCompatActivity {

    private Button info_butt;
    private Button others_butt;
    private Button anc_butt;
    private Button looters_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        info_butt = findViewById(R.id.info_button);
        anc_butt = findViewById(R.id.anc_butt);
        looters_butt = findViewById(R.id.looters_butt);

        info_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infointent = new Intent(HomeActivity.this, InfoActivity.class);
                startActivity(infointent);
            }
        });
        anc_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ancIntent = new Intent(HomeActivity.this,ANCact.class);
                startActivity(ancIntent);
            }
        });
        looters_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lootersIntent = new Intent(HomeActivity.this, LootersActivity.class);
                startActivity(lootersIntent);
            }
        });
    }
}
