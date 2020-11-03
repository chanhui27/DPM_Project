package com.example.dpm_project.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dpm_project.ModuleDatabase;
import com.example.dpm_project.dao.ModuleDao;
import com.example.dpm_project.models.Module;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ModuleRepository {
    private ModuleDao moduleDao;
    private LiveData<List<Module>> allModules;
    private Executor executor = Executors.newSingleThreadExecutor();

    public ModuleRepository(Application application){
        ModuleDatabase database = ModuleDatabase.getInstance(application);
        moduleDao = database.moduleDao();
        allModules = moduleDao.getAllModules();
    }

    public void insert(Module module){
        executor.execute(() -> moduleDao.insert(module));

    }
    public void update(Module module){
        executor.execute(() -> moduleDao.update(module));
    }
    public void delete(Module module){
        executor.execute(() -> moduleDao.delete(module));
    }
    public LiveData<List<Module>> getAllModules() {
        return allModules;
    }

}
