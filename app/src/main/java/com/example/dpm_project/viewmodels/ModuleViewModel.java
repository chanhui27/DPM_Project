package com.example.dpm_project.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.ModuleWithPathways;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.repositories.ModuleRepository;

import java.util.List;
import java.util.stream.Collectors;

public  class  ModuleViewModel  extends  AndroidViewModel {

    private ModuleRepository repository;
    private LiveData<List<Module>> allModules;
    private LiveData<List<ModuleWithPathways>> modulesWithPathways;

    public  ModuleViewModel ( @NonNull  Application  application ) {
        super(application);
        repository = new ModuleRepository(application);
        allModules = repository.getAllModules();
        modulesWithPathways = repository.getModulesWithPathways();
    }

    public void insert(Module module) {
        repository.insert(module);
    }

    public void update(Module... module) {
        repository.update(module);
    }

    public void delete(Module module) {
        repository.delete(module);
    }

    public LiveData<List<ModuleWithPathways>> getModulesWithPathways() {
        return modulesWithPathways;
    }

    public LiveData<List<Module>> getAllModules() {
        return allModules;
    }

}