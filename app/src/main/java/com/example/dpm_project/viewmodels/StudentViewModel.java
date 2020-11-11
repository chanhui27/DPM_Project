package com.example.dpm_project.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Student;
import com.example.dpm_project.models.StudentPathway;
import com.example.dpm_project.repositories.ModuleRepository;
import com.example.dpm_project.repositories.StudentRepository;

import java.util.List;

public  class  StudentViewModel  extends  AndroidViewModel {

    private StudentRepository repository;
    private LiveData<List<StudentPathway>> allStudent;

    public   StudentViewModel ( @NonNull  Application  application ) {
        super(application);
        repository = new StudentRepository(application);
        allStudent = repository.getAllStudent();
    }

    public LiveData<Student> getStudent(int id){
        return repository.getStudent(id);
    }

    public void insert(Student student) {
        repository.insert(student);
    }

    public void update(Student student) {
        repository.update(student);
    }

    public void delete(Student student) {
        repository.delete(student);
    }

    public LiveData<List<StudentPathway>> getAllStudent() {
        return allStudent;
    }
}