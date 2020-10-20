package com.example.dpm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    private EditText editText_Id;
    private EditText editText_Name;
    private EditText editText_Email;
    private EditText editText_Address;
    private EditText editText_Phone;
    private EditText editText_Pathway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        editText_Id = findViewById(R.id.edit_id);
        editText_Name = findViewById(R.id.edit_name);
        editText_Email = findViewById(R.id.edit_email);
        editText_Address = findViewById(R.id.edit_address);
        editText_Phone = findViewById(R.id.edit_phone);
        editText_Pathway = findViewById(R.id.edit_pathway);


    }
}