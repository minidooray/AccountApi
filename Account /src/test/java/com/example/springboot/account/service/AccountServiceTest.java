package com.example.springboot.account.service;

import com.example.springboot.account.domain.AccountsDTO;
import com.example.springboot.account.entity.Accounts;
import com.example.springboot.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccount() {
        AccountsDTO accountDTO = new AccountsDTO("shin", "1234", "신재욱", "shin@naver.com", null, null);
        given(accountRepository.save(any(Accounts.class))).willReturn(new Accounts("shin", "1234", "신재욱", "shin@naver.com", null, null));

        // When
        AccountsDTO result = accountService.createAccount(accountDTO);

        // Then
        assertNotNull(result);
        assertEquals("shin", result.getAccountId());
    }

    @Test
    void getAccounts() {
        Accounts accountDTO = new Accounts("shin", "1234", "신재욱", "shin@naver.com", null, null);
        List<Accounts> accountList = new ArrayList<>();
        accountList.add(accountDTO);
        given(accountRepository.findAll()).willReturn(accountList);


        // When
        List<AccountsDTO> result = accountService.getAccounts();

        // Then
        assertNotNull(result);
        assertEquals("shin", result.get(0).getAccountId());
    }

    @Test
    void getAccount() {
        given(accountRepository.findById(any())).willReturn(Optional.of(new Accounts("shin", "1234", "신재욱", "shin@naver.com", null, null)));

        // When
        AccountsDTO result = accountService.getAccount("shin");

        // Then
        assertNotNull(result);
        assertEquals("shin", result.getAccountId());
    }

    @Test
    void getAccountbyEmail() {
        given(accountRepository.findByAccountEmail(any())).willReturn(Optional.of(new Accounts("shin", "1234", "신재욱", "shin@naver.com", null, null)));

        // When
        AccountsDTO result = accountService.getAccountbyEmail("shin@naver.com");

        // Then
        assertNotNull(result);
        assertEquals("shin", result.getAccountId());
    }
    @Test
    void deleteAccount() throws Exception{
        String accountId = "shin";
        doNothing().when(accountRepository).deleteById(accountId);

        // When
        accountService.deleteAccount(accountId);

        // Then
        verify(accountRepository).deleteById(accountId);
    }

//    @Test
//    void updateStatus() throws Exception{
//        AccountsDTO accountDTO = new AccountsDTO("shin", "1234", "신재욱", "shin@naver.com", null, null);
//        given(accountRepository.save(any(Accounts.class))).willReturn(new Accounts("shin", "1234", "신재욱", "shin@naver.com", null, null));
//
//        accountService.updateStatus(accountDTO.getAccountId(),)
//    }
}