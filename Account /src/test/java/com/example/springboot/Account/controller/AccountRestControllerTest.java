package com.example.springboot.Account.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAccounts() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void updateAccountStatus() {
    }

    @Test
    void getAccountByEmail() {
    }

    @Test
    void deleteAccount() {
    }
}