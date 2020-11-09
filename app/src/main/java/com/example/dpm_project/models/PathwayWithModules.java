package com.example.dpm_project.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.DatabaseView;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;
public class PathwayWithModules {
    @Embedded public Pathway pathway;
    @Relation(
            parentColumn = "pathwayId",
            entityColumn = "moduleId",
            associateBy = @Junction(PathwayModuleCrossRef.class)
    )
    public List<Module> modules;
}
