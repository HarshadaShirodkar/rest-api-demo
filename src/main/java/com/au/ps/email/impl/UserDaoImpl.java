package com.au.ps.email.impl;

import com.au.ps.email.dao.UserDao;
import com.au.ps.email.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() {
        List<User> userList;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //userList = userRepository.getAllEmails();
            userList = Arrays.asList(objectMapper.readValue(new File("data/test.json"), User[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public List<User> findByName(String name) {
        List<User> email;
        try {
            //userList = userRepository.getEmailByName();
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> userList = Arrays.asList(objectMapper.readValue(new File("data/test.json"), User[].class));
            System.out.println("userList is:" + userList);

            email = userList.stream()
                    .filter(it -> name.equalsIgnoreCase(it.getFirstName()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

    @Override
    public void save(User theEmployee) {

    }

    @Override
    public void deleteById(int theId) {

    }
}
