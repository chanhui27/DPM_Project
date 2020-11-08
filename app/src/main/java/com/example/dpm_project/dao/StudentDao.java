package com.example.dpm_project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dpm_project.models.Student;

import java.util.List;

public interface StudentDao {

    @Insert
    void insert(Student student);
    @Update
    void update(Student student);
    @Delete
    void delete(Student student);
    @Query("SELECT * FROM student_table ")
    LiveData<List<Student>> getAllStudents();
}
