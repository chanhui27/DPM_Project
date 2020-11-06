package com.example.dpm_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfileActivity extends AppCompatActivity {
    private TextView menuText;
    private EditText editText_Id;
    private EditText editText_Name;
    private EditText editText_Email;
    private EditText editText_Address;
    private EditText editText_Phone;

    private Button cancelButton;
    private CircleImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    private static final String FILE_NAME = "student.txt";


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

        //get image from gallery
        profileImage = (CircleImageView) findViewById(R.id.photo);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        cancelButton = findViewById(R.id.profileCancelbtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cIntent = new Intent(UpdateProfileActivity.this, StudentModuleActivity.class);
                startActivity(cIntent);
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

    public void load(View v) {
        FileInputStream fis = null;

        try{
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            editText_Id.setText(sb.toString());
            editText_Name.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally  {
            if(fis != null) {
                try{
                    fis.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}