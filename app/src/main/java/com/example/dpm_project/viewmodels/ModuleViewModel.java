package com.example.dpm_project.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.repositories.ModuleRepository;

import java.util.List;

public class ModuleViewModel extends AndroidViewModel {

    private ModuleRepository repository;
    private LiveData<List<Module>> allModules;

    public ModuleViewModel(@NonNull Application application) {
        super(application);
        repository = new ModuleRepository(application);
        allModules = repository.getAllModules();
    }

    public void insert(Module module) {
        repository.insert(module);
    }

    public void update(Module module) {
        repository.update(module);
    }

    public void delete(Module module) {
        repository.delete(module);
    }



    public LiveData<List<Module>> getAllModules() {
        return allModules;
    }
}

