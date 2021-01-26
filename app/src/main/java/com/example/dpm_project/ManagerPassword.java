package com.example.dpm_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerPassword extends AppCompatActivity {
    private EditText tPwd;
    private Button btnConfirm;
    private Button btnCancel;
    private TextView menuText;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_password);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Manager");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.wintec_logo);

        tPwd = findViewById(R.id.edit_pwd);
        btnConfirm = findViewById(R.id.ConfirmBtn);
        btnCancel = findViewById(R.id.CancelBtn);

        //click Confirm button
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tPwd.getText().toString().equals(("WinITDMP01"))) {
                    Intent intent = new Intent(ManagerPassword.this, ManagerMain.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ManagerPassword.this, "wrong password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //click Cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goIntent = new Intent(ManagerPassword.this, MainActivity.class);
                startActivity(goIntent);
            }
        });
    }
}