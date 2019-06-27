package com.example.foodapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;

import static com.example.foodapp.activity.PopUpANC.PUBLIC_STATIC_STRING_IDENTIFIER;

public class PopUpLooters extends AppCompatActivity {

    private String categorySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_looters);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        Button burgers = findViewById(R.id.button_burgers);
        Button chinese = findViewById(R.id.button_chinese);
        Button hotItems = findViewById(R.id.button_hotItem);
        Button mojito = findViewById(R.id.button_mojito);
        Button pizza = findViewById(R.id.button_pizza);
        Button sw = findViewById(R.id.button_sw);
        Button shakes = findViewById(R.id.button_shakes);
        Button subs = findViewById(R.id.button_subs);
        Button wraps = findViewById(R.id.button_wraps);

        burgers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Burgers";
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

        hotItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Hot Items";
                resultData(categorySelected);
            }
        });
        mojito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected= "Mojito";
                resultData(categorySelected);
            }
        });
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Pizzas";
                resultData(categorySelected);
            }
        });
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Sandwich";
                resultData(categorySelected);
            }
        });
        shakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 categorySelected="Shakes";
                resultData(categorySelected);
            }
        });
        subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Subs";
                resultData(categorySelected);
            }
        });
        wraps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected="Wraps";
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
