package org.formation.proxibanque.service;

import org.formation.proxibanque.exception.ClientNotFoundException;
import org.formation.proxibanque.model.Client;
import org.formation.proxibanque.model.Compte;
import org.formation.proxibanque.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConseillerService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClientOrThrow(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client newClient) {
        getClientOrThrow(id);
        Client updatedClient = new Client(newClient.getNom(),
                newClient.getPrenom(),
                newClient.getAdresse(),
                newClient.getCodePostal(),
                newClient.getVille(),
                newClient.getTelephone(),
                newClient.getConseiller());

        return clientRepository.save(updatedClient);
    }

    public void deleteById(Long id) {
        getClientOrThrow(id);
        clientRepository.deleteById(id);
    }

    public void virementCompte(Compte sender, Compte receiver, Double amount) {
        sender.setSolde(sender.getSolde() - amount);
        receiver.setSolde(receiver.getSolde() + amount);
    }
}

