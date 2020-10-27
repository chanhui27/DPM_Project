package com.example.dpm_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< Updated upstream
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
=======
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
>>>>>>> Stashed changes
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
<<<<<<< Updated upstream
=======
    private DrawerLayout drawer;
    private Button pathwaybutton;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDisclaimer();
<<<<<<< Updated upstream

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
=======

        //testing popup screen
        pathwaybutton = findViewById(R.id.PathwayBtn);
        pathwaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PopActivity.class);
                startActivity(intent);
            }
        });

        //toolbar
        //Toolbar toolbar = findViewById(R.id.toolbar);
      /*  Toolbar toolbar = findViewById(R.id.toolbar2);
>>>>>>> Stashed changes
        setSupportActionBar(toolbar);

        /*Toolbar toolbar = findViewById(R.id.toolbar2);
        //drawer = findViewById(R.id.drawer_layout);

        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
             //   R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       // drawer.addDrawerListener(toggle);
        //toggle.syncState(); */

<<<<<<< Updated upstream
    }
=======
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/
>>>>>>> Stashed changes

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.stu_menu, menu);
        return true;
    }

<<<<<<< Updated upstream
     /*
    @Override
=======
    /*@Override
>>>>>>> Stashed changes
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }*/

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

    //Stu_Menu option clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home: {
                // do your home stuff
                return true;
            }
            case R.id.About: {
                // do your about stuff
                return true;
            }
            case R.id.Profile: {
                // do your profile stuff
                return true;
            }
            case R.id.Track: {
                // do your track stuff
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}