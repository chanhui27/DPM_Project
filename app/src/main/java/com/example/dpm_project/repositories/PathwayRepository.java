package com.example.dpm_project.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dpm_project.ModuleDatabase;
import com.example.dpm_project.dao.PathwayDao;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PathwayRepository {
    private PathwayDao pathwayDao;
    LiveData<List<PathwayWithModules>> allModules;
    private LiveData<List<Pathway>> allPathways;
    private Executor executor = Executors.newSingleThreadExecutor();

    public PathwayRepository(Application application) {
        ModuleDatabase database = ModuleDatabase.getInstance(application);
        pathwayDao = database.pathwayDao();
        allPathways = pathwayDao . getAllPathways ();
        allModules = pathwayDao.getPathwayWithModules();
    }

    public void insert(Pathway pathway) {
        executor.execute(() -> pathwayDao.insert(pathway));

    }

    public LiveData<List<PathwayWithModules>> getPathwayWithModules(){
        return allModules;
    }

    public LiveData<List<Pathway>> getAllPathways() {
        return allPathways;
    }
}