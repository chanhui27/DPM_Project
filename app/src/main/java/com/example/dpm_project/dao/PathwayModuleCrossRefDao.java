package com.example.dpm_project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayModuleCrossRef;
import com.example.dpm_project.models.PathwayWithModules;

import java.util.List;

@Dao
public interface PathwayModuleCrossRefDao {
    @Insert
    void insert(PathwayModuleCrossRef pathwayModuleCrossRef);
    @Transaction
    @Query("SELECT * FROM pathway_table")
    List<PathwayWithModules> getAllPathwayWithModules();



    @Transaction
    @Query("SELECT * FROM pathway_table WHERE pathwayId = :pathwayId")
    PathwayWithModules getPathwayWithModules(int pathwayId);


}