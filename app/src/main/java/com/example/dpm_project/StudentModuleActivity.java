package com.example.dpm_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;
import com.example.dpm_project.viewmodels.PathwayViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentModuleActivity extends AppCompatActivity {
    //private ModuleViewModel moduleViewModel;
    private PathwayViewModel pathwayViewModel;
    private TextView menuText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_module_activity_main);

        SharedPreferences prefs = getSharedPreferences("don'tshow", MODE_PRIVATE);
        boolean first = prefs.getBoolean("first", true);

        if(first) {
            openProfile();
        }


        RecyclerView recyclerView = findViewById(R.id.student_pathway_recyclerview);
        Spinner spinner = findViewById(R.id.student_pathway_spinner);

        final Pathway[] selectedPathway = new Pathway[1];
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        StudentModuleAdapter adapter = new StudentModuleAdapter();
        recyclerView.setAdapter(adapter);

        pathwayViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PathwayViewModel.class);
        pathwayViewModel.getAllPathways().observe(this, pathways -> {
            ArrayAdapter<Pathway> spinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, pathways);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPathway[0] = (Pathway) spinner.getSelectedItem();
                pathwayViewModel.getPathwayWithModules().observe(StudentModuleActivity.this, pathwayWithModules -> {
                    if (selectedPathway[0] != null) {
                        for (PathwayWithModules p : pathwayWithModules) {
                            if (selectedPathway[0].pathwayId == p.pathway.pathwayId) {
                                adapter.setModules(p.modules);
                                break;
                            }
                        }
                    } else {
                        List<Module> result = new ArrayList<>();
                        pathwayWithModules.forEach(p -> result.addAll((p.modules)));
                        adapter.setModules(result);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    //popup profile
    public void openProfile() {
        AlertDialog.Builder alertDialoguilder = new AlertDialog.Builder(this);
        alertDialoguilder.setMessage("Would you like to create a profile?");

        alertDialoguilder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences prefs = getSharedPreferences("don'tshow", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("first", false);
                editor.apply();
                Intent intent = new Intent(StudentModuleActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

        alertDialoguilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialoguilder.create();
        alertDialog.show();
    }
}
