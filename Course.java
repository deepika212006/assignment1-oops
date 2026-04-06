package com.university.model;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {

    private String courseCode;
    private String title;
    private int credits;
    private Professor professor;
    private Schedule schedule;
    private List<Course> prerequisites;
    private List<Student> enrolledStudents;
    private int enrollmentLimit;

    public Course(String courseCode, String title, int credits) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.prerequisites = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.enrollmentLimit = 50;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student) {

        if(enrolledStudents.size() < enrollmentLimit){
            enrolledStudents.add(student);
        } else {
            System.out.println("Enrollment limit reached");
        }
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void addPrerequisite(Course course){
        prerequisites.add(course);
    }
    
    @Override
    public String toString() {
        return courseCode + " - " + title + " (" + credits + " credits)";
    }
}

