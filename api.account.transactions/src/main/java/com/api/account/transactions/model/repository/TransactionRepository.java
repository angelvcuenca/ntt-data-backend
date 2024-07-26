package com.api.account.transactions.model.repository;

import com.api.account.transactions.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountIdAndFechaBetween(Long accountId,LocalDate fechaInicio, LocalDate fechaFin);




}
