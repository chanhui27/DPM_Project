package com.example.dpm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ManagerPassword extends AppCompatActivity {
    private Button confirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_password);

        confirmBtn = findViewById(R.id.ConfirmBtn);
    }
}