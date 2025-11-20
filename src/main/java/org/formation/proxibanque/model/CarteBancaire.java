package org.formation.proxibanque.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CarteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeCarte type;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public CarteBancaire() {
    }

    public CarteBancaire(TypeCarte type, Client client) {
        this.type = type;
        this.client = client;
        this.active = true;
    }
}
