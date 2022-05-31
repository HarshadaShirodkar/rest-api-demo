package com.au.ps.email.controller;

import com.au.ps.email.model.User;
import com.au.ps.email.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
    }

    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        User user = new User();
        user.setFirstName("pub");
        user.setLastName("sap");
        user.setEmail("pub.sap@testdomain.com");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/users")
                        .contentType("application/json")
                        //.param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }
}
