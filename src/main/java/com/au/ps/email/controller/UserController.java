package com.au.ps.email.controller;

import com.au.ps.email.model.User;
import com.au.ps.email.service.UserService;
import com.au.ps.email.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping()
    ResponseEntity<Object> createUser(@RequestBody User user) {
        if(EmailUtil.emailExists(user)){
            return new ResponseEntity<>("User is already created", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/search")
    ResponseEntity<Object> getEmails(@RequestParam(value = "name", required = false) String name) {
        List<User> userList = userService.getDetails(name);

        if (userList == null || userList.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        //service.delete(id);
    }

}