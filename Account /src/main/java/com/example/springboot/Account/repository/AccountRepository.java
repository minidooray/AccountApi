package com.example.springboot.Account.repository;

import com.example.springboot.Account.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Accounts,String> {


    Optional<Accounts> findByAccountEmail(String email);
}
