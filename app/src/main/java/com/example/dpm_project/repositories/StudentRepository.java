package com.example.dpm_project.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dpm_project.ModuleDatabase;
import com.example.dpm_project.dao.StudentDao;
import com.example.dpm_project.models.Student;
import com.example.dpm_project.models.StudentPathway;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StudentRepository {

    private StudentDao studentDao;
    private LiveData<List<StudentPathway>> allStudent;
    private Executor executor = Executors.newSingleThreadExecutor();

    public StudentRepository(Application application){
        ModuleDatabase database = ModuleDatabase.getInstance(application);
        studentDao = database.studentDao();
        allStudent = studentDao . getStudentPathway ();
    }

    public void insert(Student student){
        executor.execute(() -> studentDao.insert(student));

    }
    public void update(Student student){
        executor.execute(() -> studentDao.update(student));
    }
    public void delete(Student student){
        executor.execute(() -> studentDao.delete(student));
    }
    public LiveData<List<StudentPathway>> getAllStudent() {
        return allStudent;
    }
}
