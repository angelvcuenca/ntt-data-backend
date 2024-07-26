package com.api.account.transactions.service.impl;

import com.api.account.transactions.model.dto.AccountDto;
import com.api.account.transactions.model.entity.Account;
import com.api.account.transactions.model.repository.AccountRepository;
import com.api.account.transactions.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> listAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }


}
