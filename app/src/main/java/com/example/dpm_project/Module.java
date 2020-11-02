package com.example.dpm_project;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
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
*/

public class Module {
    private String module;
    private String moduledesc;
    private String moduleCode;


    public Module(){}

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModuledesc() {
        return moduledesc;
    }

    public void setModuledesc(String moduledesc) {
        this.moduledesc = moduledesc;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
}
