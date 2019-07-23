package com.example.foodapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;

public class PopUpANC extends AppCompatActivity {

    private String categorySelected;
    public static final String PUBLIC_STATIC_STRING_IDENTIFIER = "iden";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_anc);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));


        Button parathas = findViewById(R.id.button_parathas);
        Button sandwiches = findViewById(R.id.button_sw);
        Button south = findViewById(R.id.button_southInd);
        Button maggi = findViewById(R.id.button_maggi);
        Button snacks = findViewById(R.id.button_snacks);
        Button chinese = findViewById(R.id.button_chinese);
        Button indian = findViewById(R.id.button_ind);
        Button tandoor = findViewById(R.id.button_tandoor);
        Button bvg = findViewById(R.id.button_bvrg);

        parathas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               categorySelected = "Parathas";
               resultData(categorySelected);
            }
        });

        sandwiches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected = "Sandwiches";
                resultData(categorySelected);
            }
        });

        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="South Indian";
                resultData(categorySelected);
                }
        });
        maggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Maggi";
                resultData(categorySelected);
            }
        });
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Snacks";
                resultData(categorySelected);
            }
        });
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               categorySelected="Chinese";
                resultData(categorySelected);
            }
        });
        indian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Indian";
                resultData(categorySelected);
            }
        });
        tandoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Tandoor";
                resultData(categorySelected);
            }
        });
        bvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Beverages";
                resultData(categorySelected);
            }
        });
    }

    private void resultData(String str)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER,str);
        setResult(Activity.RESULT_OK,resultIntent);
        finish();
    }

}
