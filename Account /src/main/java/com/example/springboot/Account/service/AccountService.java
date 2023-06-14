package com.example.springboot.Account.service;


import com.example.springboot.Account.Status;
import com.example.springboot.Account.domain.AccountsDTO;
import com.example.springboot.Account.entity.Accounts;
import com.example.springboot.Account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountsDTO createAccount(Accounts account){
        Accounts accounts = accountRepository.save(account);
        AccountsDTO accountsDTO = AccountsDTO.builder()
                .accountId(accounts.getAccountId())
                .accountName(accounts.getAccountName())
                .accountPwd(accounts.getAccountPwd())
                .accountEmail(accounts.getAccountEmail())
                .accountStatus(accounts.getAccountStatus())
                .accountAccessAt(accounts.getAccountAccessAt())
                .build();
        return accountsDTO;
    }

    public List<AccountsDTO> getAccounts(){
        List<Accounts> accountsList =  accountRepository.findAll();
        List<AccountsDTO> accountsDTOList = new ArrayList<>();
        for (Accounts accounts: accountsList) {
            AccountsDTO accountsDTO = AccountsDTO.builder()
                    .accountId(accounts.getAccountId())
                    .accountName(accounts.getAccountName())
                    .accountPwd(accounts.getAccountPwd())
                    .accountEmail(accounts.getAccountEmail())
                    .accountStatus(accounts.getAccountStatus())
                    .accountAccessAt(accounts.getAccountAccessAt())
                    .build();
            accountsDTOList.add(accountsDTO);
        }
        return accountsDTOList;
    }

    public AccountsDTO getAccount(String id){
        LocalDate localDate = LocalDate.now();
        Accounts accounts = accountRepository.findById(id).orElseThrow(() -> new RuntimeException(id+"존재하지 않음"));
        accounts.setAccountAccessAt(localDate);
        accountRepository.save(accounts);
        AccountsDTO accountsDTO = AccountsDTO.builder()
                .accountId(accounts.getAccountId())
                .accountName(accounts.getAccountName())
                .accountPwd(accounts.getAccountPwd())
                .accountEmail(accounts.getAccountEmail())
                .accountStatus(accounts.getAccountStatus())
                .accountAccessAt(accounts.getAccountAccessAt())
                .build();
        return accountsDTO;

    }


    public AccountsDTO getAccountbyEmail(String email){
        Accounts accounts = accountRepository.findByAccountEmail(email).orElseGet(() -> new Accounts(null,null,null,null,null,null));
        AccountsDTO accountsDTO = AccountsDTO.builder()
                .accountId(accounts.getAccountId())
                .accountName(accounts.getAccountName())
                .accountPwd(accounts.getAccountPwd())
                .accountEmail(accounts.getAccountEmail())
                .accountStatus(accounts.getAccountStatus())
                .accountAccessAt(accounts.getAccountAccessAt())
                .build();
        return accountsDTO;

    }

    public AccountsDTO updateStatus(String id, String data){
        Accounts accounts = accountRepository.findById(id).orElseThrow(() -> new RuntimeException(id+"존재하지 않음"));
        Status status = Status.valueOf(data);
        accounts.setAccountStatus(status.getName());
        accountRepository.save(accounts);

        AccountsDTO accountsDTO = AccountsDTO.builder()
                .accountId(accounts.getAccountId())
                .accountName(accounts.getAccountName())
                .accountPwd(accounts.getAccountPwd())
                .accountEmail(accounts.getAccountEmail())
                .accountStatus(accounts.getAccountStatus())
                .accountAccessAt(accounts.getAccountAccessAt())
                .build();
        return accountsDTO;
    }
    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }




}
