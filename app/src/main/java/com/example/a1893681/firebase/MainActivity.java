package com.example.a1893681.firebase;

import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txt_nm;
    ArrayList<GitHub> vals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    vals = new ArrayList<>();
        txt_nm=findViewById(R.id.txt_nm);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("items");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               //String nm = dataSnapshot.child("0").child("name").getValue().toString();

               // txt_nm.setText(nm);
                Iterable<DataSnapshot> childs = dataSnapshot.getChildren();
                for(DataSnapshot snap : childs)
                {
                    GitHub git = snap.getValue(GitHub.class);
                    System.out.println(git.getId()+" " +git.getWatchers()+ " " + git.getFull_name());

                   // System.out.println(snap.child("name").getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        }
    }

