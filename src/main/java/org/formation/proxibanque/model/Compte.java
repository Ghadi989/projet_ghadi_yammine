package org.formation.proxibanque.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero;

    private Double solde = 0.0;

    private LocalDate dateOuverture = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client titulaire;

    public Compte() {
    }

    public Compte(String numero, Double solde, Client titulaire) {
        this.numero = numero;
        this.solde = solde;
        this.titulaire = titulaire;
        this.dateOuverture = LocalDate.now();
    }
}
