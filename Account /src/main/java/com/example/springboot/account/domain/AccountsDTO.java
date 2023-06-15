package com.example.springboot.account.domain;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDTO {
    String accountId;
    String accountPwd;

    String accountName;
    String accountEmail;
    String accountStatus;

    LocalDate accountAccessAt;
}
