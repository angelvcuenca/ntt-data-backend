package com.api.client.person.service.impl;

import com.api.client.person.model.dto.ClientDto;
import com.api.client.person.model.entity.Client;
import com.api.client.person.model.repository.ClientRepository;
import com.api.client.person.service.IClientService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IClientServiceImpl implements IClientService {
    @Autowired
    private ClientRepository clientRepository;



    @Override
    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {

        Client client = clientDto.toEntity();

        client = clientRepository.save(client);
        return ClientDto.fromEntity(client).toEntity();
    }

    @Transactional
    @Override
    public Client savePartial(Long id, ClientDto clientDto) {



        return clientRepository.findById(id)
                .map(client -> {
                    if (clientDto.getNombre() != null) {
                        client.setNombre(clientDto.getNombre());
                    }
                    if (clientDto.getDireccion() != null) {
                        client.setDireccion(clientDto.getDireccion());
                    }
                    if (clientDto.getEdad() != null) {
                        client.setEdad(clientDto.getEdad());
                    }
                    if (clientDto.getGenero() != null) {
                        client.setGenero(clientDto.getGenero());
                    }
                    if (clientDto.getIdentificacion() != null) {
                        client.setIdentificacion(clientDto.getIdentificacion());
                    }
                    if (clientDto.getTelefono() != null) {
                        client.setTelefono(clientDto.getTelefono());
                    }
                    if (clientDto.getEstado() != null) {
                        client.setEstado(clientDto.getEstado());
                    }
                    if (clientDto.getPassword() != null) {
                        client.setPassword(clientDto.getPassword());
                    }
                    // Actualizar otros campos si es necesario
                   // Client client = clientDto.toEntity();

                    client = clientRepository.save(client);
                    return ClientDto.fromEntity(client).toEntity();
                }).orElse(null);

    }


    @Transactional(readOnly = true)
    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }


    @Data
    @Builder
    public static class ClientBuilder {
        private String nombre;
        private String genero;
        private int edad;
        private String identificacion;
        private String direccion;
        private String telefono;
        private String password;
        private Boolean estado;

        public Client build() {
            Client client = new Client();
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
    }
}
