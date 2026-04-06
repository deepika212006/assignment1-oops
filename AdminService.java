package com.university.service;

import com.university.model.*;
import com.university.datastore.DataStore;

import java.util.*;

public class AdminService {

    private static Scanner input = new Scanner(System.in);

    // Fixed Admin Credentials
    private static final String ADMIN_EMAIL = "admin@svnit.ac.in";
    private static final String ADMIN_PASSWORD = "Svnit@123";

    public static void showMenu() {

        System.out.println("\n----- ADMIN LOGIN -----");

        System.out.println("Enter Email:");
        String email = input.nextLine();

        System.out.println("Enter Password:");
        String password = input.nextLine();

        if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {

            System.out.println("Admin Login Successful");
            adminDashboard();

        } else {
            System.out.println("Invalid Credentials");
        }
    }

    private static void adminDashboard() {

        while (true) {

            System.out.println("\n----- ADMIN DASHBOARD -----");

            System.out.println("1. Add Course");
            System.out.println("2. View Courses");
            System.out.println("3. Register Professor");
            System.out.println("4. Assign Course to Professor");
            System.out.println("5. View Professors");
            System.out.println("6. View Students");
            System.out.println("7. Logout");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    addCourse();
                    break;

                case 2:
                    viewCourses();
                    break;

                case 3:
                    registerProfessor();
                    break;

                case 4:
                    assignCourseToProfessor();
                    break;

                case 5:
                    viewProfessors();
                    break;

                case 6:
                    viewStudents();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void addCourse() {

        System.out.println("Enter Course Code:");
        String code = input.nextLine();

        System.out.println("Enter Course Title:");
        String title = input.nextLine();

        System.out.println("Enter Credits:");
        int credits = input.nextInt();
        input.nextLine();

        Course course = new Course(code, title, credits);

        DataStore.courses.put(code, course);

        System.out.println("Course Added Successfully");
    }

    private static void viewCourses() {

        if (DataStore.courses.isEmpty()) {
            System.out.println("No Courses Available");
            return;
        }

        for (Course course : DataStore.courses.values()) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
    }

    private static void registerProfessor() {

        System.out.println("Enter Name:");
        String name = input.nextLine();

        System.out.println("Enter Email:");
        String email = input.nextLine();

        // prevent duplicate
        if (DataStore.professors.containsKey(email)) {
            System.out.println("Professor already exists");
            return;
        }

        System.out.println("Enter Password:");
        String password = input.nextLine();

        System.out.println("Enter Department:");
        String dept = input.nextLine();

        String id = UUID.randomUUID().toString();

        Professor professor = new Professor(id, name, email, password, dept);

        DataStore.professors.put(email, professor);

        System.out.println("Professor Registered Successfully");
    }

    private static void assignCourseToProfessor() {

        System.out.println("Enter Professor Email:");
        String email = input.nextLine();

        Professor professor = DataStore.professors.get(email);

        if (professor == null) {
            System.out.println("Professor not found");
            return;
        }

        System.out.println("Enter Course Code:");
        String code = input.nextLine();

        Course course = DataStore.courses.get(code);

        if (course == null) {
            System.out.println("Course not found");
            return;
        }

        // assign both sides
        course.setProfessor(professor);
        professor.assignCourse(course);

        System.out.println("Course Assigned to Professor Successfully");
    }

    private static void viewProfessors() {

        if (DataStore.professors.isEmpty()) {
            System.out.println("No Professors Available");
            return;
        }

        for (Professor professor : DataStore.professors.values()) {

            System.out.println("\nID: " + professor.getId());
            System.out.println("Name: " + professor.getName());
            System.out.println("Email: " + professor.getEmail());
            System.out.println("Department: " + professor.getDepartment());
        }
    }

    private static void viewStudents() {

        if (DataStore.students.isEmpty()) {
            System.out.println("No Students Available");
            return;
        }

        for (Student student : DataStore.students.values()) {

            System.out.println("\nID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Department: " + student.getDepartment());
        }
    }
}