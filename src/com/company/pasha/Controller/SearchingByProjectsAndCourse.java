package com.company.pasha.Controller;

import com.company.pasha.Model.MyTableBase;
import com.company.pasha.Model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 20.05.2016.
 */
public class SearchingByProjectsAndCourse {

    private int numberOfCourse;
    private int numberOfProjects;
    private MyTableBase myTableBase;
    private MyTableBase tableBase;
    private List<Student> studentList;
    private List<Student> studentListForDelete;

    private int numberOfAllCourses;
    private int numberOfAllGroups;

    public SearchingByProjectsAndCourse(int projects, int course, MyTableBase myTableBaseIn, MyTableBase tableBaseIn) {
        numberOfProjects = projects;
        numberOfCourse = course -1;
        myTableBase = myTableBaseIn;
        tableBase = tableBaseIn;
        studentListForDelete = new ArrayList<>();
        numberOfAllCourses=tableBase.getNumberOfAllCourses();
        numberOfAllGroups = tableBase.getNumberOfAllGroups();

        for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
            for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                tableBase.getCourseArrayList().get(courseNumber).getGroupArrayList().get(groupNumber).removeStudentList();
            }
        }

        for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
            for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                int studentNumber = 0;
                studentList = new ArrayList();
                studentList = myTableBase.getCourseArrayList().get(courseNumber).getGroupArrayList().get(groupNumber).getStudentList();
                while (studentList.size() > studentNumber){
                    if(studentList.get(studentNumber).getProject().getNumberOfProjects() == numberOfProjects && courseNumber == numberOfCourse){
                        studentListForDelete.add(studentList.get(studentNumber));
                        tableBase.setFirstName(studentList.get(studentNumber).getStudentFirstName());
                        tableBase.setSecondName(studentList.get(studentNumber).getStudentSecondtName());
                        tableBase.setMiddleName(studentList.get(studentNumber).getStudentMiddleName());
                        tableBase.setNumberOfCourse(courseNumber+1);
                        tableBase.setNumberOfGroup(groupNumber +1);
                        tableBase.setNumberOfProjects(studentList.get(studentNumber).getProject().getNumberOfProjects());
                        tableBase.setNumberOfProjects(studentList.get(studentNumber).getProject().getNumberOfSolvedProjects());
                        tableBase.setProgramLanguage(studentList.get(studentNumber).getProgramLanguage().getNameOfProgramLanguage());
                        tableBase.addToBase();
                    }
                    studentNumber++;

                }
            }
        }
    }

    public List<Student> getStudentListForDelete() {
        return studentListForDelete;
    }
}
