package com.api.account.transactions.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "transacciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING) // O EnumType.ORDINAL
    @Column(name = "tipo_transaccion")
    private TransactionType tipoTransaccion;

    private double valor;
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
