package com.example.dpm_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Update;

public class ManagePopActivity extends AppCompatActivity {
    public static final String EXTRA_CODE = "com.example.dpm_project.EXTRA_CODE";
    public static final String EXTRA_TITLE = "com.example.dpm_project.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.example.dpm_project.EXTRA_DESC";
    public static final String EXTRA_LEVEL = "com.example.dpm_project.EXTRA_LEVEL";
    public static final String EXTRA_CREDIT = "com.example.dpm_project.EXTRA_CREDIT";
    public static final String EXTRA_PRE = "com.example.dpm_project.EXTRA_PRE";
    public static final String EXTRA_CORE = "com.example.dpm_project.EXTRA_CORE";
    public static final String EXTRA_STREAM = "com.example.dpm_project.EXTRA_STREAM";

    private TextView code, title, desc, level,credit, core,pre, stream;
    private ImageButton exit;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popmanager);
        //screensize();

        code = findViewById(R.id.mModuleCode2);
        title = findViewById(R.id.mModuleTitle2);
        desc = findViewById(R.id.mDescription2);
        level = findViewById(R.id.mModuleLevel2);
        credit = findViewById(R.id.mCredit2);
        core = findViewById(R.id.mCoRequisite2);
        pre= findViewById(R.id.mPreRequisite2);
        stream = findViewById(R.id.mStream2);
        exit = findViewById(R.id.mpopClose);
        update = findViewById(R.id.edit_update);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_CODE)) {
            code.setText(intent.getStringExtra(EXTRA_CODE));
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            desc.setText(intent.getStringExtra(EXTRA_DESC));
            level.setText(intent.getStringExtra(EXTRA_LEVEL));
            credit.setText(intent.getStringExtra(EXTRA_CREDIT));
            core.setText(intent.getStringExtra(EXTRA_CORE));
            pre.setText(intent.getStringExtra(EXTRA_PRE));
            stream.setText(intent.getStringExtra(EXTRA_STREAM));
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upintent = new Intent(ManagePopActivity.this, UpdatePopActivity.class);
                startActivity(upintent);
            }
        });
    }



    public void screensize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.9));

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.CENTER;
        params.x=0;
        params.y = -20;

        getWindow().setAttributes((params));
    }

}