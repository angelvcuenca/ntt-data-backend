package com.api.account.transactions.model.dto;

import com.api.account.transactions.model.entity.Account;
import com.api.account.transactions.model.entity.Transaction;
import com.api.account.transactions.model.entity.TransactionType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@Builder
public class TransactionDto {
    private Long id;
    private LocalDate fecha;
    private String tipoTransaccion;
    private double valor;
    private double saldo;
    private Long accountId;

}
