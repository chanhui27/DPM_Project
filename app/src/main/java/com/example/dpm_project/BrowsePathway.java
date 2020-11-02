package com.example.dpm_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BrowsePathway extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Module> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView menuText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pathway);

        //change menu title
        menuText = findViewById(R.id.Degree_title);
        menuText.setText("Pathway");

        recyclerView = findViewById(R.id.recyclerView); //id connect
        recyclerView.setHasFixedSize(true); //improve
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //object list (go to adapter)

        database = FirebaseDatabase.getInstance();//firebase database connect

        databaseReference = database.getReference("Module"); //db table connect
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //receive firebase database's data
                arrayList.clear(); //reset orginal array list
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    Module module = snapshot1.getValue(Module.class); //store module object
                    arrayList.add(module); // stored data insert into array list and start to sent it to recycerview
                }
                adapter.notifyDataSetChanged();//list save and refresh
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //while getting db, error message
                Log.e("BrowsePathway",String.valueOf(error.toException()));//display error message
            }
        });

        adapter = new CustomAdapter(arrayList,this);
        recyclerView.setAdapter(adapter); // recyclerview adapter connect

    }
}