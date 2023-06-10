package com.example.demo.service;

import com.example.demo.entity.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> getAccount(Integer accountId);

    Account findByUsernameAndPassword(String username, String password);

    Account addAccount(Account account);

    Account findAccountByEmail(String email);
}
