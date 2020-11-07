package com.example.dpm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManagerMain extends AppCompatActivity {
    private TextView menuText;
    private Button mModuleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        menuText = findViewById(R.id.Degree_title);
        menuText.setText("Manager");

        mModuleButton = findViewById(R.id.ManageModulesBtn);

        mModuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerMain.this, ManagerModuleActivity.class);
                startActivity(intent);
            }
        });

    }
}