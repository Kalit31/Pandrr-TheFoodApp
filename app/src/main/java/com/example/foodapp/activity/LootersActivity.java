package com.example.foodapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapters.RecycleAdapterItem;
import com.example.foodapp.models.Item;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.foodapp.activity.ANCact.STATIC_INTEGER_VALUE;
import static com.example.foodapp.activity.PopUpANC.PUBLIC_STATIC_STRING_IDENTIFIER;

public class LootersActivity extends AppCompatActivity {

    private Button cart_butt;
    private RecyclerView recyclerView;
    private Button menu_butt;
    private RecycleAdapterItem adapter;
    private ArrayList<Item> items;
    private String catItem="Pizzas";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looters);
        FirebaseApp.initializeApp(this);

        cart_butt= findViewById(R.id.cart_butt);
        cart_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(LootersActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

        menu_butt = findViewById(R.id.menu_looters_btn);
        recyclerView = findViewById(R.id.looters_rv);
        firebaseDatabase = FirebaseDatabase.getInstance();
        items = new ArrayList<>();

              menu_butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent popintent = new Intent(LootersActivity.this, PopUpLooters.class);
                    startActivityForResult(popintent, STATIC_INTEGER_VALUE);
                   }
            });

              progressDialog = new ProgressDialog(LootersActivity.this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == STATIC_INTEGER_VALUE && resultCode == Activity.RESULT_OK)
        {
            catItem = data.getStringExtra(PUBLIC_STATIC_STRING_IDENTIFIER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressDialog.setMessage("Fetching menu");
        progressDialog.show();
        myRef = firebaseDatabase.getReference("Looters").child(catItem);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    items.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Item item = ds.getValue(Item.class);
                        items.add(item);
                    }
                    adapter = new RecycleAdapterItem(items, getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
