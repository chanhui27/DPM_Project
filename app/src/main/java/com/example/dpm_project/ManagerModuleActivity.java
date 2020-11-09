package com.example.dpm_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;
import com.example.dpm_project.viewmodels.ModuleViewModel;
import com.example.dpm_project.viewmodels.PathwayViewModel;

import java.util.ArrayList;
import java.util.List;

public class ManagerModuleActivity extends AppCompatActivity {
    //private ModuleViewModel moduleViewModel;
    private PathwayViewModel pathwayViewModel;
    public static final int VIEW_REQUEST=1;
    private ModuleViewModel moduleViewModel;
    private TextView menuText;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_module_activity_main);

        //toolbar
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Manager");
        mToolbar.setTitleMarginStart(300);
        setSupportActionBar(mToolbar);

        RecyclerView recyclerView = findViewById(R.id.manager_pathway_recyclerview);
        Spinner spinner = findViewById(R.id.manager_pathway_spinner);

        final Pathway[] selectedPathway = new Pathway[1];
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ManagerModuleAdapter adapter = new ManagerModuleAdapter();
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
                pathwayViewModel.getPathwayWithModules().observe(ManagerModuleActivity.this, pathwayWithModules -> {
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

        //clicking change
        adapter.setOnItemClickListener(new ModuleAdapter.OnITemClickListener() {
            @Override
            public void onItemClick(Module module) {
                Intent intent = new Intent(ManagerModuleActivity.this, PopActivity.class);
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

    //toolbar
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

            /*case R.id.menu_profile:
                Intent intent3 = new Intent(this,ProfileActivity.class);
                startActivity(intent3);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
