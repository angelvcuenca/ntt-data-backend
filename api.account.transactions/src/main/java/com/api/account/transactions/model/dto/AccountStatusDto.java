package com.api.account.transactions.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AccountStatusDto {
    private Long clienteId;
    private String nombre;
    private String identificacion;
    private List<AccountDTO> accounts;

    @Data
    @Builder
    public static class AccountDTO {
        private String numeroCuenta;
        private String tipoCuenta;
        private double saldoInicial;
        private Boolean estado;
        private List<TransactionDTO> transactions;
    }

    @Data
    @Builder
    public static class TransactionDTO {
        private LocalDate fecha;
        private String tipoTransaccion;
        private Double valor;
        private Double saldo;
    }
}
