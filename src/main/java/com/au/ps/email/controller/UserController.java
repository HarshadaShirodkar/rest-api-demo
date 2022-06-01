package com.au.ps.email.controller;

import com.au.ps.email.model.User;
import com.au.ps.email.service.UserService;
import com.au.ps.email.util.EmailUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/users")
@Log4j2
public class UserController {

    @Autowired
    public UserService userService;

    static Logger logger = Logger.getLogger(UserController.class.getName());

    @PostMapping()
    ResponseEntity<Object> createUser(@RequestBody User user) {
        String correlationId = UUID.randomUUID().toString();

        logger.log(Level.INFO, "start createUser request with correlationId={}", correlationId);

        if (EmailUtil.emailExists(user)) {
            return new ResponseEntity<>("User is already created", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(user);

        logger.log(Level.INFO, "End createUser request with correlationId={}", correlationId);

        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/search")
    ResponseEntity<Object> getEmails(@RequestParam(value = "name", required = false) String name) {
        String correlationId = UUID.randomUUID().toString();
        logger.log(Level.INFO, "start getEmails() request with correlationId={}", correlationId);

        List<User> userList = userService.getDetails(name);

        if (userList == null || userList.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        logger.log(Level.INFO, "start getEmails() request with correlationId {}", correlationId);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        //service.delete(id);
    }

}