package com.mtu.studentrecords.Model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Student implements Serializable {
    private  String studentId;
    private  String fname;
    private  String lname;
    private  String email;
    private  String gender;
    private  String dob;
    private Set<Module> modules = new HashSet<Module>();

    public Student(String studentId, String fname, String lname, String email, String gender, String dob) {
        this.setStudentId(studentId);
        this.setFname(fname);
        this.setLname(lname);
        this.setEmail(email);
        this.setGender(gender);
        this.setDob(dob);
    }
    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }
    public String getStudentId()
    {
        return studentId;
    }
    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getFname()
    {
        return fname;
    }
    public void setLname(String lname)
    {
        this.lname = lname;
    }
    public String getLname()
    {
        return lname;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }

    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public String getDob()
    {
        return dob;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
    public String getGender()
    {
        return gender;
    }

    public String toString()
    {
        return "Student ID: " + getStudentId() + " First Name: " + getFname() + " Last Name: " + getLname() + " Email: " + getEmail() + " Gender: " + getGender() + " Date of Birth: " + getDob();
    }
    public Set<Module> getModules() {
        return modules;
    }
    public void addModule(Module module) {
        modules.add(module);
    }
    public void removeModule(Module module) {
        modules.remove(module);
    }
    public void grade(Module module, String studentId, double grade) {
        for (Module mod : modules) {
            if (mod.equals(module)) {
                mod.setGrade(studentId, grade);
                break;
            }
        }
    }
}
