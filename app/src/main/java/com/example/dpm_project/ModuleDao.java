package com.example.dpm_project;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ModuleDao {
    @Insert
    void insert(Module module);
    @Update
    void update(Module module);
    @Delete
    void delete(Module module);
    @Query("SELECT * FROM module_table ORDER BY isCompleted")
    LiveData<List<Module>> getAllModules();



}
