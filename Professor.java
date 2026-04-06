package com.university.model;

import java.io.Serializable;
import java.util.*;

public class Professor extends User implements Serializable {

    private List<Course> assignedCourses;

    public Professor(String id, String name, String email, String password, String department) {
        super(id, name, email, password, department);
        this.assignedCourses = new ArrayList<>();
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
    }
}