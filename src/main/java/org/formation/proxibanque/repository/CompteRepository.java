package org.formation.proxibanque.repository;

import org.formation.proxibanque.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}

