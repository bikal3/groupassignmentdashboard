package com.example.groupproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView alcohol,soil,pulse,rfid;
    String status;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alcohol=findViewById(R.id.alcoholsensorvalue);
        soil=findViewById(R.id.soilsensorvalue);
        pulse=findViewById(R.id.pulsesensorvalue);
        rfid=findViewById(R.id.rfidsensorvalue);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        Query alcoholquery=databaseReference.child("alcohol").orderByKey().limitToLast(1);
        Query plusequery=databaseReference.child("pulse").orderByKey().limitToLast(1);
        Query rfidquery=databaseReference.child("RFID/name").orderByKey().limitToLast(1);
        Query soilquery=databaseReference.child("soil").orderByKey().limitToLast(1);
        soilquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status=dataSnapshot.child("value").getValue().toString();
                soil.setText(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rfidquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status=dataSnapshot.child("").getValue().toString();
                rfid.setText(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        plusequery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status=dataSnapshot.child("value").getValue().toString();
                pulse.setText(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, " Pulse Database Error", Toast.LENGTH_SHORT).show();
            }
        });
        alcoholquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status=dataSnapshot.child("value").getValue().toString();
                alcohol.setText(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, " Alcohol Database Error"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }
}
