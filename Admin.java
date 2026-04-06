package com.university.model;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(String id, String name, String email, String password, String department) {
        super(id, name, email, password, department);
    }
}