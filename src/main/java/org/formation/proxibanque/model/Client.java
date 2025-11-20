package org.formation.proxibanque.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "conseiller_id")
    private Conseiller conseiller;

    @OneToOne(mappedBy = "titulaire")
    private CompteCourant compteCourant;

    @OneToOne(mappedBy = "titulaire")
    private CompteEpargne compteEpargne;

    @OneToMany(mappedBy = "client")
    private List<CarteBancaire> cartes;

    public Client() {
    }

    public Client(String nom, String prenom, String adresse, String codePostal,
                  String ville, String telephone, Conseiller conseiller) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.conseiller = conseiller;
    }
}
