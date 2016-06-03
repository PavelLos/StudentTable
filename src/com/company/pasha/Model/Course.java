package com.company.pasha.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 22.04.2016.
 */
public class Course {
    private int numberOfCourse;
    private List<Group> groupArrayList;
    private int numberOfAllGroups;


    public List<Group> getGroupArrayList() {
        return groupArrayList;
    }

    public Course(int numberOfCourse, int numberOfAllGroups) {

        this.numberOfCourse = numberOfCourse;
        this.numberOfAllGroups = numberOfAllGroups;
        groupArrayList = new ArrayList<>();
        for (int groupNumber = 0; groupNumber < this.numberOfAllGroups; groupNumber++) {
            groupArrayList.add(new Group(groupNumber + 1));
        }
    }

    public void addToGroup(int numberOfGroup) {
    }


}
