package com.example.dpm_project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Student;
import com.example.dpm_project.models.StudentPathway;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insert(Student student);
    @Update
    void update(Student student);
    @Delete
    void delete(Student student);
    @Transaction
    @Query("SELECT * FROM student_table")
    /*LiveData<List<Student>> getAllStudent();*/
    public LiveData<List<StudentPathway>> getStudentPathway();
}
