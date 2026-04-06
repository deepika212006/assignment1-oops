package com.university.service;

import com.university.model.Student;
import com.university.model.Course;
import com.university.datastore.DataStore;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentService {

    private static Scanner input = new Scanner(System.in);

    public static void showMenu() {

        while (true) {

            System.out.println("\n----- STUDENT MENU -----");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    registerStudent();
                    break;

                case 2:
                    loginStudent();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void registerStudent() {

        System.out.println("Enter Name:");
        String name = input.nextLine();

        System.out.println("Enter Email:");
        String email = input.nextLine();

        // Check if student already exists
        if (DataStore.students.containsKey(email)) {
            System.out.println("Student already registered with this Email ID. Use a different one.");
            return;
        }

        // Email validation
        if (!email.endsWith("@svnit.ac.in")) {
            System.out.println("Email must end with @svnit.ac.in");
            return;
        }

        System.out.println("Enter Password:");
        String password = input.nextLine();

        // Password validation
        if (!isValidPassword(password)) {
            System.out.println("Invalid Password");
            System.out.println("Password must contain:");
            System.out.println("1 Uppercase, 1 Lowercase, 1 Digit, 1 Special Character");
            System.out.println("Length between 5 to 12");
            return;
        }

        System.out.println("Enter Department:");
        String dept = input.nextLine();

        String id = UUID.randomUUID().toString();

        Student student = new Student(id, name, email, password, dept);

        DataStore.students.put(email, student);

        System.out.println("Registration Successful");
    }
    
    private static boolean isValidPassword(String password) {

        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{5,12}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
    
	private static void loginStudent() {

        System.out.println("Enter Email:");
        String email = input.nextLine();

        System.out.println("Enter Password:");
        String password = input.nextLine();

        Student student = DataStore.students.get(email);

        if (student != null && student.getPassword().equals(password)) {

            System.out.println("Login Successful");
            studentDashboard(student);

        } else {
            System.out.println("Invalid Credentials");
        }
    }

	private static void viewCourses() {

	    if (DataStore.courses.isEmpty()) {
	        System.out.println("No courses available");
	        return;
	    }

	    System.out.println("\n------------------------------------------------------------------------------------------");
	    System.out.printf("%-10s %-25s %-8s %-20s %-20s\n",
	            "Code", "Course Name", "Credits", "Professor", "Prerequisites");
	    System.out.println("------------------------------------------------------------------------------------------");

	    for (Course course : DataStore.courses.values()) {

	        String professorName = (course.getProfessor() != null)
	                ? course.getProfessor().getName()
	                : "Not Assigned";

	        // Convert prerequisites list to string
	        StringBuilder prereq = new StringBuilder();

	        for (Course c : course.getPrerequisites()) {
	            prereq.append(c.getCourseCode()).append(" ");
	        }

	        if (prereq.length() == 0) {
	            prereq.append("None");
	        }

	        System.out.printf("%-10s %-25s %-8d %-20s %-20s\n",
	                course.getCourseCode(),
	                course.getTitle(),
	                course.getCredits(),
	                professorName,
	                prereq.toString());
	    }
	}

    private static void registerCourse(Student student) {

        System.out.println("Enter Course Code:");
        String code = input.nextLine();

        Course course = DataStore.courses.get(code);

        if (course == null) {
            System.out.println("Course not found");
            return;
        }

        boolean success = student.registerCourse(course);

        if (success) {
            course.addStudent(student);
            System.out.println("Course Registered Successfully");
        }
    }

    private static void dropCourse(Student student) {

        System.out.println("Enter Course Code:");
        String code = input.nextLine();

        student.dropCourse(code);

        System.out.println("Course Dropped");
    }

    private static void viewRegisteredCourses(Student student) {

        if (student.getRegisteredCourses().isEmpty()) {
            System.out.println("No registered courses");
            return;
        }

        for (Course course : student.getRegisteredCourses().values()) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
    }

    private static void studentDashboard(Student student) {

        while (true) {

            System.out.println("\nWelcome " + student.getName());

            System.out.println("1. View Available Courses");
            System.out.println("2. Register Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View My Courses");
            System.out.println("5. Logout");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    viewCourses();
                    break;

                case 2:
                    registerCourse(student);
                    break;

                case 3:
                    dropCourse(student);
                    break;

                case 4:
                    viewRegisteredCourses(student);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}