package org.formation.proxibanque.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Agence {

    @Id
    @Column(length = 5, unique = true, nullable = false)
    private String id;

    private LocalDate dateCreation;

    @OneToOne(mappedBy = "agence")
    private Gerant gerant;

    @OneToMany(mappedBy = "agence")
    private List<Conseiller> conseillers;

    public Agence() {
    }

    public Agence(String id, LocalDate dateCreation) {
        this.id = id;
        this.dateCreation = dateCreation;
    }
}
