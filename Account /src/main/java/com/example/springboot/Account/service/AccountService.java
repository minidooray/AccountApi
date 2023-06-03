package com.example.springboot.Account.service;


import com.example.springboot.Account.entity.Accounts;
import com.example.springboot.Account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Accounts createAccount(Accounts account){
        return accountRepository.save(account);
    }

    public List<Accounts> getAccounts(){
        return  accountRepository.findAll();
    }

    public Accounts getAccount(String id){
        return  accountRepository.findById(id).orElseThrow(() -> new RuntimeException(id+"존재하지 않음"));
    }

    public Accounts updateStatus(String id, String status){
        Accounts account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException(id+"존재하지 않음"));
        account.setAccount_status(status);
        return  accountRepository.save(account);
    }
    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }


}
