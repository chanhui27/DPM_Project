package com.example.dpm_project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayWithModules;

import java.util.List;

@Dao
public   interface   PathwayDao {
    @Insert
    void insert(Pathway pathway);

    @Update
    void update(Pathway pathway);

    @Delete
    void delete(Pathway pathway);

    @Query("SELECT * FROM pathway_table")
    LiveData<List<Pathway>> getAllPathways();

}