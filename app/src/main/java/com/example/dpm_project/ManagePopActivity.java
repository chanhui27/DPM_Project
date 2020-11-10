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

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Student;
import com.example.dpm_project.viewmodels.ModuleViewModel;
import com.example.dpm_project.viewmodels.StudentViewModel;

public class ManagePopActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.eample.dpm_project.EXTRA_ID";
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
    private Button editbutton, editcancel;

    private Module module;
    private ModuleViewModel moduleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popmanage);
        //screensize();

        code = findViewById(R.id.mModuleCode2);
        title = findViewById(R.id.mModuleTitle2);
        desc = findViewById(R.id.mDescription2);
        level = findViewById(R.id.mModuleLevel2);
        credit = findViewById(R.id.mCredit2);
        core = findViewById(R.id.mCoRequisite2);
        pre= findViewById(R.id.mPreRequisite2);
        stream = findViewById(R.id.mStream2);
        editbutton = findViewById(R.id.editbtn);
        editcancel = findViewById(R.id.editcancelbtn);
        exit = findViewById(R.id.mClose);

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

        //click exit image
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //click edit button
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit()) {
                    Toast.makeText(ManagePopActivity.this, "Edited", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        //click edit cancel button
        editcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private boolean edit() {
        String editcode = code.getText().toString();
        String edittitle = title.getText().toString();
        String editlevel = level.getText().toString();
        String editdesc = desc.getText().toString();
        String editcredit = credit.getText().toString();
        String editcore = core.getText().toString();
        String editpre = pre.getText().toString();
        String editstream = stream.getText().toString();

        if (module == null) {
            Toast.makeText(this, "no module", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editcode.trim().isEmpty() || edittitle.trim().isEmpty()||editcore.trim().isEmpty()||
        editcredit.trim().isEmpty()|| editdesc.trim().isEmpty()||editpre.trim().isEmpty()||
        editlevel.trim().isEmpty()||editstream.trim().isEmpty()) {
            Toast.makeText(this, "Please insert details", Toast.LENGTH_SHORT).show();
            return false;
        }

        //create student
        Module module1 = new Module(editcode, edittitle, 1, editdesc, editlevel,editcredit,1,editcore,editpre,editstream,1);
        if (this.module == null) {
            moduleViewModel.insert(module1);
        } else {
            module1.setModuleId(this.module.getModuleId());
            moduleViewModel.update(module1);
        }

        return true;
    }


}