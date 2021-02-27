package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adminapp.event.event;
import com.example.adminapp.fcmhelper.FCMClient;
import com.example.adminapp.pojo.Customer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Map extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private LatLng draglocation;
    FCMClient fcmClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);





        Button viewById = findViewById(R.id.adding);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Bundle bundle = getIntent().getExtras();
        String tittle = bundle.getString("tittle");
        String content = bundle.getString("content");
        event event = new event();
        event.setName(tittle);
        event.setTitle(content);
        event.setLat(draglocation.latitude);
        event.setLon(draglocation.longitude);
        db.collection("Event").add(event).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(Map.this, "Event Success", Toast.LENGTH_SHORT).show();

                db.collection("Customer").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Customer> customers = queryDocumentSnapshots.toObjects(Customer.class);

                        for (Customer customer : customers){
                            String fcmid = customer.getFcmid();
                            new FCMClient().execute(fcmid,"Job Accepted Successfully", " Deliver Name : Sandio");


                        }
                    }
                });


            }
        });
            }
        });





    }

    public void setLatlang(LatLng dragtlocation) {
        this.draglocation = dragtlocation;
    }
}