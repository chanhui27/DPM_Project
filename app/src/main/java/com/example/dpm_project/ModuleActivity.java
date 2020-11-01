package com.example.dpm_project;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class ModuleActivity extends AppCompatActivity {
    private ModuleViewModel moduleViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_main);

        moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
        moduleViewModel.getAllModules().observe(this, new Observer<List<Module>>() {
            @Override
            public void onChanged(List<Module> modules) {

            }
        });
    }
}
