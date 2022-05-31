package com.au.ps.email.util;

import com.au.ps.email.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {

    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    public static String createEmailID(User user) {
        String generatedId = user.getFirstName() + "." + user.getLastName() + "@testdomain.com";
        return generatedId;
    }


    public static boolean emailExists(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        boolean isEmailExists = false;
        try {
            List<User> userList = Arrays.asList(objectMapper.readValue(new File("data/test.json"), User[].class));
            System.out.println("userList :" + userList);

            for (User userDetails : userList) {
                if (userDetails.getEmail().equalsIgnoreCase(user.getEmail()))
                    isEmailExists = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("isEmailExists :" + isEmailExists);
        return isEmailExists;
    }

}
