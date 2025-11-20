package org.formation.proxibanque.request;

import org.formation.proxibanque.model.TypeCompte;

public class VirementRequest {

    public Long senderId;
    public Long receiverId;

    public TypeCompte senderCompteType;
    public TypeCompte receiverCompteType;

    public Double amount;
}
