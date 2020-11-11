
package com.example.dpm_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.security.identity.AccessControlProfileId;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private Button managerButton;
    private Button studentButton;
    private Button pathwayButton;
    private TextView menuText;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Degree Program Mapper");


        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.wintec_logo);



        /*
        menuText = findViewById(R.id.Degree_title);

        menuText.setText("Degree Program Mapper"); */

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if(firstStart){
            openDisclaimer();
        }

        //toolbar
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //testing popup screen
        managerButton = findViewById(R.id.ManagerBtn);
        pathwayButton = findViewById(R.id.PathwayBtn);
        studentButton = findViewById(R.id.StudentBtn);

        //pathway
        pathwayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ModuleActivity.class));
            }
        });


        //student
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StudentModuleActivity.class));
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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.draw_menu,menu);
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
                Intent intent2 = new Intent(this,about.class);
                startActivity(intent2);
                return true;

           /* case R.id.menu_profile:
                Intent intent3 = new Intent(this,ProfileActivity.class);
                startActivity(intent3);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //popup disclaimer
    public void openDisclaimer() {
        LayoutInflater adbInflater = LayoutInflater.from(this);
        AlertDialog.Builder alertDialoguilder = new AlertDialog.Builder(this);
        alertDialoguilder.setTitle("                     Disclaimer");
        alertDialoguilder.setMessage(R.string.disclamer);



        alertDialoguilder.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"you accepted it", Toast.LENGTH_LONG).show();
                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstStart", false);
                editor.apply();
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
