package com.university.main;

import com.university.datastore.DataStore;
import com.university.service.*;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Load saved data
        DataStore.loadData();

        System.out.println("-----WELCOME TO THE UNIVERSITY COURSE REGISTRATION SYSTEM-----");

        while (true) {

            System.out.println("\nEnter your choice:");
            System.out.println("1. Enter the application");
            System.out.println("2. Exit the application");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nEnter your role:");
                    System.out.println("1. Student");
                    System.out.println("2. Professor");
                    System.out.println("3. Admin");

                    int roleChoice = input.nextInt();

                    switch (roleChoice) {

                        case 1:
                            System.out.println("Student Portal");
                            StudentService.showMenu();
                            break;

                        case 2:
                            System.out.println("Professor Portal");
                            ProfessorService.showMenu();
                            break;

                        case 3:
                            System.out.println("Admin Portal");
                            AdminService.showMenu();
                            break;

                        default:
                            System.out.println("Invalid role choice");
                    }
                    break;

                case 2:
                    System.out.println("Exiting application...");
                    DataStore.saveData();
                    System.exit(0);
                    
                case 3:
                    System.out.println("Admin Portal");
                    AdminService.showMenu();
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}