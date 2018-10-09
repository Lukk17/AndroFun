package com.lukk.db;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lukk.androfun.R;

public class DBActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        DatabaseReference users = fb.child("user");
        DatabaseReference user1 = users.child("1");

        user1.push();
        user1.child("email").setValue("user1@email.com");

        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("onDataChange", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("onCancelled", "Failed to read value.", error.toException());
            }
        });


    }



}
