package com.example.dpm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dpm_project.models.Module;

public class PopActivity extends AppCompatActivity {
    public static final String EXTRA_CODE = "com.example.dpm_project.EXTRA_CODE";
    public static final String EXTRA_TITLE = "com.example.dpm_project.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.example.dpm_project.EXTRA_DESC";
    public static final String EXTRA_LEVEL = "com.example.dpm_project.EXTRA_LEVEL";
    public static final String EXTRA_CREDIT = "com.example.dpm_project.EXTRA_CREDIT";
    public static final String EXTRA_PRE = "com.example.dpm_project.EXTRA_PRE";
    public static final String EXTRA_CORE = "com.example.dpm_project.EXTRA_CORE";
    public static final String EXTRA_STREAM = "com.example.dpm_project.EXTRA_STREAM";

    private TextView code, title, desc, level, pre, core, stream;
    private ImageButton exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        screensize();

        code = findViewById(R.id.popModuleCode2);
        title = findViewById(R.id.popModuleTitle2);
        desc = findViewById(R.id.popDescription2);
        level = findViewById(R.id.popModuleLevel2);
        pre = findViewById(R.id.popPreRequisite2);
        core = findViewById(R.id.popCoRequisite2);
        stream = findViewById(R.id.popStream2);
        exit = findViewById(R.id.popClose);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_CODE)) {
            code.setText(intent.getStringExtra(EXTRA_CODE));
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            desc.setText(intent.getStringExtra(EXTRA_DESC));
            level.setText(intent.getStringExtra(EXTRA_LEVEL));
            core.setText(intent.getStringExtra(EXTRA_CORE));
            stream.setText(intent.getStringExtra(EXTRA_STREAM));
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                /*Intent intent2 = new Intent(PopActivity.this, ModuleActivity.class);
                startActivity(intent2);*/
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