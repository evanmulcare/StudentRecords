package com.mtu.studentrecords.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Module implements Serializable {

    private String title;
    private String crn;
    private final HashSet<Student> students = new HashSet<>();
    private final Map<String, Double> grades = new HashMap<>();


    public Module(String title, String crn) {
        this.setTitle(title);
        this.setCrn(crn);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getCrn() {
        return crn;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void setGrade(String studentId, double grade) {
        grades.put(studentId, grade);
    }

    public double getGrade(String studentId) {
        return grades.getOrDefault(studentId, 0.0);
    }

}