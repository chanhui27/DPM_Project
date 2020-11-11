package com.example.dpm_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import  android.view.WindowManager ;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagePopActivity extends AppCompatActivity {
    //initialize extra variables
    public static final String EXTRA_ID = "com.eample.dpm_project.EXTRA_ID";
    public static final String EXTRA_CODE = "com.example.dpm_project.EXTRA_CODE";
    public static final String EXTRA_TITLE = "com.example.dpm_project.EXTRA_TITLE";
    public static final String EXTRA_COMPLET = "com.example.dpm_project.EXTRA_COMPLET";
    public static final String EXTRA_DESC = "com.example.dpm_project.EXTRA_DESC";
    public static final String EXTRA_LEVEL = "com.example.dpm_project.EXTRA_LEVEL";
    public static final String EXTRA_CREDIT = "com.example.dpm_project.EXTRA_CREDIT";
    public static final String EXTRA_YEAR = "com.example.dpm-project.EXTRA_YEAR";
    public static final String EXTRA_PRE = "com.example.dpm_project.EXTRA_PRE";
    public static final String EXTRA_CORE = "com.example.dpm_project.EXTRA_CORE";
    public static final String EXTRA_STREAM = "com.example.dpm_project.EXTRA_STREAM";
    public static final String EXTRA_SEMESTER = "com.example.dpm_project.EXTRA_SEMESTER";

    private TextView code, title, desc, level,credit, core,pre, stream;
    private ImageButton exit;
    private Button saving, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super . onCreate (savedInstanceState);
        setContentView(R.layout.activity_popmanage);

        //initialize the id
        code = findViewById(R.id.mModuleCode2);
        title = findViewById(R.id.mModuleTitle2);
        desc = findViewById(R.id.mDescription2);
        level = findViewById(R.id.mModuleLevel2);
        credit = findViewById(R.id.mCredit2);
        core = findViewById(R.id.mCoRequisite2);
        pre= findViewById(R.id.mPreRequisite2);
        stream = findViewById(R.id.mStream2);
        exit = findViewById(R.id.mClose);
        saving = findViewById(R.id.editbtn);
        cancel = findViewById(R.id.editcancelbtn);

        Intent intent = getIntent();

        //get intent data from manager module activity
        if(intent.hasExtra(EXTRA_ID)) {
            code.setText(intent.getStringExtra(EXTRA_CODE));
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            desc.setText(intent.getStringExtra(EXTRA_DESC));
            level.setText(intent.getStringExtra(EXTRA_LEVEL));
            credit.setText(intent.getStringExtra(EXTRA_CREDIT));
            core.setText(intent.getStringExtra(EXTRA_CORE));
            pre.setText(intent.getStringExtra(EXTRA_PRE));
            stream.setText(intent.getStringExtra(EXTRA_STREAM));

        }

        //clicking exist image
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void  onClick ( View  v ) {
                finish();
            }
        });

        //clicking save button
        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void  onClick ( View  v ) {
                savePop();
            }
        });

        //clicking cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void  onClick ( View  v ) {
                finish();
            }
        });
    }

    //save edit module
    private void savePop() {
        String updateCode = code.getText().toString();
        String updateTitle = title.getText().toString();
        String updateDesc = desc.getText().toString();
        String updateLevel = level.getText().toString();
        String updateCredit = credit.getText().toString();
        String updateCore = core.getText().toString();
        String updatePre = pre.getText().toString();
        String updateStream = stream.getText().toString();

        //check whether every edit text is empty or not
        if(updateCode.trim().isEmpty() || updateTitle.trim().isEmpty() || updateDesc.trim().isEmpty() || updateLevel.trim().isEmpty() ||
                updateCredit.trim().isEmpty() ||updateStream.trim().isEmpty()){
            Toast.makeText(this,"Please insert details", Toast.LENGTH_SHORT).show();
            return;
        }
        //create intent and put updated string values
        Intent data = new Intent();
        data.putExtra(EXTRA_CODE, updateCode);
        data.putExtra(EXTRA_TITLE, updateTitle);
        data.putExtra(EXTRA_DESC, updateDesc);
        data.putExtra(EXTRA_LEVEL, updateLevel);
        data.putExtra(EXTRA_CREDIT, updateCredit);
        data.putExtra(EXTRA_CORE, updateCore);
        data.putExtra(EXTRA_PRE, updatePre);
        data.putExtra(EXTRA_STREAM, updateStream);
        int isco = getIntent().getIntExtra(EXTRA_COMPLET, -1);
        int year1 = getIntent().getIntExtra(EXTRA_YEAR, 0);
        int semester1 = getIntent().getIntExtra(EXTRA_SEMESTER, 0);

        data.putExtra(EXTRA_COMPLET, isco);
        data.putExtra(EXTRA_YEAR, year1);
        data.putExtra(EXTRA_SEMESTER, semester1);

        //check id is exist
        int id = getIntent () . getIntExtra ( EXTRA_ID , - 1 );
        if(id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult ( RESULT_OK , data);
        finish();
    }


}