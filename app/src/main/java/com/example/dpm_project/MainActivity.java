package com.example.dpm_project;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private Button managerButton;
    private Button pathwayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDisclaimer();

        //toolbar
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //testing popup screen
        managerButton = findViewById(R.id.ManagerBtn);
        pathwayButton = findViewById(R.id.PathwayBtn);

        pathwayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ModuleActivity.class));
            }
        });



        //go to manager screen
        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), ManagerPassword.class);
                startActivity(mIntent);
            }
        });

        //toolbar
        //Toolbar toolbar = findViewById(R.id.toolbar);
     /*   Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    //popup disclaimer
    public void openDisclaimer() {
        AlertDialog.Builder alertDialoguilder = new AlertDialog.Builder(this);
        alertDialoguilder.setMessage(R.string.disclamer);

        alertDialoguilder.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"you accepted it", Toast.LENGTH_LONG).show();
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