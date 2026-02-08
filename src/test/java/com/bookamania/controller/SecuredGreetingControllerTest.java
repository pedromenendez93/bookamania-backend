package com.bookamania.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class SecuredGreetingControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply(springSecurity())
            .build();
    }

    @Test
    @DisplayName("Tests the behaviour of the secured greeting endpoint when called without authentication")
    void givenAGetRequestWithoutAuth_whenSecuredGreeting_thenReturn401Unauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/secured/greeting"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    @DisplayName("Tests the behaviour of the secured greeting endpoint when called with authentication")
    void givenAGetRequestWithAuth_whenSecuredGreeting_thenReturn200StatusOkAndSecuredGreeting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/secured/greeting"))
            .andExpect(status().isOk())
            .andExpect(content().json("{'content': 'Hello secured World'}"));
    }
}