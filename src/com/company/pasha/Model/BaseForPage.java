package com.company.pasha.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 26.05.2016.
 */
public class BaseForPage {
    private MyTableBase tableBase;
    private MyTableBase myTableBase;

    private int numberOfRows; //кол-во строк на странице
    private int numberOfPages; //кол-во страниц
    private int startRow; //начало страницы
    private int rowsOnTheLatestPage; // кол-во студентов на последней странице
    private int currentPage; //текущая страница
    private boolean latestPageFlag; // последняя страница
    private int sizeOfBase;

    private int numberOfAllCourses;
    private int numberOfAllGroups;

    public BaseForPage(MyTableBase myTable) {
        myTableBase = myTable;
        numberOfAllCourses = myTableBase.getNumberOfAllCourses();
        numberOfAllGroups = myTableBase.getNumberOfAllGroups();
        tableBase = new MyTableBase();
        numberOfRows = 2;
        refresh();
    }


    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
        refresh();
    }

    public void dataChange(int startRow, int numberOfRows) {
        removeDataBase();
        int studentNumberForStartRow = 0;
        for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
            for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                int studentNumber = 0;
                List<Student> studentList = new ArrayList();
                studentList = myTableBase.getCourseArrayList().get(courseNumber).getGroupArrayList().get(groupNumber).getStudentList();
                while (studentList.size() > studentNumber) {
                    if (startRow <= studentNumberForStartRow) {
                        if (studentNumberForStartRow < startRow + numberOfRows) {
                            tableBase.setFirstName(studentList.get(studentNumber).getStudentFirstName());
                            tableBase.setSecondName(studentList.get(studentNumber).getStudentSecondtName());
                            tableBase.setMiddleName(studentList.get(studentNumber).getStudentMiddleName());
                            tableBase.setNumberOfCourse(courseNumber + 1);
                            tableBase.setNumberOfGroup(groupNumber + 1);
                            tableBase.setNumberOfProjects(studentList.get(studentNumber).getProject().getNumberOfProjects());
                            tableBase.setNumberOfSolvedProjects(studentList.get(studentNumber).getProject().getNumberOfSolvedProjects());
                            tableBase.setProgramLanguage(studentList.get(studentNumber).getProgramLanguage().getNameOfProgramLanguage());
                            tableBase.addToBase();
                        }
                    }
                    studentNumber++;
                    studentNumberForStartRow++;
                }
            }
        }
    }

    public void refresh() {
        sizeOfBase = 0;
        for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
            for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                sizeOfBase += myTableBase.getCourseArrayList().get(courseNumber).getGroupArrayList().get(groupNumber).getStudentList().size();
            }
        }
        rowsOnTheLatestPage = sizeOfBase % numberOfRows;
        numberOfPages = sizeOfBase / numberOfRows;
        if (rowsOnTheLatestPage != 0)
            numberOfPages += 1;
        if (rowsOnTheLatestPage == 0)
            rowsOnTheLatestPage = numberOfRows;
        latestPageFlag = false;
        if(numberOfPages == 1){
            latestPageFlag = true;
        }
        currentPage = 1;
        startRow = 0;
        removeDataBase();
        dataChange(startRow, numberOfRows);

    }

    public void nextPage() {
        if (!latestPageFlag) {
            if (currentPage + 1 < numberOfPages) {
                removeDataBase();
                startRow += numberOfRows;
                dataChange(startRow, numberOfRows);

                currentPage++;
            } else {
                removeDataBase();
                startRow += numberOfRows;
                dataChange(startRow, rowsOnTheLatestPage);
                latestPageFlag = true;
                currentPage++;

            }
        }
    }

    public void previousPage() {
        if (currentPage != 1) {
            removeDataBase();
            startRow -= numberOfRows;
            dataChange(startRow , numberOfRows);
            currentPage--;
            latestPageFlag = false;
        }
    }

    public void firstPage() {

        while (currentPage != 1)
            previousPage();
    }

    public void lastPage() {
        while (!latestPageFlag) {
            nextPage();
        }

    }

    public void removeDataBase() {
        for (int courseNumber = 0; courseNumber < numberOfAllCourses; courseNumber++) {
            for (int groupNumber = 0; groupNumber < numberOfAllGroups; groupNumber++) {
                tableBase.getCourseArrayList().get(courseNumber).getGroupArrayList().get(groupNumber).removeStudentList();
            }
        }

    }

    public MyTableBase getTableBase() {
        return tableBase;
    }

    public void setTableBase(MyTableBase tableBase) {
        this.tableBase = tableBase;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
