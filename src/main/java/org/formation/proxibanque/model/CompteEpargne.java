package org.formation.proxibanque.model;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompteEpargne extends Compte {

    private Double tauxRemuneration = 0.03;

    public CompteEpargne() {}

    public CompteEpargne(String numero, Double solde, Client titulaire) {
        super(numero, solde, titulaire);
    }
}
