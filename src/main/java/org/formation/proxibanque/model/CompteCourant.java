package org.formation.proxibanque.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompteCourant extends Compte {

    private Double autorisationDecouvert = 1000.0;

    public CompteCourant() {}

    public CompteCourant(String numero, Double solde, Client titulaire) {
        super(numero, solde, titulaire);
    }
}
