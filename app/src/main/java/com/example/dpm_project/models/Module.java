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

    public Module(String code, String title, int isCompleted, String aim, String level, String credit, int year, String coRequisite, String preRequisite, String stream, int semester) {
        this.code = code;
        this.title = title;
        this.aim = aim;
        this.level = level;
        this.credit = credit;
        this.year = year;
        this.coRequisite = coRequisite;
        this.preRequisite = preRequisite;
        this.stream = stream;
        this.semester = semester;
        this.isCompleted = isCompleted;
    }

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

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCoRequisite() {
        return coRequisite;
    }

    public void setCoRequisite(String coRequisite) {
        this.coRequisite = coRequisite;
    }

    public String getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(String preRequisite) {
        this.preRequisite = preRequisite;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }


}

