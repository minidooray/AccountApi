package com.example.springboot.Account.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Accounts {

    @Id
    String account_id;

    String account_pwd;

    String account_name;

    String account_email;

    String account_status;
}
