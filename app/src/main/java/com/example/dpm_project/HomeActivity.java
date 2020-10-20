package com.example.dpm_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        openDisclaimer();

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
    }

    //popup disclaimer
    public void openDisclaimer() {
        AlertDialog.Builder alertDialoguilder = new AlertDialog.Builder(this);
        alertDialoguilder.setMessage(R.string.disclamer);

        alertDialoguilder.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(HomeActivity.this,"you accepted it", Toast.LENGTH_LONG).show();
            }
        });

        alertDialoguilder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialoguilder.create();
        alertDialog.show();
    }
}