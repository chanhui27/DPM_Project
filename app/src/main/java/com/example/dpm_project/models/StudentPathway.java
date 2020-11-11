package com.example.dpm_project.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class StudentPathway {

    @Embedded public Student student;
    @Relation(
            parentColumn = "studentPathwayId",
            entityColumn = "pathwayId"
    )
    public Pathway pathway;
}