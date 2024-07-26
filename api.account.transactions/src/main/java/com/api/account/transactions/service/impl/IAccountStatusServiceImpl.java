package com.api.account.transactions.service.impl;

import com.api.account.transactions.client.ClienteServiceClient;
import com.api.account.transactions.model.dto.AccountStatusDto;
import com.api.account.transactions.model.dto.AccountStatusReportDto;
import com.api.account.transactions.model.dto.ClientDto;
import com.api.account.transactions.model.entity.Account;
import com.api.account.transactions.model.entity.Transaction;
import com.api.account.transactions.model.entity.TransactionType;
import com.api.account.transactions.model.repository.AccountRepository;
import com.api.account.transactions.model.repository.TransactionRepository;
import com.api.account.transactions.service.IAccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class IAccountStatusServiceImpl implements IAccountStatusService {
    @Autowired
    private ClienteServiceClient clienteServiceClient;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AccountStatusDto generateReportAccountStatus(Long clientId, LocalDate startDate, LocalDate endDate) {

        ClientDto client = clienteServiceClient.findByClientId(clientId);

        List<Account> accounts = accountRepository.findByClientId(clientId);

        List<AccountStatusDto.AccountDTO> accountDTOS = accounts.stream()
                .map(account -> {
                    List<Transaction> transactions = transactionRepository.findByAccountIdAndFechaBetween(account.getId(), startDate, endDate);
                    List<AccountStatusDto.TransactionDTO> transactionDTOS = transactions.stream()
                            .map(transaction -> AccountStatusDto.TransactionDTO.builder()
                                    .fecha(transaction.getFecha())
                                    .tipoTransaccion(String.valueOf(TransactionType.valueOf(String.valueOf(transaction.getTipoTransaccion()))))
                                    .valor(transaction.getValor())
                                    .build())
                            .toList();
                    return AccountStatusDto.AccountDTO.builder()

                            .numeroCuenta(account.getNumeroCuenta())
                            .saldoInicial(account.getSaldoInicial())
                            .tipoCuenta(String.valueOf(account.getTipoCuenta()))
                            .transactions(transactionDTOS)
                            .build();
                })
                .toList();

        return AccountStatusDto.builder()
                .clienteId(clientId)
                .nombre(client.getNombre())
                .identificacion(client.getIdentificacion())
                .accounts(accountDTOS)
                .build();
    }

}
