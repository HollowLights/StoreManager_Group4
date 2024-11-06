package com.example.storemanager_group4.activity.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BaseActivity extends AppCompatActivity{
    public FirebaseDatabase database;
    public FirebaseAuth auth;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase instances here
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        databaseReference = database.getReference();  // Initialize the database reference
    }

    // Method to write data to the database
    protected void writeData(String path, Object value) {
        databaseReference.child(path).setValue(value)
                .addOnSuccessListener(aVoid -> Log.d("Firebase", "Data written successfully"))
                .addOnFailureListener(e -> Log.e("Firebase", "Data write failed", e));
    }

    // Method to read data from the database
    protected void readData(String path, OnDataReceivedListener listener) {
        databaseReference.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onDataReceived(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to read data", error.toException());
            }
        });
    }

    // Example method to listen for real-time changes in a path
    protected void listenForChanges(String path, OnDataChangedListener listener) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onDataChanged(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to listen for changes", error.toException());
            }
        };
        databaseReference.child(path).addValueEventListener(valueEventListener);
    }

    // Cleanup listener to prevent memory leaks
    protected void removeListener(String path, ValueEventListener listener) {
        databaseReference.child(path).removeEventListener(listener);
    }

    // Callback interfaces
    public interface OnDataReceivedListener {
        void onDataReceived(DataSnapshot snapshot);
    }

    public interface OnDataChangedListener {
        void onDataChanged(DataSnapshot snapshot);
    }
}
