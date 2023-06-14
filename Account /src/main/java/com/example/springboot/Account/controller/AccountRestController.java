package com.example.springboot.Account.controller;


import com.example.springboot.Account.domain.AccountsDTO;
import com.example.springboot.Account.domain.ResultDTO;
import com.example.springboot.Account.entity.Accounts;
import com.example.springboot.Account.service.AccountService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AccountsDTO>>  getAccounts(){

        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountsDTO>  getAccount(@PathVariable String id){
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PostMapping
    public ResponseEntity<AccountsDTO> createAccount(@RequestBody Accounts account){
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PostMapping("/{id}/{status}")
    public ResponseEntity<AccountsDTO>  updateAccountStatus(@PathVariable String id, @PathVariable String status){
        return ResponseEntity.ok(accountService.updateStatus(id,status));
    }

    @GetMapping("/by/{email}")
    public ResponseEntity<AccountsDTO> getAccountByEmail(@PathVariable String email){
        return ResponseEntity.ok(accountService.getAccountbyEmail(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultDTO> deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
        ResultDTO r = new ResultDTO();
        r.setResult("ok");
        return ResponseEntity.ok(r);
    }

}
