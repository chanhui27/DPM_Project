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
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.ModuleWithPathways;
import com.example.dpm_project.models.Student;
import com.example.dpm_project.viewmodels.ModuleViewModel;
import com.example.dpm_project.viewmodels.StudentViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentModuleActivity extends AppCompatActivity {
    //    private ModuleViewModel moduleViewModel;
    private StudentViewModel studentViewModel;
    private ModuleViewModel moduleViewModel;
    public static final int VIEW_REQUEST = 1;
    private StudentModuleAdapter adapter;
    private List<Module> modules;
    private List<ModuleWithPathways> modulesWithPathways;
    private Student student;
    private Spinner pathwaySpinner;
    private Spinner semesterSpinner;
    private Spinner yearSpinner;
    private Toolbar mToolbar;
    private int studentPathwayId = 0;
    private static int STUDENT_ID = 1;
    private int MODE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_module_activity_main);


        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Student");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.wintec_logo);
/*
        //sharedPReferences to keep the creating part is showing once
        SharedPreferences prefs = getSharedPreferences("don'tshow", MODE_PRIVATE);
        boolean first = prefs.getBoolean("first", true);*/

/*
        if (first) {
            openProfile();
        }
*/


        RecyclerView recyclerView = findViewById(R.id.student_pathway_recyclerview);
        pathwaySpinner = findViewById(R.id.student_pathway_spinner);
        yearSpinner = findViewById(R.id.student_year_spinner);
        semesterSpinner = findViewById(R.id.student_semester_spinner);
        CheckBox checkBox = findViewById(R.id.checkbox);

        //final Pathway[] selectedPathway = new Pathway[1];

        List<String> years = Arrays.asList(getResources().getStringArray(R.array.years_array));
        ArrayAdapter<String> yearSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
        yearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearSpinnerAdapter);


        String[][] semesters = new String[][]{new String[]{"All semesters", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6"},
                new String[]{"All semesters", "Semester 1", "Semester 2"}, new String[]{"All semesters", "Semester 3", "Semester 4"}, new String[]{"All semesters", "Semester 5", "Semester 6"}};
        semesterSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, semesters[0]));

        List<String> pathways = Arrays.asList(getResources().getStringArray(R.array.pathways_array));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pathways);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pathwaySpinner.setAdapter(spinnerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new StudentModuleAdapter();
        recyclerView.setAdapter(adapter);


        studentViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(StudentViewModel.class);
        studentViewModel.getStudent(STUDENT_ID).observe(this, student -> {
                    this.student = student;
                    if (student == null) {
                        openProfile();
                    }
                    if (student != null) {
                        studentPathwayId = student.getStudentPathwayId();
                        pathwaySpinner.setSelection(studentPathwayId);
                        MODE = pathwaySpinner.getSelectedItemPosition() == 0 ? 0 : 1;
                    }
                }
        );


        moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
        moduleViewModel.getModulesWithPathways().observe(this, this::updateModulesWithPathways);
//        pathwayViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PathwayViewModel.class);
//        Log.d("STUDENT_MODE", String.valueOf(pathwaySpinner.getSelectedItemPosition()));

        /*pathwayViewModel.getPathwayWithModules(student.getStudentPathwayId()).observe(this, pathwaysWithModules -> {
            this.modules = pathwaysWithModules;
            adapter.setModules(modules);
        });*/

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, 0) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                Module module = adapter.getModuleAt(viewHolder.getAdapterPosition());
                if (MODE == 0) {
                    swipeFlags = 0;
                }
                if (module.isLocked()) {
                    swipeFlags = 0;
                }

                return makeMovementFlags(0, swipeFlags);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                List<Module> pre = new ArrayList<>();
                Module module = adapter.getModuleAt(viewHolder.getAdapterPosition());
                module.setIsCompleted(module.getIsCompleted() == 0 ? 1 : 0);
                if (module.getIsCompleted() == 0) {
                    pre = modules.stream().filter(m -> m.getPreRequisite().contains(module.getCode())).collect(Collectors.toList());
                    pre.stream().forEach(prem -> prem.setIsCompleted(0));
                }
                pre.add(module);


                moduleViewModel.update(pre.toArray(new Module[pre.size()]));
