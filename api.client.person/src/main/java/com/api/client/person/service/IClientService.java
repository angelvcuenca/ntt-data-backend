package com.api.client.person.service;
import com.api.client.person.model.dto.ClientDto;
import com.api.client.person.model.entity.Client;
import java.util.List;


public interface IClientService {

    List<Client> listAll();

    Client save(ClientDto client);

    Client savePartial(Long id, ClientDto client);

    Client findById(Long id);

    void delete(Client cliente);

    boolean existsById(Long id);


}
