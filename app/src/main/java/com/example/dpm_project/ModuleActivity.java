package com.example.dpm_project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.ModuleWithPathways;
import com.example.dpm_project.viewmodels.ModuleViewModel;
import com.example.dpm_project.viewmodels.PathwayViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleActivity extends AppCompatActivity {
    private ModuleViewModel moduleViewModel;
    private PathwayViewModel pathwayViewModel;
    private ModuleAdapter adapter;
    private List<ModuleWithPathways> modules;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_main);
        RecyclerView recyclerView = findViewById(R.id.pathway_recyclerview);
        Spinner pathwaySpinner = findViewById(R.id.pathway_spinner);
        Spinner yearSpinner = findViewById(R.id.year_spinner);
        Spinner semesterSpinner = findViewById(R.id.semester_spinner);

        List<String> years = Arrays.asList(getResources().getStringArray(R.array.years_array));
        ArrayAdapter<String> yearSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
        yearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearSpinnerAdapter);


        String[][] semesters = new String[][]{new String[]{"All semesters", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6"},
                new String[]{"Semester 1", "Semester 2"}, new String[]{"Semester 3", "Semester 4"}, new String[]{"Semester 5", "Semester 6"}};

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                semesterSpinner.setAdapter(new ArrayAdapter<>(ModuleActivity.this, android.R.layout.simple_spinner_dropdown_item, semesters[pos]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new ModuleAdapter();
        recyclerView.setAdapter(adapter);
        moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
        pathwayViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PathwayViewModel.class);
        List<String> pathways = Arrays.asList(getResources().getStringArray(R.array.pathways_array));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pathways);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pathwaySpinner.setAdapter(spinnerAdapter);

        moduleViewModel.getModulesWithPathways().observe(this, mwps -> {
            this.modules = mwps;
        });



        pathwaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /*pathway_id = i;
                getModules(pathway_id);*/
                getModules(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });











       /* moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
        moduleViewModel.getPathway().observe(this, p -> {
            adapter.setModules(moduleViewModel.getFilteredModules());*/
/*
            selectedYear = (int) yearSpinner.getSelectedItemPosition();
            selectedSemester = (int) semesterSpinner.getSelectedItemPosition();
            Log.d("selected_Pathway", String.valueOf(selectedPathway));
            Log.d("selected_Year", String.valueOf(selectedYear));
            Log.d("selected_Semester", String.valueOf(selectedSemester));
            Log.d("moduleWithPathways", String.valueOf(moduleWithPathways.size()));*/

//                Log.d("filteredModules", String.valueOf(filteredModules.size()));
















/*        pathwaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPathway = (Pathway) pathwaySpinner.getSelectedItem();
                selectedYear = yearSpinner.getSelectedItemPosition();
                selectedSemester = semesterSpinner.getSelectedItemPosition();
                pathwayViewModel.getPathwayWithModules(selectedPathway.pathwayId).observe(ModuleActivity.this, new Observer<List<Module>>() {
                    @Override
                    public void onChanged(List<Module> modules) {
                        List<Module> filteredModules = modules.stream().filter(m -> m.getCode().startsWith("COMP")).collect(Collectors.toList());

                        adapter.setModules(filteredModules);

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/





/*        ArrayAdapter<Pathway> spinnerAdapter = new ArrayAdapter<Pathway>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, pathways);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);*/



       /* moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
        moduleViewModel.getAllModules().observe(this, modules -> adapter.setModules(modules));*/

        /*new

                ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Module module = adapter.getModuleAt(viewHolder.getAdapterPosition());
                boolean isCompleted = module.getIsCompleted() == 0 ? false : true;
                if (isCompleted) {
                    module.setIsCompleted(0);
                } else {
                    module.setIsCompleted(1);
                }
                moduleViewModel.update(module);
                Toast.makeText(ModuleActivity.this, isCompleted ? "Module incompleted" : "Module completed", Toast.LENGTH_SHORT).show();
            }
        }).

                attachToRecyclerView(recyclerView);
    }*/
    }

    private void getModules(int pathway_id) {
        List<Module> result;
        if (pathway_id == 0) {
            result = modules.stream()
                    .map(m -> m.module)
                    .collect(Collectors.toList());
        }else{
            result = modules.stream()
                    .filter(mwps -> mwps.pathways.stream()
                            .anyMatch(p -> p.pathwayId == pathway_id))
                    .map(m -> m.module)
                    .collect(Collectors.toList());
        }
        adapter.setModules(result);
    }

   /* private void getModules(int pathway_id) {
        pathwayViewModel.getPathwayWithModules(pathway_id).observe(this, this::updateModules);
    }*/


}
