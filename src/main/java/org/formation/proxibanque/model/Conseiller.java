package org.formation.proxibanque.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Conseiller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

    @OneToMany(mappedBy = "conseiller")
    private List<Client> clients;

    public Conseiller() {
    }

    public Conseiller(String nom, String prenom, Agence agence) {
        this.nom = nom;
        this.prenom = prenom;
        this.agence = agence;
    }
}
