package com.company.pasha.Model;

import java.util.ArrayList;

/**
 * Created by pasha on 22.04.2016.
 */
public class Student {
    private String studentFirstName;
    private String studentSecondtName;
    private String studentMiddleName;
    private String fio;
    private ProgramLanguage programLanguage;
    private Project project;

    public Student(String firstName, String secondtName, String middleName) {
        studentFirstName = firstName;
        studentSecondtName = secondtName;
        studentMiddleName = middleName;
        setFio();
    }

    void setFio(){
        fio = studentFirstName+" "+studentSecondtName+" "+studentMiddleName;
    }

    public String getFio() {
        return fio;
    }

    public String getStudentSecondtName() {
        return studentSecondtName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public ProgramLanguage getProgramLanguage() {
        return programLanguage;
    }

    public void setProgramLanguage(ProgramLanguage programLanguage) {
        this.programLanguage = programLanguage;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
