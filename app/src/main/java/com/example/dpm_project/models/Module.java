package com.example.dpm_project.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "module_table")
public class Module {
    @PrimaryKey(autoGenerate = true)
    private int moduleId;
    private String code;
    private String title;
    private String aim;
    private String level;
    private String credit;
    private int year;
    private String coRequisite;
    private String preRequisite;
    private String stream;
    private int semester;
    private int isCompleted;

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }



    public Module(String code, String title, int isCompleted, String aim, String level, String credit, int year, String coRequisite, String preRequisite, String stream, int semester) {
        this.code = code;
        this.title = title;
        this.isCompleted = isCompleted;
        this.aim = aim;
        this.level = level;
        this.credit = credit;
        this.year = year;
        this.coRequisite = coRequisite;
        this.preRequisite = preRequisite;
        this.stream=stream;
        this.semester = semester;

    }


    /*public Module(String code, String title, int isCompleted, String aim, String level, String credit, int year, String coRequisite, int semester) {
        this.code = code;
        this.title = title;
        this.isCompleted = isCompleted;
        this.aim = aim;
        this.level = level;
        this.credit = credit;
        this.year = year;
        this.coRequisite = coRequisite;
        this.semester = semester;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return getModuleId() == module.getModuleId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleId());
    }


    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
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

    public String getAim(){ return aim;}

    public int getYear() {
        return year;
    }

    public String getLevel() {
        return level;
    }

    public String getCredit(){return credit;}

    public String getCoRequisite(){
        return coRequisite;
    }

    public int getSemester(){
        return semester;
    }

    public String getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(String preRequisite) {
        this.preRequisite = preRequisite;
    }
}
