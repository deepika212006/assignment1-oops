package com.university.service;

import com.university.model.*;
import com.university.datastore.DataStore;

import java.util.*;

public class ProfessorService {

    private static Scanner input = new Scanner(System.in);

    public static void showMenu() {

        while (true) {

            System.out.println("\n----- PROFESSOR MENU -----");
            System.out.println("1. Login");
            System.out.println("2. Back");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    loginProfessor();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void loginProfessor() {

        System.out.println("Enter Email:");
        String email = input.nextLine();

        System.out.println("Enter Password:");
        String password = input.nextLine();

        Professor professor = DataStore.professors.get(email);

        if (professor != null && professor.getPassword().equals(password)) {

            System.out.println("Login Successful");
            professorDashboard(professor);

        } else {
            System.out.println("Invalid Credentials");
        }
    }

    private static void professorDashboard(Professor professor) {

        while (true) {

            System.out.println("\nWelcome " + professor.getName());

            System.out.println("1. View Assigned Courses");
            System.out.println("2. View Students");
            System.out.println("3. Logout");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    viewCourses(professor);
                    break;

                case 2:
                    viewStudents(professor);
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void viewCourses(Professor professor) {

        if (professor.getAssignedCourses().isEmpty()) {
            System.out.println("No courses assigned");
            return;
        }

        for (Course course : professor.getAssignedCourses()) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
    }

    private static void viewStudents(Professor professor) {

        for (Course course : professor.getAssignedCourses()) {

            System.out.println("\nCourse: " + course.getTitle());

            for (Student student : course.getEnrolledStudents()) {
                System.out.println(student.getName());
            }
        }
    }
}