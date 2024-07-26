package com.api.account.transactions.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class AccountStatusReportDto {

    private LocalDate fecha;

    private String identificacion;
    private String nombre;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private String tipoTransaccion;
    private Double valor;
    private Double saldo;






}
