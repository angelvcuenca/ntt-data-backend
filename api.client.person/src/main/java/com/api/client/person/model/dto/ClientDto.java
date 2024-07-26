package com.api.client.person.model.dto;

import com.api.client.person.model.entity.Client;
import com.api.client.person.model.entity.Person;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ClientDto {
    private Long clienteId;

    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;

    private String password;
    private Boolean estado;

    public Client toEntity() {
        Client client = new Client();
        client.setId(clienteId);
        client.setNombre(this.nombre);
        client.setGenero(this.genero);
        client.setEdad(this.edad);
        client.setIdentificacion(this.identificacion);
        client.setDireccion(this.direccion);
        client.setTelefono(this.telefono);
        client.setPassword(this.password);
        client.setEstado(this.estado);

        return client;
    }

    public static ClientDto fromEntity(Client client) {
        return ClientDto.builder()
                .clienteId(client.getId())
                .nombre(client.getNombre())
                .genero(client.getGenero())
                .edad(client.getEdad())
                .identificacion(client.getIdentificacion())
                .direccion(client.getDireccion())
                .telefono(client.getTelefono())
                .password(client.getPassword())
                .estado(client.getEstado())
                .build();
    }
}
