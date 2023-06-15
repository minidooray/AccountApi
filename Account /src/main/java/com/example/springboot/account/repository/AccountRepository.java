package com.example.springboot.account.repository;

import com.example.springboot.account.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
public interface AccountRepository extends JpaRepository<Accounts,String> {
    Optional<Accounts> findByAccountEmail(String email);
}
