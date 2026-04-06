package com.university.model;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected String department;

    public User(String id, String name, String email, String password, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}