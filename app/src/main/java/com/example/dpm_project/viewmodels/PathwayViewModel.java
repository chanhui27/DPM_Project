package com.example.dpm_project.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;
import com.example.dpm_project.repositories.PathwayRepository;

import java.util.List;

public  class  PathwayViewModel  extends  AndroidViewModel {
    private PathwayRepository repository;
    private LiveData<List<Pathway>> allPathways;
    LiveData<List<PathwayWithModules>> allModules;
    public  PathwayViewModel ( @NonNull  Application  application ) {
        super(application);
        repository = new PathwayRepository(application);
        allPathways = repository.getAllPathways();
        allModules = repository.getPathwayWithModules();
    }

    public LiveData<List<PathwayWithModules>> getPathwayWithModules(){
        return allModules;
    }
    public LiveData<List<Pathway>> getAllPathways(){
        return allPathways;
    }
}