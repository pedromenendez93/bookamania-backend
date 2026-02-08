package com.bookamania.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class GreetingControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
    }

    @Test
    @DisplayName("Tests the behaviour of the greeting endpoint when is called without passing a parameter")
    void givenAGetRequestWithNoName_whenGreeting_thenReturn200StatusOkAndTheDefaultGreeting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/greeting"))
            .andExpect(status().isOk())
            .andExpect(content().json("{'content': 'Hello, World'}"));
    }

    @Test
    @DisplayName("Tests the behaviour of the greeting endpoint when is called passing it a parameter")
    void givenAGetRequestWithName_whenGreeting_thenReturn200StatusOkAndTheCustomGreeting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/greeting").param("name", "John"))
            .andExpect(status().isOk())
            .andExpect(content().json("{'content' :  'Hello, John'}"));
    }
}
