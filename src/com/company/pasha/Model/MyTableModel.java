package com.company.pasha.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 25.04.2016.
 */
public class MyTableModel extends AbstractTableModel {
    private MyTableBase myTableBase;


    private String fio;
    private int numberOfCourse;
    private int numberOfGroup;
    private String programLanguage;
    private int numberOfSolvedProjects;
    private int numberOfProjects;

    private int numberOfAllCourses;
    private int numberOfAllGroups;


    public MyTableModel(MyTableBase base) {
        myTableBase = base;
        numberOfAllCourses=myTableBase.getNumberOfAllCourses();
        numberOfAllGroups = myTableBase.getNumberOfAllGroups();

    }

    @Override
    public String getColumnName(int columnIndex) {
        String columnName = "";
        switch (columnIndex) {
            case 0:
                columnName = "ФИО";
                break;
            case 1:
                columnName = "Курс";
                break;
            case 2:
                columnName = "Группа";
                break;
            case 3:
                columnName = "Общее число работ";
                break;
            case 4:
                columnName = "Количество выполненных работ";
                break;
            case 5:
                columnName = "Язык программирования";
                break;
        }
        return columnName;
    }

    @Override
    public int getRowCount() {
        int numberOfRows = 0;
        for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
            for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                numberOfRows += myTableBase.getCourseArrayList().get(courseNumber).getGroupArrayList().get(groupNumber).getStudentList().size();
            }
        }
        return numberOfRows;

    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        int studentNumberForRowIndex = 0;
        for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
            for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                int studentNumber = 0;
                List<Student> studentList = new ArrayList();
                studentList = myTableBase.getCourseArrayList().get(courseNumber).getGroupArrayList().get(groupNumber).getStudentList();
                while (studentList.size() > studentNumber) {
                    if (studentNumberForRowIndex == rowIndex) {
                        numberOfCourse = courseNumber + 1;
                        numberOfGroup = groupNumber + 1;
                        fio = studentList.get(studentNumber).getFio();
                        programLanguage = studentList.get(studentNumber).getProgramLanguage().getNameOfProgramLanguage();
                        numberOfProjects = studentList.get(studentNumber).getProject().getNumberOfProjects();
                        numberOfSolvedProjects = studentList.get(studentNumber).getProject().getNumberOfSolvedProjects();
                    }
                    studentNumberForRowIndex++;
                    studentNumber++;
                }
            }
        }
        switch (columnIndex) {
            case 0:
                return fio;
            case 1:
                return numberOfCourse;
            case 2:
                return numberOfGroup;
            case 3:
                return numberOfProjects;
            case 4:
                return numberOfSolvedProjects;
            case 5:
                return programLanguage;
            default:
                return "";
        }
    }

}

