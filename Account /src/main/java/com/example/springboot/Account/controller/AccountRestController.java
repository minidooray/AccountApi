package com.example.springboot.Account.controller;


import com.example.springboot.Account.domain.ResultDTO;
import com.example.springboot.Account.entity.Accounts;
import com.example.springboot.Account.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping
    public List<Accounts> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public Accounts getAccount(@PathVariable String id){
        return accountService.getAccount(id);
    }

    @PostMapping
    public Accounts createAccount(@RequestBody Accounts account){
        return accountService.createAccount(account);
    }

    @PostMapping("/{id}/{status}")
    public Accounts updateAccountStatus(@PathVariable String id, @PathVariable String status){
        return accountService.updateStatus(id,status);
    }

    @DeleteMapping("/{id}")
    public ResultDTO deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
        ResultDTO r = new ResultDTO();
        r.setResult("ok");
        return r;
    }
}
