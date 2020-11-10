package com.example.dpm_project.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dpm_project.ModuleDatabase;
import com.example.dpm_project.dao.PathwayDao;
import com.example.dpm_project.dao.PathwayModuleCrossRefDao;
import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PathwayRepository {
    private PathwayDao pathwayDao;
    private PathwayModuleCrossRefDao pathwayModuleCrossRefDao;
    private LiveData<List<Pathway>> allPathways;
    private Executor executor = Executors.newSingleThreadExecutor();

    public PathwayRepository(Application application) {
        ModuleDatabase database = ModuleDatabase.getInstance(application);
        pathwayDao = database.pathwayDao();
        pathwayModuleCrossRefDao = database.pathwayModuleCrossRefDao();
        allPathways = pathwayDao.getAllPathways();

    }

    public void insert(Pathway pathway) {
        executor.execute(() -> pathwayDao.insert(pathway));

    }

    public LiveData<List<Module>> getPathwayWithModules(int pathwayId){
        MutableLiveData<List<Module>> modules = new MutableLiveData<>();
        executor.execute(() -> {
            PathwayWithModules pathwayWithModules = pathwayModuleCrossRefDao.getPathwayWithModules(pathwayId);
            modules.postValue(pathwayWithModules.modules);
        });

        return modules;
    }

    public LiveData<List<Pathway>> getAllPathways() {
        return allPathways;
    }
}