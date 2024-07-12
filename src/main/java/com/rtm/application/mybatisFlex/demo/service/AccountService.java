package com.rtm.application.mybatisFlex.demo.service;

import com.rtm.application.mybatisFlex.demo.entity.Account;
import java.util.List;

public interface AccountService {

    Account select(String id);

    Account selectGBase(String id);

    List<Account> selectAll();

    int insert(Account account);
}
