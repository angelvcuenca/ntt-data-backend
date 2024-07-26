package com.api.account.transactions.client;

import com.api.account.transactions.model.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "api-client-person", url = "http://localhost:8090")
public interface ClienteServiceClient {
    @GetMapping("/clientes/findByClientId")
    ClientDto findByClientId(@RequestParam("clientId") Long clienteId);


}
