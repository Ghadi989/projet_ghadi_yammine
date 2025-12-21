package org.formation.proxibanque.repository;

import org.formation.proxibanque.model.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository extends JpaRepository<Agence, String> {
}

