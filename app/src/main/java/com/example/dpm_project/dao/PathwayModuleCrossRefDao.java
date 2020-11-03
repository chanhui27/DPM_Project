package com.example.dpm_project.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.dpm_project.models.PathwayModuleCrossRef;
@Dao
public interface PathwayModuleCrossRefDao {
    @Insert
    void insert(PathwayModuleCrossRef pathwayModuleCrossRef);

}
