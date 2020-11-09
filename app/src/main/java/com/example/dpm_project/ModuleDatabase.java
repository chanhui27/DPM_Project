package com.example.dpm_project;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dpm_project.dao.ModuleDao;
import com.example.dpm_project.dao.PathwayDao;
import com.example.dpm_project.dao.PathwayModuleCrossRefDao;
import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayModuleCrossRef;

import java.util.concurrent.Executors;

@Database(entities = {Module.class, Pathway.class, PathwayModuleCrossRef.class}, version = 1)
public abstract class ModuleDatabase extends RoomDatabase {
    private static ModuleDatabase instance;

    public abstract ModuleDao moduleDao();

    public abstract PathwayDao pathwayDao();

    public abstract PathwayModuleCrossRefDao pathwayModuleCrossRefDao();

    public static synchronized ModuleDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ModuleDatabase.class, "module_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {


                instance.pathwayDao().insert(new Pathway("Database Architecture"));
                instance.pathwayDao().insert(new Pathway("Network Engineering"));
                instance.pathwayDao().insert(new Pathway("Software Engineering"));
                instance.pathwayDao().insert(new Pathway("Web Development"));

                // Modules for all pathways indexing from 1 to 14
                instance.moduleDao().insert(new Module("COMP501", "IT Operations", 0));
                instance.moduleDao().insert(new Module("COMP502", "Fundamentals of Programming and Problem Solving", 0));
                instance.moduleDao().insert(new Module("INFO501", "Professional Practice", 0));
                instance.moduleDao().insert(new Module("INFO502", "Business Systems Analysis & Design", 0));
                instance.moduleDao().insert(new Module("COMP503", "Introduction to Networks", 0));
                instance.moduleDao().insert(new Module("COMP504", "Operating Systems & Systems Support", 0));
                instance.moduleDao().insert(new Module("INFO503", "Database Principles", 0));
                instance.moduleDao().insert(new Module("INFO504", "Technical Support", 0));
                instance.moduleDao().insert(new Module("COMP601", "Object-Oriented Programming", 0));
                instance.moduleDao().insert(new Module("INFO601", "Data-modelling and SQL", 0));
                instance.moduleDao().insert(new Module("MATH601", "Mathematics for IT", 0));
                instance.moduleDao().insert(new Module("COMP602", "Web Development", 0));
                instance.moduleDao().insert(new Module("INFO602", "Business, Interpersonal Communications & Technical Writing", 0));
                instance.moduleDao().insert(new Module("BIZM701", "Business Essentials for IT Professionals", 0));

                // id = 15
                instance.moduleDao().insert(new Module("COMP615", "Data Centre Infrastructure", 0));
                //id = 16
                instance.moduleDao().insert(new Module("COMP605", "Data Structures & Algorithms", 0));
                //id = 17
                instance.moduleDao().insert(new Module("COMP603", "The Web Environment", 0));
                // id = 18
                instance.moduleDao().insert(new Module("INFO603", "Systems Administration", 0));
                // id = 19
                instance.moduleDao().insert(new Module("COMP609", "Applications Development", 0));
                // id = 20
                instance.moduleDao().insert(new Module("COMP606", "Web Programming", 0));
                // id = 21
                instance.moduleDao().insert(new Module("COMP604", "Routing & Switching Essentials", 0));
                // id = 22
                instance.moduleDao().insert(new Module("COMP602", "Mathematics for Programming", 0));
                // id = 23
                instance.moduleDao().insert(new Module("INFO604", "Database Systems", 0));
                // id = 24
                instance.moduleDao().insert(new Module("INFO605", "Human-Computer Interaction", 0));
                // id = 25
                instance.moduleDao().insert(new Module("COMP701", "Advanced Networking", 0));
                // id = 26
                instance.moduleDao().insert(new Module("COMP707", "Principles of Software Testing", 0));
                // id = 27
                instance.moduleDao().insert(new Module("INFO704", "Data-Warehousing and Business Intelligence", 0));
                // id = 28
                instance.moduleDao().insert(new Module("COMP716", "Advanced Web Technologies", 0));
                // id = 29
                instance.moduleDao().insert(new Module("INFO702", "Cyber-Security", 0));
                // id = 30
                instance.moduleDao().insert(new Module("INFO706", "Database Front-End Applications", 0));
                // id = 31
                instance.moduleDao().insert(new Module("COMP704", "Network Security", 0));
                // id = 32
                instance.moduleDao().insert(new Module("COMP709", "Mobile Applications Development", 0));
                // id = 33
                instance.moduleDao().insert(new Module("COMP702", "Scaling Networks", 0));
                // id = 34
                instance.moduleDao().insert(new Module("COMP706", "Game Development", 0));
                // id = 35
                instance.moduleDao().insert(new Module("INFO707", "Cloud Server Databases", 0));
                // id = 36
                instance.moduleDao().insert(new Module("COMP710", "Web Applications Development", 0));
                // id = 37
                instance.moduleDao().insert(new Module("COMP716", "Virtualisation Essentials", 0));
                // id = 38
                instance.moduleDao().insert(new Module("INFO703", "Big Data and Analytics", 0));
                // id = 39
                instance.moduleDao().insert(new Module("INFO708", "Data Visualisation", 0));
                // id = 40
                instance.moduleDao().insert(new Module("COMP714", "Network Engineering Project", 0));
                // id = 41
                instance.moduleDao().insert(new Module("INFO710", "Internship", 0));
                // id = 42
                instance.moduleDao().insert(new Module("DFNZ701", "Design Factory", 0));
                // id = 43
                instance.moduleDao().insert(new Module("COMP715", "Software Engineering Project", 0));
                // id = 44
                instance.moduleDao().insert(new Module("INFO712", "Database Architecture Project", 0));
                // id = 45
                instance.moduleDao().insert(new Module("COMP713", "Web Development Project", 0));

                for (int i = 1; i <= 4; i++) {
                    for (int j = 1; j <= 14; j++) {
                        instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(i, j));
                    }
                }

                for (int i = 1; i <= 4; i++) {
                    for (int j = 41; j <= 42; j++) {
                        instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(i, j));
                    }
                }

                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 15));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 16));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 16));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 17));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 18));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 19));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 20));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 20));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 21));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 22));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 23));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 24));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 25));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 26));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 27));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 28));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 29));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 29));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 29));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 30));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 31));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 32));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 32));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 32));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 33));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 34));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 35));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 36));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 37));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 38));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 38));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 39));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 40));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 43));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 44));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 45));

            });
        }
    };

}
