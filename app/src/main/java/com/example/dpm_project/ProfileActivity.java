package com.example.dpm_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private TextView menuText;
    private EditText editText_Id;
    private EditText editText_Name;
    private EditText editText_Email;
    private EditText editText_Address;
    private EditText editText_Phone;
    private EditText editText_Pathway;
    private Button cancelButton;
    private CircleImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        menuText = findViewById(R.id.Degree_title);
        menuText.setText("Student");

        editText_Id = findViewById(R.id.edit_id);
        editText_Name = findViewById(R.id.edit_name);
        editText_Email = findViewById(R.id.edit_email);
        editText_Address = findViewById(R.id.edit_address);
        editText_Phone = findViewById(R.id.edit_phone);
        editText_Pathway = findViewById(R.id.edit_pathway);

        cancelButton = findViewById(R.id.profileCancelbtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cIntent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(cIntent);
            }
        });

        //get image from gallery
        profileImage = (CircleImageView) findViewById(R.id.photo);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
        }
    }



}