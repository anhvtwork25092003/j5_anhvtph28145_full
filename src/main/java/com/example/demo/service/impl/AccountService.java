package com.example.demo.service.impl;

import com.example.demo.entity.Account;
import com.example.demo.repository.IAccountRepository;
import com.example.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountService implements IAccountService {

    @Autowired
    IAccountRepository accountRepository;

    @Override
    public Optional<Account> getAccount(Integer accountId) {
        return this.accountRepository.findById(accountId);
    }

     @Override
     public Account findByUsernameAndPassword(String username, String password){
        return this.accountRepository.findByUsernameAndPassword(username, password);
     };

}
