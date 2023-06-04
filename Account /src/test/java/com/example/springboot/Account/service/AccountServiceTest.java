package com.example.springboot.Account.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAccount() {
    }

    @Test
    void getAccounts() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void deleteAccount() {
    }
}