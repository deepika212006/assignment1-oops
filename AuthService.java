package com.university.service;

import com.university.model.*;
import com.university.datastore.DataStore;

public class AuthService {

    public static Student studentLogin(String email, String password) {

        Student student = DataStore.students.get(email);

        if (student != null && student.getPassword().equals(password)) {
            return student;
        }

        return null;
    }

    public static void registerStudent(Student student) {
        DataStore.students.put(student.getEmail(), student);
    }
}