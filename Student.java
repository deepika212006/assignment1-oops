package com.university.model;

import java.util.*;

public class Student extends User {

    private int semester;
    private Map<String, Course> registeredCourses;
    private int currentCredits;

    public Student(String id, String name, String email, String password, String department) {
        super(id, name, email, password, department);
        this.semester = 1;
        this.registeredCourses = new HashMap<>();
        this.currentCredits = 0;
    }

    public boolean registerCourse(Course course) {

        if (registeredCourses.containsKey(course.getCourseCode())) {
            System.out.println("Already registered in this course");
            return false;
        }

        registeredCourses.put(course.getCourseCode(), course);
        return true;
    }
    
    public void dropCourse(String courseCode) {

        Course course = registeredCourses.remove(courseCode);

        if (course != null) {
            currentCredits -= course.getCredits();
        }
    }
    
//    public void dropCourse(String courseCode) {
//
//        if (registeredCourses.containsKey(courseCode)) {
//            registeredCourses.remove(courseCode);
//            System.out.println("Course Dropped Successfully");
//        } else {
//            System.out.println("You are not registered in this course");
//        }
//    }

    public Map<String, Course> getRegisteredCourses() {
        return registeredCourses;
    }
}