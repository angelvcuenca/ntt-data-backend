package com.api.account.transactions.service;

import com.api.account.transactions.model.dto.TransactionDto;
import com.api.account.transactions.model.entity.Transaction;

import java.util.List;

public interface ITransactionService {
    List<Transaction> listAll();


    Transaction saveTransaction(Transaction transaction);


}
