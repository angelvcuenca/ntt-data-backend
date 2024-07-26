package com.api.account.transactions.controller;

import com.api.account.transactions.exception.BadRequestException;
import com.api.account.transactions.model.dto.AccountDto;
import com.api.account.transactions.model.dto.TransactionDto;
import com.api.account.transactions.model.entity.Account;
import com.api.account.transactions.model.entity.AccountType;
import com.api.account.transactions.model.entity.Transaction;
import com.api.account.transactions.model.entity.TransactionType;
import com.api.account.transactions.model.payload.MsgResponse;
import com.api.account.transactions.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class TransactionController    {
    @Autowired
    private ITransactionService transactionService;

    //todo: EndPoint para consultar todos los clientes
    @GetMapping
    public ResponseEntity<?> showAll() {
        List<Transaction> getList = transactionService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .msg("No hay movimientos registradas")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MsgResponse.builder()
                        .msg("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);
    }

    //todo: EndPoint para crear un cliente
    @PostMapping
    public ResponseEntity<?> create( @RequestBody TransactionDto transactionDto) {

        try {
            //Account account = new Account();
            //account.setId(transactionDto.getAccountId());

            Account account = Account.builder()
                    .id(transactionDto.getAccountId())
                    .build();

            Transaction transaction1 = Transaction.builder()
                    .fecha(transactionDto.getFecha())
                    .valor(transactionDto.getValor())
                    .account(account)
                    .build();
            transaction1 = transactionService.saveTransaction(transaction1);


           // transaction = transactionService.saveTransaction(transactionDto);
            return new ResponseEntity<>(MsgResponse.builder()
                    .msg("Guardado correctamente")
                    .object(TransactionDto.builder()
                            .id(transaction1.getId())
                            .fecha(transaction1.getFecha())
                            .saldo(transaction1.getSaldo())
                            .tipoTransaccion(String.valueOf(TransactionType.valueOf(String.valueOf(transaction1.getTipoTransaccion()))))
                            .valor(transaction1.getValor())
                            .accountId(account.getId())
                            //.account(account)
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw  new BadRequestException(exDt.getMessage());
        }
    }
}
