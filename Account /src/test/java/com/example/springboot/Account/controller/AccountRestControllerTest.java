package com.example.springboot.Account.controller;

import com.example.springboot.Account.entity.Accounts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(3)
    void getAccounts() throws Exception{
        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].accountId", equalTo("admin")));
    }

    @Test
    @Order(2)
    void getAccount() throws Exception{
        mockMvc.perform(get("/account/{id}", "shin"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountName", equalTo("신재욱")));
    }

    @Test
    @Order(1)
    void createAccount() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Accounts zbum = new Accounts("shin", "1234", "신재욱","shin@naver.com",null,null);
        mockMvc.perform(post("/account")
                        .content(objectMapper.writeValueAsString(zbum))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountName", equalTo("신재욱")));
    }

    @Test
    @Order(5)
    void updateAccountStatus() {
    }

    @Test
    @Order(4)
    void getAccountByEmail() {
    }

    @Test
    @Order(6)
    void deleteAccount() throws Exception{
        this.mockMvc.perform(delete("/account/{id}", "shin"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("ok")));
    }
}