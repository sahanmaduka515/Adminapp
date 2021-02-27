package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        TextView titt = findViewById(R.id.titt);
        TextView content = findViewById(R.id.content);
        Button add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        String s = titt.getText().toString();
        String s1 = content.getText().toString();

        System.out.println(s);
        System.out.println(s1);


        Intent intent = new Intent(Event.this,Map.class);
        intent.putExtra("tittle",s);
        intent.putExtra("content",s1);
        startActivity(intent);
            }
        });



    }
}