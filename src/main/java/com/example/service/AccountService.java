package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount() {
    	Account account = new Account();
    	account.setAmount(0);
    	return accountRepository.save(account);

    }


    public Optional<Account> findById(Integer id) {
    	return accountRepository.findById(id);
    }

    public Account update(Account account) {
    	return accountRepository.save(account);
    }
}


//Accountインスタンスを生成する
//
//初期残高は 0 を設定する
//
//Accountインスタンスの内容をデータベースに登録し、登録結果を返却する