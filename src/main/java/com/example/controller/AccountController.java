package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.resource.RequestAmount;
import com.example.resource.ResponseAmount;
import com.example.service.AccountService;

@RestController
@RequestMapping("/bankTrading")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/open")
    public Account open() {
    	return this.accountService.createAccount();
    }


//    URLから口座IDを取得する
//
//    取得した口座IDをもとに対象のデータを取得する
//
//    取得したデータの預金額をResponseAmountインスタンスにセットする
//
//    ResponseAmountインスタンスを返却する
    @GetMapping("/{account_id}")
    public ResponseAmount getAmount(@RequestParam("accunt_id") Integer accuntId) {
    	Account acount = accountService.findById(accuntId).get();
    	ResponseAmount resAmount = new ResponseAmount();
    	resAmount.setAmount(acount.getAmount());
    	return resAmount;
    }



//    URLから口座IDを取得する
//
//    取得した口座IDから対象口座を取得する
//
//    対象口座の残高に対して、リクエストされた金額を加算し更新する
//
//    更新後の預金額をResponseAmountインスタンスにセットする
//
//    ResponseAmountインスタンスを返却する
    @PostMapping("/deposit/{account_id}")
    public ResponseAmount deposit(@RequestParam Integer accountId, RequestAmount requestAmount) {
    	Account account = accountService.findById(accountId).get();
    	account.setAmount(requestAmount.getAmount() + account.getAmount());
    	accountService.update(account);
    	ResponseAmount resAmount = new ResponseAmount();
    	resAmount.setAmount(account.getAmount());
    	return resAmount;
    }




//    URLから口座IDを取得する
//
//    取得した口座IDから対象口座を取得する
//
//    リクエストされた金額が残高から引き出せる金額か確認する
//
//    残高が足りている場合
//
//    残高からリクエストされた金額を減算し更新する
//
//    残高が足りていない場合
//
//    減算処理は行わない
//
//    更新後の預金額をResponseAmountインスタンスにセットする
//
//    ResponseAmountインスタンスを返却する
    @PostMapping("/withdraw/{account_id}")
    public ResponseAmount withdraw(@RequestParam Integer accountId, RequestAmount requestAmount) {
    	Account account = accountService.findById(accountId).get();
    	if(requestAmount.getAmount() <= account.getAmount()) {
    		account.setAmount(account.getAmount() - requestAmount.getAmount());
    	}
    	else {
    		account.setAmount(account.getAmount());
    	}

    	accountService.update(account);
    	ResponseAmount resAmount = new ResponseAmount();
    	resAmount.setAmount(account.getAmount());
    	return resAmount;
    }

}