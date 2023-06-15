package com.example.springboot.account.controller;

import com.example.springboot.account.domain.AccountsDTO;
import com.example.springboot.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountRestController.class)
@AutoConfigureMockMvc
class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;


    @Test
    void getAccounts() throws Exception{
        List<AccountsDTO> list = new ArrayList<>();
        list.add(new AccountsDTO("test1","1234","신용재","pkt123@naver.com","가입", LocalDate.now()));
        list.add(new AccountsDTO("test2","1234","휘성","ttt123@naver.com","가입", LocalDate.now()));
        list.add(new AccountsDTO("test3","1234","박효신","kkk123@naver.com","가입", LocalDate.now()));
        given(accountService.getAccounts())
                .willReturn(list);

        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[2].accountId", equalTo("test3")));
    }

    @Test
    void getAccount() throws Exception{
        AccountsDTO accountsDTO = new AccountsDTO("test1","1234","신용재","pkt123@naver.com","가입", LocalDate.now());
        given(accountService.getAccount("test1"))
                .willReturn(accountsDTO);

        mockMvc.perform(get("/account/{id}", "test1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("accountId", equalTo("test1")));
    }

    @Test
    void createAccount() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        AccountsDTO zbum = new AccountsDTO("shin", "1234", "신재욱","shin@naver.com","가입", null);
        given(accountService.createAccount(any()))
                .willReturn(zbum);

        mockMvc.perform(post("/account")
                        .content(objectMapper.writeValueAsString(zbum))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("accountId", equalTo("shin")))
                .andDo(print());
    }

    @Test
    void updateAccountStatus() throws Exception{
        AccountsDTO zbum = new AccountsDTO("shin", "1234", "신재욱","shin@naver.com","휴면", null);
        given(accountService.updateStatus(any(),any()))
                .willReturn(zbum);

        mockMvc.perform(post("/account/{id}/{status}", "shin","휴면"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("accountId", equalTo("shin")))
                .andDo(print());

    }

    @Test
    void getAccountByEmail() throws Exception{
        AccountsDTO zbum = new AccountsDTO("shin", "1234", "신재욱","shin@naver.com","휴면", null);
        given(accountService.getAccountbyEmail(any()))
                .willReturn(zbum);

        mockMvc.perform(get("/account/by/{email}", "shin@naver.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("accountId", equalTo("shin")))
                .andDo(print());
    }
//
    @Test
    void deleteAccount() throws Exception{
        doNothing().when(accountService).deleteAccount("shin");
        this.mockMvc.perform(delete("/account/{id}", "shin"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("ok")));
    }
}