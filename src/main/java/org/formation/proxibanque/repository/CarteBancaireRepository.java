package org.formation.proxibanque.repository;

import org.formation.proxibanque.model.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Long> {
    List<CarteBancaire> findByClientId(Long clientId);
}

