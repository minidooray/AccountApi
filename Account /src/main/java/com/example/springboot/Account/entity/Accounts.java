package com.example.springboot.Account.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Accounts")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accounts {

    @Id
    String account_id;

    String account_pwd;

    String account_name;

    String account_email;

    String account_status;
}
