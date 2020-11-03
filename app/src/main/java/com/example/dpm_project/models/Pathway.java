package com.example.dpm_project.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pathway_table")
public class Pathway {
    @PrimaryKey(autoGenerate = true)
    public int pathwayId;
    public String name;

    public Pathway(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
