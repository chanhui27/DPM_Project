package com.example.dpm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ManagerPassword extends AppCompatActivity {
    private Button confirmBtn;
    private TextView menuText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_password);

        menuText = findViewById(R.id.Degree_title);
        confirmBtn = findViewById(R.id.ConfirmBtn);
        menuText.setText("Manager Password");
    }
}