package com.example.dpm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ManagerPassword extends AppCompatActivity {
    private EditText tPwd;
    private Button btnConfirm;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_password);

        tPwd = findViewById(R.id.edit_id);
        btnConfirm = findViewById(R.id.ConfirmBtn);
        btnCancel = findViewById(R.id.CancelBtn);

        //click Confirm button
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tPwd.equals(("WinITDMP01"))) {
                    Intent intent = new Intent(ManagerPassword.this,ManagerMain.class);
                    startActivity(intent);
                }
            }
        });

        //click Cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goIntent = new Intent(ManagerPassword.this, MainActivity.class);
            }
        });



    }
}