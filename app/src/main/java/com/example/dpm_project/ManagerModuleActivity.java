package com.example.dpm_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.ModuleWithPathways;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;
import com.example.dpm_project.models.Student;
import com.example.dpm_project.viewmodels.ModuleViewModel;
import com.example.dpm_project.viewmodels.PathwayViewModel;
import com.example.dpm_project.viewmodels.StudentViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManagerModuleActivity extends AppCompatActivity {
    //private ModuleViewModel moduleViewModel;
    private PathwayViewModel pathwayViewModel;
    public static final int VIEW_REQUEST=1;
    public static final int EDIT_REQUEST=2;
    private ModuleViewModel moduleViewModel;
    private ManagerModuleAdapter adapter;
    private TextView menuText;
    private  Toolbar mToolbar;
    private List<Module> modules;
    private List<ModuleWithPathways> modulesWithPathways;
    private Student student;
    private Spinner pathwaySpinner;
    private Spinner semesterSpinner;
    private Spinner yearSpinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super . onCreate (savedInstanceState);
        setContentView(R.layout.manager_module_activity_main);

        //toolbar
        mToolbar = findViewById ( R . id . toolbar);
        mToolbar.setTitle("Manager");
        setSupportActionBar (mToolbar);
        getSupportActionBar().setLogo(R.mipmap.wintec_logo);;


        RecyclerView recyclerView = findViewById(R.id.manager_pathway_recyclerview);
        pathwaySpinner = findViewById(R.id.manager_pathway_spinner);
        yearSpinner = findViewById(R.id.manager_year_spinner);
        semesterSpinner = findViewById(R.id.manager_semester_spinner);

        List<String> years = Arrays.asList(getResources().getStringArray(R.array.years_array));
        ArrayAdapter<String> yearSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
        yearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearSpinnerAdapter);

        //final Pathway[] selectedPathway = new Pathway[1];

        String[][] semesters = new String[][]{new String[]{"All semesters", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6"},
                new String[]{"All semesters", "Semester 1", "Semester 2"}, new String[]{"All semesters", "Semester 3", "Semester 4"}, new String[]{"All semesters", "Semester 5", "Semester 6"}};
        semesterSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, semesters[0]));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ManagerModuleAdapter();
        recyclerView.setAdapter(adapter);

        moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
        moduleViewModel.getModulesWithPathways().observe(this, this::updateModulesWithPathways);
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
                updateModules();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public  void  onItemSelected ( AdapterView <?>  parent , View  view , int  pos , long  id ) {
                semesterSpinner.setAdapter(new ArrayAdapter<>(ManagerModuleActivity.this, android.R.layout.simple_spinner_dropdown_item, semesters[pos]));
                int semester = semesterSpinner.getSelectedItemPosition();
                int pathway_id = pathwaySpinner.getSelectedItemPosition();
                updateModules();
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
                updateModules();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //clicking change
        adapter.setOnItemClickListener(new ModuleAdapter.OnITemClickListener() {
            @Override
            public void onItemClick(Module module) {
                Intent intent = new Intent(ManagerModuleActivity.this, ManagePopActivity.class);
                intent.putExtra(ManagePopActivity.EXTRA_ID, module.getModuleId());
                intent.putExtra(ManagePopActivity.EXTRA_CODE, module.getCode());
                intent.putExtra(ManagePopActivity.EXTRA_TITLE, module.getTitle());
                intent.putExtra(ManagePopActivity.EXTRA_COMPLET, module.getIsCompleted());
                intent.putExtra(ManagePopActivity.EXTRA_DESC, module.getAim());
                intent.putExtra(ManagePopActivity.EXTRA_LEVEL, module.getLevel());
                intent.putExtra(ManagePopActivity.EXTRA_CREDIT, module.getCredit());
                intent.putExtra(ManagePopActivity.EXTRA_YEAR,(module.getYear()));
                intent.putExtra(ManagePopActivity.EXTRA_CORE, module.getCoRequisite());
                intent.putExtra(ManagePopActivity.EXTRA_PRE, module.getPreRequisite());
                intent.putExtra(ManagePopActivity.EXTRA_STREAM, module.getStream());
                intent.putExtra(ManagePopActivity.EXTRA_SEMESTER,module.getSemester());
                startActivityForResult(intent, VIEW_REQUEST );
            }
        });

        //change
        //moduleViewModel =  new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);

    }

    private void updateModulesWithPathways(List<ModuleWithPathways> moduleWithPathways) {
        this.modulesWithPathways = moduleWithPathways;
        this.modules = moduleWithPathways.stream().map(m -> m.module).collect(Collectors.toList());
        updateModules();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if(requestCode ==VIEW_REQUEST && resultCode == RESULT_OK) {
            String code = data.getStringExtra(ManagePopActivity.EXTRA_CODE);
            String title = data.getStringExtra(ManagePopActivity.EXTRA_TITLE);
            int isComple = Integer.parseInt(data.getStringExtra(ManagePopActivity.EXTRA_COMPLET));
            String desc = data.getStringExtra(ManagePopActivity.EXTRA_DESC);
            String level = data.getStringExtra(ManagePopActivity.EXTRA_LEVEL);
            String credits = data.getStringExtra(ManagePopActivity.EXTRA_CREDIT);
            int year = Integer.parseInt(data.getStringExtra(ManagePopActivity.EXTRA_YEAR));
            String core = data.getStringExtra(ManagePopActivity.EXTRA_CORE);
            String pre = data.getStringExtra(ManagePopActivity.EXTRA_PRE);
            String stream = data.getStringExtra(ManagePopActivity.EXTRA_STREAM);
            int semseter = Integer.parseInt(data.getStringExtra(ManagePopActivity.EXTRA_SEMESTER));



            Module module = new Module(code,title,isComple,desc,level,credits,year,core,pre,stream,semseter);
            moduleViewModel.update(module);

        }
        else*/
        if(requestCode == VIEW_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(ManagePopActivity.EXTRA_ID, -1);

            if(id == -1) {
                Toast.makeText(this, "can't update", Toast.LENGTH_SHORT).show();
                return;
            }
            String code = data.getStringExtra(ManagePopActivity.EXTRA_CODE);
            String title = data.getStringExtra(ManagePopActivity.EXTRA_TITLE);
            String desc = data.getStringExtra(ManagePopActivity.EXTRA_DESC);
            int isComple = data.getIntExtra(ManagePopActivity.EXTRA_COMPLET, -1);
            String level = data.getStringExtra(ManagePopActivity.EXTRA_LEVEL);
            String credits = data.getStringExtra(ManagePopActivity.EXTRA_CREDIT);
            String core = data.getStringExtra(ManagePopActivity.EXTRA_CORE);
            String pre = data.getStringExtra(ManagePopActivity.EXTRA_PRE);
            int year = data.getIntExtra(ManagePopActivity.EXTRA_YEAR, 0);
            String stream = data.getStringExtra(ManagePopActivity.EXTRA_STREAM);
            int semseter = data.getIntExtra(ManagePopActivity.EXTRA_SEMESTER, 0);

            Module module = new Module(code,title,isComple,desc,level,credits,year,core,pre,stream,semseter);
            module.setModuleId(id);
            moduleViewModel.update(module);

            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();


        }

    }

    //toolbar
    @Override
    public  boolean  onCreateOptionsMenu ( Menu  menu ) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.draw_pm,menu);
        return true;

    }

    @Override
    public  boolean  onOptionsItemSelected ( @NonNull  MenuItem  item ) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_about:
                Intent intent2 = new Intent(this,about.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateModules() {
        List<Module> byPathway = getModulesByPathway();
//        Log.d("STUD_list of modules", String.valueOf(byPathway.size()));
        List<Module> byYear = getModulesByYear();
        List<Module> bySemester = getModulesBySemester();
        byPathway.retainAll(byYear);
        bySemester.retainAll(byPathway);

        adapter.setModules(bySemester);
    }

    private List<Module> getModulesByYear() {
        Stream<ModuleWithPathways> result = modulesWithPathways.stream();
        int year = yearSpinner.getSelectedItemPosition();
        if (year != 0) {
            result = modulesWithPathways.stream().filter(mwps -> mwps.module.getYear() == year);
        }
        return result.map(m -> m.module).collect(Collectors.toList());
    }

    private List<Module> getModulesByPathway() {
        Log.d("STUD_modules", String.valueOf(modulesWithPathways.size()));
        Stream<ModuleWithPathways> result = modulesWithPathways.stream();
        int pathway_id = pathwaySpinner.getSelectedItemPosition();
        Log.d("STUD_pathwaySpinner position", String.valueOf(pathway_id));
        if (pathway_id != 0) {
            result = modulesWithPathways.stream().filter(mwps -> mwps.pathways.stream().anyMatch(p -> p.pathwayId == pathway_id));
        }
        return result.map(m -> m.module).collect(Collectors.toList());
    }

    private List<Module> getModulesBySemester() {
        Stream<ModuleWithPathways> result = modulesWithPathways.stream();
        int semester = semesterSpinner.getSelectedItemPosition();
        int year = yearSpinner.getSelectedItemPosition();
        if (semester != 0) {
            int real_year = year == 0 ? semester / 2 + semester % 2 : year;
            int real_semester = year == 0 ? semester == 5 ? 1 : semester / 3 + semester % 3 : semester;
            result = modulesWithPathways.stream()
                    .filter(mwps -> mwps.module.getSemester() == real_semester && mwps.module.getYear() == real_year);
        }
        return result.map(m -> m.module).collect(Collectors.toList());
    }


}