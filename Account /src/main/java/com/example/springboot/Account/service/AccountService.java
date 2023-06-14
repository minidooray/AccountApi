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
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


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

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public AccountsDTO getAccount(String id){
        LocalDate localDate = LocalDate.now();
        Accounts accounts = accountRepository.findById(id).orElseThrow(() -> new RuntimeException(id+"존재하지 않음"));

        AccountsDTO accountsDTO = AccountsDTO.builder()
                .accountId(accounts.getAccountId())
                .accountName(accounts.getAccountName())
                .accountPwd(accounts.getAccountPwd())
                .accountEmail(accounts.getAccountEmail())
                .accountStatus(accounts.getAccountStatus())
                .accountAccessAt(localDate)
                .build();

        Accounts accounts1 = Accounts.builder()
                .accountId(accountsDTO.getAccountId())
                .accountPwd(accountsDTO.getAccountPwd())
                .accountName(accountsDTO.getAccountName())
                .accountStatus(accountsDTO.getAccountStatus())
                .accountEmail(accountsDTO.getAccountEmail())
                .accountAccessAt(accountsDTO.getAccountAccessAt())
                .build();
        accountRepository.save(accounts1);
        return accountsDTO;

    }

    @Transactional(readOnly = true)
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

        AccountsDTO accountsDTO = AccountsDTO.builder()
                .accountId(accounts.getAccountId())
                .accountName(accounts.getAccountName())
                .accountPwd(accounts.getAccountPwd())
                .accountEmail(accounts.getAccountEmail())
                .accountStatus(status.getName())
                .accountAccessAt(accounts.getAccountAccessAt())
                .build();

        Accounts accounts1 = Accounts.builder()
                .accountId(accountsDTO.getAccountId())
                .accountPwd(accountsDTO.getAccountPwd())
                .accountName(accountsDTO.getAccountName())
                .accountStatus(accountsDTO.getAccountStatus())
                .accountEmail(accountsDTO.getAccountEmail())
                .accountAccessAt(accountsDTO.getAccountAccessAt())
                .build();
        accountRepository.save(accounts);

        return accountsDTO;
    }
    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }




}
