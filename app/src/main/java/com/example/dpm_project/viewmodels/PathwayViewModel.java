package com.example.dpm_project.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;
import com.example.dpm_project.repositories.PathwayRepository;

import java.util.List;

public   class   PathwayViewModel   extends   AndroidViewModel {
    private PathwayRepository repository;
    private LiveData<List<Pathway>> allPathways;
    public   PathwayViewModel ( @NonNull   Application   application ) {
        super(application);
        repository = new PathwayRepository(application);
        allPathways = repository.getAllPathways();

    }

    public LiveData<List<Module>> getPathwayWithModules(int pathwayId){
        return repository.getPathwayWithModules(pathwayId);
    }
    public LiveData<List<Pathway>> getAllPathways(){
        return allPathways;
    }
}