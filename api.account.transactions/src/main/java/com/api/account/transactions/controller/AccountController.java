package com.api.account.transactions.controller;

import com.api.account.transactions.exception.BadRequestException;
import com.api.account.transactions.model.dto.AccountDto;
import com.api.account.transactions.model.entity.Account;
import com.api.account.transactions.model.entity.AccountType;
import com.api.account.transactions.model.entity.TransactionType;
import com.api.account.transactions.model.payload.MsgResponse;
import com.api.account.transactions.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    private IAccountService accountService;


    //todo: EndPoint para consultar todos los clientes
    @GetMapping
    public ResponseEntity<?> showAll() {
        List<Account> getList = accountService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .msg("No hay cuentas registradas registrados")
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
    public ResponseEntity<?> create(@RequestBody @Valid AccountDto accountDto) {

        try {
            Account account = Account.builder()
                    .numeroCuenta(accountDto.getNumeroCuenta())
                    .tipoCuenta(AccountType.valueOf(accountDto.getTipoCuenta()))
                    .saldoInicial(accountDto.getSaldoInicial())
                    .estado(accountDto.getEstado())
                    .clientId(accountDto.getClientId())
                    .build();

            account = accountService.save(account);
            return new ResponseEntity<>(MsgResponse.builder()
                    .msg("Guardado correctamente")
                    .object(AccountDto.builder()
                            .numeroCuenta(account.getNumeroCuenta())
                            .tipoCuenta(String.valueOf(AccountType.valueOf(String.valueOf(account.getTipoCuenta()))))
                            .saldoInicial(account.getSaldoInicial())
                            .estado(account.getEstado())
                            .clientId(account.getClientId())

                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw  new BadRequestException(exDt.getMessage());
        }
    }


}
