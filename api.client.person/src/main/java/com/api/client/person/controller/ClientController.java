package com.api.client.person.controller;

import com.api.client.person.model.dto.ClientDto;
import com.api.client.person.model.entity.Client;
import com.api.client.person.model.payload.MsgResponse;
import com.api.client.person.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    private IClientService clientService;



    //todo: EndPoint para consultar todos los clientes
    @GetMapping
    public ResponseEntity<?> showAll() {
        List<Client> getList = clientService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .msg("No hay clientes registrados")
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
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto) {
        Client client = null;
        try {


            client = clientService.save(clientDto);
            return new ResponseEntity<>(MsgResponse.builder()
                    .msg("Guardado correctamente")
                    .object(ClientDto.builder()
                            .clienteId(client.getId())
                            .nombre(client.getNombre() )
                            .genero(client.getGenero())
                            .edad(client.getEdad())
                            .identificacion(client.getIdentificacion())
                            .direccion(client.getDireccion())
                            .telefono(client.getTelefono())
                            .password(client.getPassword())
                            .estado(client.getEstado())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .msg(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    //todo: EndPoint para editar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ClientDto clientDto, @PathVariable Long id) {
        Client client = null;
        try {
            if (clientService.existsById(id)) {
                clientDto.setClienteId(id);
                client = clientService.save(clientDto);
                return new ResponseEntity<>(MsgResponse.builder()
                        .msg("Registro editado correctamente")
                        .object(ClientDto.builder()
                                .clienteId(client.getId())
                                .nombre(client.getNombre() )
                                .genero(client.getGenero())
                                .edad(client.getEdad())
                                .identificacion(client.getIdentificacion())
                                .direccion(client.getDireccion())
                                .telefono(client.getTelefono())
                                .password(client.getPassword())
                                .estado(client.getEstado())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MsgResponse.builder()
                                .msg("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .msg(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    // Endpoint PATCH para actualizar un cliente existente
    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@RequestBody ClientDto clientDto, @PathVariable Long id) {

        Client client = null;
        try {
            if (clientService.existsById(id)) {
                clientDto.setClienteId(id);
                client = clientService.savePartial(id, clientDto);
                return new ResponseEntity<>(MsgResponse.builder()
                        .msg("Registro editado correctamente")
                        .object(ClientDto.builder()
                                .clienteId(client.getId())
                                .nombre(client.getNombre() )
                                .genero(client.getGenero())
                                .edad(client.getEdad())
                                .identificacion(client.getIdentificacion())
                                .direccion(client.getDireccion())
                                .telefono(client.getTelefono())
                                .password(client.getPassword())
                                .estado(client.getEstado())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MsgResponse.builder()
                                .msg("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .msg(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }


    }


    //todo: EndPoint para eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Client client = clientService.findById(id);
            clientService.delete(client);
            return new ResponseEntity<>(client, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .msg(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    //todo: EndPoint para buscar  un cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Client client = clientService.findById(id);

        if (client == null) {
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .msg("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MsgResponse.builder()
                        .msg("Registro encontrado")
                        .object(ClientDto.builder()
                                .clienteId(client.getId())
                                .nombre(client.getNombre() )
                                .genero(client.getGenero())
                                .edad(client.getEdad())
                                .identificacion(client.getIdentificacion())
                                .direccion(client.getDireccion())
                                .telefono(client.getTelefono())
                                .password(client.getPassword())
                                .estado(client.getEstado())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("/findByClientId")
    public ResponseEntity<?> showByIdOk(@RequestParam("clientId") Long clientId) {

        return ResponseEntity.ok(clientService.findById(clientId));


    }



}
