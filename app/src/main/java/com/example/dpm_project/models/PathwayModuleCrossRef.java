package com.example.dpm_project.models;

import androidx.room.Entity;

@Entity(primaryKeys = {"pathwayId", "moduleId"}, tableName = "pathway_module_table")
public class PathwayModuleCrossRef {
    public int pathwayId;
    public int moduleId;

    public PathwayModuleCrossRef(int pathwayId, int moduleId) {
        this.pathwayId = pathwayId;
        this.moduleId = moduleId;
    }
}