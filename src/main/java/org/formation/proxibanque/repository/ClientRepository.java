package org.formation.proxibanque.repository;

import org.formation.proxibanque.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(long id);
}
