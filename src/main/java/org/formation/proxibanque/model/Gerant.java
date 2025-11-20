package org.formation.proxibanque.model;

import jakarta.persistence.*;

@Entity
public class Gerant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @OneToOne
    @JoinColumn(name = "agence_id", unique = true)
    private Agence agence;

    public Gerant() {
    }

    public Gerant(String nom, String prenom, Agence agence) {
        this.nom = nom;
        this.prenom = prenom;
        this.agence = agence;
    }
}
