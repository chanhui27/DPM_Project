package com.example.dpm_project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.ModuleWithPathways;
import com.example.dpm_project.models.PathwayWithModules;

import java.util.List;

@Dao
public  interface  ModuleDao {
    @Insert
    void insert(Module module);
    @Update
    void update(Module module);
    @Delete
    void delete(Module module);
    @Query("SELECT * FROM module_table ORDER BY isCompleted")
    LiveData<List<Module>> getAllModules();
    @Transaction
    @Query("SELECT * FROM module_table")
    LiveData<List<ModuleWithPathways>> getModuleWithPathways();



}