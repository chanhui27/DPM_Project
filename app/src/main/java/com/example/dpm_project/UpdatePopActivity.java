package com.example.dpm_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdatePopActivity extends AppCompatActivity {
    public static final String EXTRA_CODE = "com.example.dpm_project.EXTRA_CODE";
    public static final String EXTRA_TITLE = "com.example.dpm_project.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.example.dpm_project.EXTRA_DESC";
    public static final String EXTRA_LEVEL = "com.example.dpm_project.EXTRA_LEVEL";
    public static final String EXTRA_CREDIT = "com.example.dpm_project.EXTRA_CREDIT";
    public static final String EXTRA_PRE = "com.example.dpm_project.EXTRA_PRE";
    public static final String EXTRA_CORE = "com.example.dpm_project.EXTRA_CORE";
    public static final String EXTRA_STREAM = "com.example.dpm_project.EXTRA_STREAM";

    private EditText code, title, desc, level,credit, core,pre, stream;
    private ImageButton exit;
    private Button save, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popmanagerupdate);
        //screensize();

        code = findViewById(R.id.muModuleCode2);
        title = findViewById(R.id.muModuleTitle2);
        desc = findViewById(R.id.muDescription2);
        level = findViewById(R.id.muModuleLevel2);
        credit = findViewById(R.id.muCredit2);
        core = findViewById(R.id.muCoRequisite2);
        pre= findViewById(R.id.muPreRequisite2);
        stream = findViewById(R.id.muStream2);
        exit = findViewById(R.id.muClose);
        save = findViewById(R.id.edit_save);
        cancel = findViewById(R.id.edit_cancel);

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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePop();
            }
        });

    }

    private void savePop() {
        String updateCode = code.getText().toString();
        String updateTitle = title.getText().toString();
        String updateDesc = desc.getText().toString();
        String updateLevel = level.getText().toString();
        String updateCredit = credit.getText().toString();
        String updateCore = core.getText().toString();
        String updatePre = pre.getText().toString();
        String updateStream = stream.getText().toString();

        if(updateCode.trim().isEmpty() || updateTitle.trim().isEmpty() || updateDesc.trim().isEmpty() || updateLevel.trim().isEmpty() ||
                updateCredit.trim().isEmpty() ||updateCore.trim().isEmpty()||updatePre.trim().isEmpty()||updateStream.trim().isEmpty()){
            Toast.makeText(this,"Please insert details",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_CODE, updateCode);
        data.putExtra(EXTRA_TITLE, updateTitle);
        data.putExtra(EXTRA_DESC, updateDesc);
        data.putExtra(EXTRA_LEVEL, updateLevel);
        data.putExtra(EXTRA_CREDIT, updateCredit);
        data.putExtra(EXTRA_CORE, updateCore);
        data.putExtra(EXTRA_PRE, updatePre);
        data.putExtra(EXTRA_STREAM, updateStream);



        setResult(RESULT_OK,data);
        finish();
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