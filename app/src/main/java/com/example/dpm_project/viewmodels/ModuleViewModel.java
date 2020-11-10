package com.example.dpm_project.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.ModuleWithPathways;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.repositories.ModuleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleViewModel extends AndroidViewModel {

    private ModuleRepository repository;
    private LiveData<List<Module>> allModules;
    private LiveData<List<ModuleWithPathways>> modulesWithPathways;
    //    private MutableLiveData<List<Module>> filteredModules = new MutableLiveData<>();
    MutableLiveData<Pathway> mPathway = new MutableLiveData<>();
    private Pathway pathway;


    public ModuleViewModel(@NonNull Application application) {
        super(application);
        repository = new ModuleRepository(application);
        allModules = repository.getAllModules();
        modulesWithPathways = repository.getModulesWithPathways();
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

    public LiveData<List<ModuleWithPathways>> getModulesWithPathways() {
        return modulesWithPathways;
    }

    public List<Module> getFilteredModules() {
        if (modulesWithPathways.getValue() != null) {
            return modulesWithPathways.getValue().stream()
                    .filter(mwp -> mwp.pathways.stream()
                            .anyMatch(p -> p.pathwayId == mPathway.getValue().pathwayId))
                    .map(mwp -> mwp.module)
                    .collect(Collectors.toList());
        }
        return allModules.getValue();
    }

    public LiveData<List<Module>> getAllModules() {
        return allModules;
    }

    public void setPathway(Pathway selectedPathway) {
        mPathway.setValue(selectedPathway);
    }

    public LiveData<Pathway> getPathway() {
        return mPathway;
    }
}

