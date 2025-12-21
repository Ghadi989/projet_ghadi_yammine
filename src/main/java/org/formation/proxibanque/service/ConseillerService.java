package org.formation.proxibanque.service;

import org.formation.proxibanque.exception.ClientNotFoundException;
import org.formation.proxibanque.model.CarteBancaire;
import org.formation.proxibanque.model.Client;
import org.formation.proxibanque.model.Compte;
import org.formation.proxibanque.repository.CarteBancaireRepository;
import org.formation.proxibanque.repository.ClientRepository;
import org.formation.proxibanque.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConseillerService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarteBancaireRepository carteBancaireRepository;

    @Autowired
    private CompteRepository compteRepository;

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

    @Transactional
    public void deleteById(Long id) {
        Client client = getClientOrThrow(id);

        List<CarteBancaire> cartes = carteBancaireRepository.findByClientId(id);
        for (CarteBancaire carte : cartes) {
            carte.setActive(false);
            carte.setClient(null);
        }
        carteBancaireRepository.saveAll(cartes);

        clientRepository.delete(client);
    }

    @Transactional
    public void virementCompte(Compte source, Compte destination, Double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        if (source.getSolde() < montant) {
            throw new IllegalArgumentException("Solde insuffisant pour effectuer le virement");
        }

        source.setSolde(source.getSolde() - montant);
        destination.setSolde(destination.getSolde() + montant);

        compteRepository.save(source);
        compteRepository.save(destination);
    }

    public Double simulerCredit(Double montant, Double tauxAnnuel, Integer dureeEnMois) {
        double tauxMensuel = tauxAnnuel / 12 / 100;
        if (tauxMensuel == 0) {
            return montant / dureeEnMois;
        }
        double mensualite = montant * (tauxMensuel / (1 - Math.pow(1 + tauxMensuel, -dureeEnMois)));
        return Math.round(mensualite * 100.0) / 100.0;
    }
}

