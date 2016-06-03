package com.company.pasha.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 22.04.2016.
 */
public class Group {
    private List<Student> studentList;
    private int numberOfGroup;

    public Group(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
        studentList = new ArrayList<Student>();
    }

    public void addStudent(Student stud){
        studentList.add(stud);
    }

    public Student getStudent(int numberOfStudent) {
        return studentList.get(numberOfStudent);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void removeStudentList(){
        studentList.clear();
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
