package com.example.dpm_project;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "module_table")
public class Module {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String code;
    private String title;
    private int isCompleted;

    public Module(String code, String title, int isCompleted) {
        this.code = code;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getIsCompleted() {
        return isCompleted;
    }
}
