package com.example.dpm_project.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "module_table")
public class Module {
    @PrimaryKey(autoGenerate = true)
    private int moduleId;
    private String code;
    private String title;

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    private int isCompleted;

    public Module(String code, String title, int isCompleted) {
        this.code = code;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getModuleId() {
        return moduleId;
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
