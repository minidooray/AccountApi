package com.example.springboot.Account.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@Getter
public class AccountsDTO {
    String accountId;
    String accountPwd;

    String accountName;
    String accountEmail;
    String accountStatus;

    LocalDate accountAccessAt;
}
