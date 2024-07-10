package com.rtm.application.mybatisFlex.controller;

import com.alibaba.fastjson.JSON;
import com.rtm.application.mybatisFlex.entity.AccountInfo;
import com.rtm.application.mybatisFlex.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/info/{id}")
    public String getAccount(@PathVariable String id){
        AccountInfo accountInfo = accountService.select(id);
        return JSON.toJSONString(accountInfo);
    }

    @GetMapping("/info/mysql/{id}")
    public String getMysqlAccount(@PathVariable String id){
        AccountInfo accountInfo = accountService.select(id);
        return JSON.toJSONString(accountInfo);
    }

    @GetMapping("/info/gbase/{id}")
    public String getGBaseAccount(@PathVariable String id){
        AccountInfo accountInfo = accountService.selectGBase(id);
        return JSON.toJSONString(accountInfo);
    }

    @GetMapping("/list")
    public List<AccountInfo> index() {
        return accountService.selectAll();
    }
}
