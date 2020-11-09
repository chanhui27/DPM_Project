package com.example.dpm_project.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ModuleWithPathways {
    @Embedded
    public Module module;
    @Relation(
            parentColumn = "moduleId",
            entityColumn = "pathwayId",
            associateBy = @Junction(PathwayModuleCrossRef.class)
    )
    public List<Pathway> pathways;
}
