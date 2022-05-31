package com.au.ps.email.service;

import com.au.ps.email.dao.UserDao;
import com.au.ps.email.entity.UserEntity;
import com.au.ps.email.model.User;
import com.au.ps.email.util.EmailUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserDao userDao;


    public void createUser(User user) {
        String emailID = EmailUtil.createEmailID(user);

        try {
            //Creating a JSONObject object
            JSONObject jsonObject = new JSONObject();

            //Inserting key-value pairs into the json object
            jsonObject.put("FirstName", user.getFirstName());
            jsonObject.put("LastName", user.getLastName());
            jsonObject.put("Email", emailID);

            FileWriter file = new FileWriter("data/userDetails.json", true);
            file.write(jsonObject.toJSONString());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getDetails(String name) {
        List<User> userList;

        //Repository Call
        if (name != null) {
            userList = userDao.findByName(name);


        } else {
            userList = userDao.findAll();
        }

        System.out.println("userList is:" + userList);
        return userList;
    }

}