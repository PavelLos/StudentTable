package com.company.pasha.Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 28.04.2016.
 */
public class MyTableBase {
    private String firstName;
    private String secondName;
    private String middleName;
    private int numberOfCourse;
    private int numberOfGroup;
    private String programLanguage;
    private int numberOfSolvedProjects;
    private int numberOfProjects;

    private static final int numberOfAllCourses = 5;
    private static final int numberOfAllGroups = 3;

    private List<Course> courseArrayList;


    public MyTableBase() {
        courseArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfAllCourses; i++) {
            courseArrayList.add(new Course(i + 1, numberOfAllGroups));
        }

    }

    public void addToBase() {
        Student student = new Student(firstName, secondName, middleName);
        courseArrayList.get(numberOfCourse - 1).getGroupArrayList().get(numberOfGroup - 1).addStudent(student);
        student.setProgramLanguage(new ProgramLanguage(programLanguage));
        student.setProject(new Project(numberOfProjects, numberOfSolvedProjects));
    }

    public void removeFromBase(List studentListForDelete) {
        for (int studentForDeleteNumber = 0; studentForDeleteNumber < studentListForDelete.size(); studentForDeleteNumber++) {
            for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
                for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                    int studentNumber = 0;
                    List<Student> studentList = new ArrayList();
                    studentList = courseArrayList.get(courseNumber).getGroupArrayList().get(groupNumber).getStudentList();
                    while (studentList.size() > studentNumber) {
                        if (studentList.get(studentNumber).equals(studentListForDelete.get(studentForDeleteNumber)))
                            courseArrayList.get(courseNumber).getGroupArrayList().get(groupNumber).getStudentList().remove(studentNumber);
                        studentNumber++;
                    }
                }
            }
        }
    }

    public void removeAll(){
        for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
            for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                courseArrayList.get(courseNumber).getGroupArrayList().get(groupNumber).removeStudentList();
            }
        }
    }

    public List<Course> getCourseArrayList() {
        return courseArrayList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setNumberOfCourse(int numberOfCourse) {
        this.numberOfCourse = numberOfCourse;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public void setProgramLanguage(String programLanguage) {
        this.programLanguage = programLanguage;
    }

    public void setNumberOfSolvedProjects(int numberOfSolvedProjects) {
        this.numberOfSolvedProjects = numberOfSolvedProjects;
    }

    public void setNumberOfProjects(int numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

    public int getNumberOfAllCourses() {
        return numberOfAllCourses;
    }

    public int getNumberOfAllGroups() {
        return numberOfAllGroups;
    }
}
