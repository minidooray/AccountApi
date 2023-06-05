package com.example.springboot.Account.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Accounts")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accounts {

    @Id
    @Column(name = "account_id")
    String accountId;

    @Column(name = "account_pwd")
    String accountPwd;

    @Column(name = "account_name")
    String accountName;

    @Column(name = "account_email")
    String accountEmail;

    @Column(name = "account_status")
    String accountStatus;

    @Column(name = "account_access_at")
    LocalDate accountAccessAt;
}