//                    Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (student != null) {
                    student.setStudentPathwayId(pathwaySpinner.getSelectedItemPosition());
                    studentViewModel.update(student);
                    checkBox.setEnabled(false);
                } else {
                    Student student = new Student("", "", "", "", "", "");
                    student.setStudentPathwayId(pathwaySpinner.getSelectedItemPosition());
                    studentViewModel.insert(student);
                }
            }
        });

        pathwaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int year = yearSpinner.getSelectedItemPosition();
                int semester = semesterSpinner.getSelectedItemPosition();
                MODE = ((i == studentPathwayId) && (i != 0)) ? 1 : 0;
                checkBox.setEnabled(i != 0);
                if (checkBox.isChecked() && i != studentPathwayId) {
                    checkBox.setChecked(false);
                } else if (i == studentPathwayId && i != 0) {
                    checkBox.setChecked(true);
                    checkBox.setEnabled(false);
                }
                updateModules();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                semesterSpinner.setAdapter(new ArrayAdapter<>(StudentModuleActivity.this, android.R.layout.simple_spinner_dropdown_item, semesters[pos]));
                updateModules();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
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
                Intent intent = new Intent(StudentModuleActivity.this, PopActivity.class);
                intent.putExtra(PopActivity.EXTRA_CODE, module.getCode());
                intent.putExtra(PopActivity.EXTRA_TITLE, module.getTitle());
                intent.putExtra(PopActivity.EXTRA_DESC, module.getAim());
                intent.putExtra(PopActivity.EXTRA_LEVEL, module.getLevel());
                intent.putExtra(PopActivity.EXTRA_CREDIT, module.getCredit());
                intent.putExtra(PopActivity.EXTRA_CORE, module.getCoRequisite());
                intent.putExtra(PopActivity.EXTRA_PRE, module.getPreRequisite());
                intent.putExtra(PopActivity.EXTRA_STREAM, module.getStream());
                startActivityForResult(intent, VIEW_REQUEST);
            }
        });

    }


    private void updateModulesWithPathways(List<ModuleWithPathways> moduleWithPathways) {
        this.modulesWithPathways = moduleWithPathways;
        this.modules = moduleWithPathways.stream().map(m -> m.module).collect(Collectors.toList());
        updateModules();
    }


    private void updateModules() {
        List<Module> byPathway = getModulesByPathway();
//        Log.d("STUD_list of modules", String.valueOf(byPathway.size()));
        List<Module> byYear = getModulesByYear();
        List<Module> bySemester = getModulesBySemester();
        byPathway.retainAll(byYear);
        bySemester.retainAll(byPathway);
        for (Module m : bySemester) {
            if (!m.getPreRequisite().isEmpty()) {
                List<String> codes = Arrays.asList(m.getPreRequisite().replace(",", "").split(" "));
                if (codes.contains("OR")) {
                    boolean eitherCompleted = modules.stream()
                            .filter(mpre -> codes.contains(mpre.getCode()))
                            .anyMatch(mc -> mc.getIsCompleted() == 1);
                    m.setLocked(!eitherCompleted);
                } else {
                    boolean notCompleted = modules.stream()
                            .filter(mpre -> codes.contains(mpre.getCode()))
                            .anyMatch(mc -> mc.getIsCompleted() == 0);
                    m.setLocked(notCompleted);
                }
            }
        }
        adapter.setModules(bySemester);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.draw_menu, menu);
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
                Intent intent2 = new Intent(this, about.class);
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

/*    private List<Module> updateModules(int pathway_id, int year, int semester) {
        List<Module> byPathway = getModulesByPathway(pathway_id);
        List<Module> byYear = getModulesByYear(year);
        List<Module> bySemester = getModulesBySemester(year, semester);
        byPathway.retainAll(byYear);
        bySemester.retainAll(byPathway);
        return bySemester;
    }*/

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
