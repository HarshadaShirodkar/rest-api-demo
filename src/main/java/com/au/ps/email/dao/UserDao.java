package com.au.ps.email.dao;

import com.au.ps.email.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserDao {

    public List<User> findAll();

    public List<User> findByName(String name);

    public void save(User theEmployee);

    public void deleteById(int theId);
}
