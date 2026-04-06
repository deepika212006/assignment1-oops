package com.university.util;

import java.util.regex.*;

public class ValidationUtil {

    public static boolean isValidEmail(String email) {
        return email.endsWith(".svnit.ac.in");
    }

    public static boolean isValidPassword(String password) {

        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{5,12}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
    
}