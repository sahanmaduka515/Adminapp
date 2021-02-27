package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.pojo.newsfeed;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Newsfeed extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

       TextView t = findViewById(R.id.news);
       TextView t1 = findViewById(R.id.desc);
       Button b1 = findViewById(R.id.addnews);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        String s = t.getText().toString();
        String s1 = t1.getText().toString();
        newsfeed newsfeed = new newsfeed();
        newsfeed.setName(s);
        newsfeed.setDesc(s1);

        db.collection("NewsFeed").add(newsfeed).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(Newsfeed.this, "Newsfeedsuccess", Toast.LENGTH_SHORT).show();
            }
        });

            }
        });


    }
}