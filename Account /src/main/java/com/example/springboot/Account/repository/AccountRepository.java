package com.example.springboot.Account.repository;

import com.example.springboot.Account.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface AccountRepository extends JpaRepository<Accounts,String> {

}
