package com.example.dpm_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dpm_project.models.Student;
import com.example.dpm_project.models.StudentPathway;
import com.example.dpm_project.viewmodels.ModuleViewModel;
import com.example.dpm_project.viewmodels.StudentViewModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    List<StudentPathway> students;
    private EditText editText_Id;
    private EditText editText_Name;
    private EditText editText_Email;
    private EditText editText_Address;
    private EditText editText_Phone;
    private Toolbar mToolbar;
    private Button cancelButton;
    private Button saveButton;
    private CircleImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    private Student student;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Student");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.wintec_logo);

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
                Intent cIntent = new Intent(ProfileActivity.this, StudentModuleActivity.class);
                startActivity(cIntent);
            }
        });

        saveButton = findViewById(R.id.profileSavebtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Toast.makeText(ProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        //view model
        studentViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(StudentViewModel.class);
        studentViewModel.getAllStudent().observe(this, sp -> {
            Log.d("STPW_", String.valueOf(sp.size()));
            if(sp.size() != 0) {
                student = sp.get(0).student;
            }

            this.students = sp;});

        //testing update profile
     /*   if(student != null) {

            editText_Id.setText(student.getStudentId());
            editText_Name.setText(student.getName());
            editText_Email.setText(student.getEmail());
            editText_Address.setText(student.getAddress());
            editText_Phone.setText(student.getPhone());
            profileImage.setImageURI(Uri.parse(student.getImageUrl()));

        }else {

            editText_Id.setText("");
            editText_Name.setText("");
            editText_Email.setText("");
            editText_Address.setText("");
            editText_Phone.setText("");
            profileImage.setImageURI(Uri.parse(""));

        }*/




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
        }

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    private void save() {
        Log.d("STUDENT_", String.valueOf(students.size()));
        if (students.size() == 0) {
            String id = editText_Id.getText().toString();
            String name = editText_Name.getText().toString();
            String email = editText_Email.getText().toString();
            String address = editText_Address.getText().toString();
            String phone = editText_Phone.getText().toString();
            String url = imageUri.toString();


            if (email.trim().isEmpty()) {
                Toast.makeText(this, "Please insert email", Toast.LENGTH_SHORT).show();
                return;
            }

            //create student
            Student student1 = new Student(id, name, email, address, phone, url);

            studentViewModel.insert(student1);

            finish();
        }else{

            studentViewModel.update(student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.draw_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_about:
                Intent intent2 = new Intent(this, about.class);
                startActivity(intent2);
                return true;

            case R.id.menu_profile:
                Intent intent3 = new Intent(this, ProfileActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}