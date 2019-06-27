package com.example.foodapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapters.RecycleAdapter_ANC;
import com.example.foodapp.models.Item_ANC;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ANCact extends AppCompatActivity {

    private Button cart_butt;
    private RecyclerView recyclerView;
    private RecycleAdapter_ANC adapter;
    private ArrayList<Item_ANC> items;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    public static final String TAG = "Tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ancact);
        FirebaseApp.initializeApp(this);

        cart_butt = findViewById(R.id.cart_butt);
        cart_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(ANCact.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

        recyclerView = findViewById(R.id.anc_rv);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("ANC").child("Parathas");

        items = new ArrayList<>();


     }

    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    items.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Item_ANC item = ds.getValue(Item_ANC.class);
                        items.add(item);
                    }
                    adapter = new RecycleAdapter_ANC(items);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);

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
