package com.api.account.transactions.service.impl;

import com.api.account.transactions.exception.BadRequestException;
import com.api.account.transactions.exception.ResourceNotFoundException;
import com.api.account.transactions.model.dto.TransactionDto;
import com.api.account.transactions.model.entity.Account;
import com.api.account.transactions.model.entity.Transaction;
import com.api.account.transactions.model.entity.TransactionType;
import com.api.account.transactions.model.repository.AccountRepository;
import com.api.account.transactions.model.repository.TransactionRepository;
import com.api.account.transactions.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITransactionServiceImpl implements ITransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> listAll() {
        return transactionRepository.findAll();
    }


    @Override
    public Transaction saveTransaction(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta Bancaria no encontrada"));
        //throw new ResourceNotFoundException("clientes");
        double newBalance = account.getSaldoInicial();
        double amount = transaction.getValor();


        if(amount < 0){
            double amountPositive = amount * -1;
            newBalance -= amountPositive;
            if (newBalance < 0) {
                throw new ResourceNotFoundException("Saldo insuficiente");
            }
            transaction.setTipoTransaccion(TransactionType.Retiro);

        } else if (amount > 0) {
            newBalance += amount;
            transaction.setTipoTransaccion(TransactionType.Deposito);
        }
       /* if (TransactionType.valueOf(String.valueOf(transaction.getTipoTransaccion())) == TransactionType.Retiro) {
            newBalance -= amount;
            if (newBalance < 0) {
                throw new ResourceNotFoundException("Saldo insuficiente");

            }
        } else if (TransactionType.valueOf(String.valueOf(transaction.getTipoTransaccion())) == TransactionType.Deposito) {
            newBalance += amount;
        }*/

        account.setSaldoInicial(newBalance);
        accountRepository.save(account);


        transaction.setSaldo(newBalance);

        return transactionRepository.save(transaction);

         //transactionDto.;

    }


}
