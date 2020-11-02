package com.example.dpm_project;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Module.class}, version = 1)
public abstract class ModuleDatabase extends RoomDatabase {
    private static ModuleDatabase instance;

    public abstract ModuleDao moduleDao();

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
            });
        }
    };

}
