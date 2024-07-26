package com.api.client.person.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor


public class Client extends Person {

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    public Client(Long id, String nombre, String genero, Integer edad, String identificacion, String direccion, String telefono,
                   String password, Boolean estado) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);

        this.password = password;
        this.estado = estado;
    }

}
