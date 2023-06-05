package com.example.springboot.Account.entity;


import lombok.*;

import javax.persistence.*;

@Entity(name = "Accounts")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accounts {

    @Id
    String accountId;

    @Column(name = "account_pwd")
    String accountPwd;

    @Column(name = "account_name")
    String accountName;

    @Column(name = "account_email")
    String accountEmail;

    @Column(name = "account_status")
    String accountStatus;
}
