package com.example.dpm_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.ModuleWithPathways;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;
import com.example.dpm_project.viewmodels.ModuleViewModel;
import com.example.dpm_project.viewmodels.PathwayViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModuleActivity extends AppCompatActivity {
    private ModuleViewModel moduleViewModel;
    //private PathwayViewModel pathwayViewModel;
    //menu
    //private DrawerLayout drawer;
    private Toolbar mToolbar;
    public static final int VIEW_REQUEST=1;
    private ModuleAdapter adapter;
    private List<ModuleWithPathways> modules;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Pathway");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.wintec_logo);

        RecyclerView recyclerView = findViewById(R.id.pathway_recyclerview);
        Spinner pathwaySpinner = findViewById(R.id.pathway_spinner);
        Spinner yearSpinner = findViewById(R.id.year_spinner);
        Spinner semesterSpinner = findViewById(R.id.semester_spinner);

        List<String> years = Arrays.asList(getResources().getStringArray(R.array.years_array));
        ArrayAdapter<String> yearSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
        yearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearSpinnerAdapter);


        String[][] semesters = new String[][]{new String[]{"All semesters", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6"},
                new String[]{"All semesters", "Semester 1", "Semester 2"}, new String[]{"All semesters", "Semester 3", "Semester 4"}, new String[]{"All semesters", "Semester 5", "Semester 6"}};
        semesterSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, semesters[0]));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new ModuleAdapter();
        recyclerView.setAdapter(adapter);
        moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
        moduleViewModel.getModulesWithPathways().observe(this, mwps -> {
            this.modules = mwps;
        });
//        pathwayViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PathwayViewModel.class);
        List<String> pathways = Arrays.asList(getResources().getStringArray(R.array.pathways_array));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pathways);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pathwaySpinner.setAdapter(spinnerAdapter);


        pathwaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int year = yearSpinner.getSelectedItemPosition();
                int semester = semesterSpinner.getSelectedItemPosition();
                updateModules(i, year, semester);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                semesterSpinner.setAdapter(new ArrayAdapter<>(ModuleActivity.this, android.R.layout.simple_spinner_dropdown_item, semesters[pos]));
                int semester = semesterSpinner.getSelectedItemPosition();
                int pathway_id = pathwaySpinner.getSelectedItemPosition();
                updateModules(pathway_id, pos, semester);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int year = yearSpinner.getSelectedItemPosition();
                int pathway_id = pathwaySpinner.getSelectedItemPosition();
                updateModules(pathway_id, year, i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });










        //clicking change
        adapter.setOnItemClickListener(new ModuleAdapter.OnITemClickListener() {
            @Override
            public void onItemClick(Module module) {
                Intent intent = new Intent(ModuleActivity.this, PopActivity.class);
                intent.putExtra(PopActivity.EXTRA_CODE, module.getCode());
                intent.putExtra(PopActivity.EXTRA_TITLE, module.getTitle());
                intent.putExtra(PopActivity.EXTRA_DESC, module.getAim());
                intent.putExtra(PopActivity.EXTRA_LEVEL, module.getLevel());
                intent.putExtra(PopActivity.EXTRA_CREDIT, module.getCredit());
                intent.putExtra(PopActivity.EXTRA_CORE, module.getCoRequisite());
                intent.putExtra(PopActivity.EXTRA_PRE, module.getPreRequisite());
                intent.putExtra(PopActivity.EXTRA_STREAM, module.getStream());
                startActivityForResult(intent, VIEW_REQUEST );
            }
        });

    }


    private List<Module> getModulesByYear(int year) {
        Stream<ModuleWithPathways> result = modules.stream();
        if (year != 0) {
            result = modules.stream().filter(mwps -> mwps.module.getYear() == year);
        }
        return result.map(m -> m.module).collect(Collectors.toList());
    }

    private List<Module> getModulesByPathway(int pathway_id) {
        Stream<ModuleWithPathways> result = modules.stream();
        if (pathway_id != 0) {
            result = modules.stream().filter(mwps -> mwps.pathways.stream().anyMatch(p -> p.pathwayId == pathway_id));
        }
        return result.map(m -> m.module).collect(Collectors.toList());
    }

    private List<Module> getModulesBySemester(int year, int semester) {
        Stream<ModuleWithPathways> result = modules.stream();
        if (semester != 0) {
            int real_year = year == 0 ? semester / 2 + semester % 2 : year;
            int real_semester = year == 0 ? semester == 5 ? 1 : semester / 3 + semester % 3 : semester;
            result = modules.stream()
                    .filter(mwps -> mwps.module.getSemester() == real_semester && mwps.module.getYear() == real_year);
        }
        return result.map(m -> m.module).collect(Collectors.toList());
    }


    //Pop up
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==VIEW_REQUEST && resultCode == RESULT_OK) {
            String code = data.getStringExtra(PopActivity.EXTRA_CODE);
            String title = data.getStringExtra(PopActivity.EXTRA_TITLE);
            String desc = data.getStringExtra(PopActivity.EXTRA_DESC);
            String level = data.getStringExtra(PopActivity.EXTRA_LEVEL);
            String credits = data.getStringExtra(PopActivity.EXTRA_CREDIT);
            String core = data.getStringExtra(PopActivity.EXTRA_CORE);
            String pre = data.getStringExtra(PopActivity.EXTRA_PRE);
            String stream = data.getStringExtra(PopActivity.EXTRA_STREAM);

            Module module = new Module(code,title,1,desc,level,credits,1,core,pre,stream,1);
            moduleViewModel.insert(module);

        }
    }

    //options Menu
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

            case R.id.menu_profile:
                Intent intent3 = new Intent(this,ProfileActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateModules(int pathway_id, int year, int semester) {
        List<Module> byPathway = getModulesByPathway(pathway_id);
        List<Module> byYear = getModulesByYear(year);
        List<Module> bySemester = getModulesBySemester(year, semester);
        byPathway.retainAll(byYear);
        bySemester.retainAll(byPathway);
        adapter.setModules(bySemester);
    }



}

