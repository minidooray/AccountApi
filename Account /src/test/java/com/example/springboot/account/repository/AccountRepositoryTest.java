package com.example.springboot.account.repository;

import com.example.springboot.account.Status;
import com.example.springboot.account.entity.Accounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountRepository accountRepository;

    private Accounts accounts1;
    private Accounts accounts2;
    private Accounts accounts3;

    @BeforeEach
    void init(){
        accounts1 = new Accounts("shin", "1234", "신재욱","shin@naver.com","가입", null);
        accounts2 = new Accounts("park", "1234", "박정우","park@naver.com","가입", null);
        accounts3 = new Accounts("jin", "1234", "진진바리","jin@naver.com","가입", null);
        testEntityManager.persistAndFlush(accounts1);
        testEntityManager.persistAndFlush(accounts2);
        testEntityManager.persistAndFlush(accounts3);
    }

    @Test
    void save() {
        // given
        Accounts account = new Accounts("violet",
                "1234",
                "니키",
                "tkdntlf@naver.com",
                Status.Subscription.getName(),
                null
        );
        testEntityManager.persist(account);

        Accounts accounts = accountRepository.findByAccountEmail("tkdntlf@naver.com").get();

        // when
//        accountRepository.save(account);
//        testEntityManager.flush();

        // then
//        Accounts result = testEntityManager.find(Accounts.class, account.getAccountId());

        assertThat(accounts).isEqualTo(account);
    }

    @Test
    void findbyemail(){
        Optional<Accounts> foundAccount = accountRepository.findByAccountEmail(accounts1.getAccountEmail());
        assertThat(foundAccount.isPresent()).isTrue();
        assertThat(foundAccount.get().getAccountId()).isEqualTo(accounts1.getAccountId());
    }

    @Test
    void gets(){
    List<Accounts> AccountsList = accountRepository.findAll();
        assertThat(AccountsList).isNotNull();

    }

    @Test
    void get(){
        Optional<Accounts> foundAccount = accountRepository.findById(accounts1.getAccountId());
        assertThat(foundAccount.isPresent()).isTrue();
        assertThat(foundAccount.get().getAccountId()).isEqualTo(accounts1.getAccountId());
    }

    @Test
    void delete(){
        accountRepository.deleteById(accounts1.getAccountId());
        accountRepository.deleteById(accounts2.getAccountId());
        accountRepository.deleteById(accounts3.getAccountId());
        testEntityManager.flush();
        Optional<Accounts> deletedAccount1 = accountRepository.findById(accounts1.getAccountId());
        Optional<Accounts> deletedAccount2 = accountRepository.findById(accounts2.getAccountId());
        Optional<Accounts> deletedAccount3 = accountRepository.findById(accounts3.getAccountId());
        assertThat(deletedAccount1.isPresent()).isFalse();
        assertThat(deletedAccount2.isPresent()).isFalse();
        assertThat(deletedAccount3.isPresent()).isFalse();
    }



}