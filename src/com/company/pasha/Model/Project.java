package com.company.pasha.Model;

/**
 * Created by pasha on 22.04.2016.
 */
public class Project {
    private int numberOfProjects;
    private int numberOfSolvedProjects;

    public Project(int projects, int solvedProjects) {
        numberOfProjects = projects;
        numberOfSolvedProjects = solvedProjects;
    }

    public int getNumberOfSolvedProjects() {
        return numberOfSolvedProjects;
    }

    public int getNumberOfProjects() {
        return numberOfProjects;
    }

}
