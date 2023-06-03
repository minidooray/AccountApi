package com.example.springboot.Account.repository;

import com.example.springboot.Account.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts,String> {

}
