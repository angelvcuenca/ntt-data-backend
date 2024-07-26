package com.api.account.transactions.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class AccountDto implements Serializable {
    @NotEmpty(message = "Numero de Cuenta requerido!")
    private String numeroCuenta;

    private String tipoCuenta;

    @NotNull
    @Positive
    private double saldoInicial;

    @NotNull
    private Boolean estado;

    @NotNull
    private Long clientId;

}
