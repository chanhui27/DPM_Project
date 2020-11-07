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
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;
import com.example.dpm_project.viewmodels.PathwayViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ModuleActivity extends AppCompatActivity {
    //private ModuleViewModel moduleViewModel;
    private PathwayViewModel pathwayViewModel;
    //menu
    private DrawerLayout drawer;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Pathway");
        mToolbar.setTitleMarginStart(400);
        setSupportActionBar(mToolbar);

        RecyclerView recyclerView = findViewById(R.id.pathway_recyclerview);
        Spinner spinner = findViewById(R.id.pathway_spinner);
        final Pathway[] selectedPathway = new Pathway[1];
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ModuleAdapter adapter = new ModuleAdapter();
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
                pathwayViewModel.getPathwayWithModules().observe(ModuleActivity.this, pathwayWithModules -> {
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

}
