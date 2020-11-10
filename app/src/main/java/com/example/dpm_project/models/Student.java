package com.example.dpm_project.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public  class  Student {

    @PrimaryKey(autoGenerate = true)
    private int sid;
    private String studentId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String imageUrl;
    private int studentPathwayId;

    public int getStudentPathwayId() {
        return studentPathwayId;
    }

    public void setStudentPathwayId(int studentPathwayId) {
        this.studentPathwayId = studentPathwayId;
    }

    public int getSid() {
        return sid;
    }

    public  void  setSid ( int  sid ) {
        this.sid = sid;
    }

    public Student(String studentId, String name, String email, String address, String phone, String imageUrl) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.imageUrl = imageUrl;

    }

    public  String  getImageUrl () {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public  String  getName () {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public  String  getEmail () {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}