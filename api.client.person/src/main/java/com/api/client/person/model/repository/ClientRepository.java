package com.api.client.person.model.repository;

import com.api.client.person.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
