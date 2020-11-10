package com.example.dpm_project.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.dpm_project.models.PathwayModuleCrossRef;
import com.example.dpm_project.models.PathwayWithModules;

@Dao
public interface PathwayModuleCrossRefDao {
    @Insert
    void insert(PathwayModuleCrossRef pathwayModuleCrossRef);

    @Transaction
    @Query("SELECT * FROM pathway_table WHERE pathwayId = :pathwayId")
    PathwayWithModules getPathwayWithModules(int pathwayId);

}