package com.api.account.transactions.service;


import com.api.account.transactions.model.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> listAll();

    Account save(Account account);


}
