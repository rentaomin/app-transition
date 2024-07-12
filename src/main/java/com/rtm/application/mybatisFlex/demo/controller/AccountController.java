package com.rtm.application.mybatisFlex.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rtm.application.mybatisFlex.demo.entity.Account;
import com.rtm.application.mybatisFlex.demo.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/account/{id}")
    public String getAccount(@PathVariable String id){
        Account account = accountService.select(id);
        return JSON.toJSONString(account);
    }

    @GetMapping("/account/mysql/{id}")
    public Account getMysqlAccount(@PathVariable String id){
        Account account = accountService.select(id);
        return account;
    }

    @GetMapping("/account/gbase/{id}")
    public String getGBaseAccount(@PathVariable String id){
        Account account = accountService.selectGBase(id);
        return JSON.toJSONString(account);
    }

    @GetMapping("/accounts")
    public List<Account> index() {
        return accountService.selectAll();
    }


    @PostMapping("/account")
    public int save(){
        Account account = new Account();
        account.setAge(22);
        account.setUserName("ff");
        account.setBirthday(new Date());
        int insert = accountService.insert(account);
        return insert;
    }
}
