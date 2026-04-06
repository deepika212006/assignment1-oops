package com.university.datastore;

import com.university.model.*;

import java.io.*;
import java.util.*;

public class DataStore {

    public static Map<String, Student> students = new HashMap<>();
    public static Map<String, Professor> professors = new HashMap<>();
    public static Map<String, Course> courses = new HashMap<>();
    public static Map<String, Admin> admins = new HashMap<>();

    public static void saveData() {

        try {

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("data.ser"));

            out.writeObject(students);
            out.writeObject(professors);
            out.writeObject(courses);
            out.writeObject(admins);

            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadData() {

        try {

            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("data.ser"));

            students = (Map<String, Student>) in.readObject();
            professors = (Map<String, Professor>) in.readObject();
            courses = (Map<String, Course>) in.readObject();
            admins = (Map<String, Admin>) in.readObject();

            in.close();

        } catch (Exception e) {
            System.out.println("No previous data found");
        }
    }
}