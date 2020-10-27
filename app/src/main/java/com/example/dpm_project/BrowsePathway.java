package com.example.dpm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BrowsePathway extends AppCompatActivity {
    private TextView menuText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pathway);

        menuText = findViewById(R.id.Degree_title);
        menuText.setText("Pathway");
    }
}