package org.formation.proxibanque;

import org.formation.proxibanque.model.*;
import org.formation.proxibanque.repository.*;
import org.formation.proxibanque.service.ConseillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ProxiBanqueApplication implements CommandLineRunner {

    @Autowired
    private AgenceRepository agenceRepository;

    @Autowired
    private ConseillerRepository conseillerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ConseillerService conseillerService;

    public static void main(String[] args) {
        SpringApplication.run(ProxiBanqueApplication.class, args);
    }

    @Override
    public void run(String... args) {
        testGestionClient();
        testVirement();
    }

    private void testGestionClient() {
        System.out.println("\n========== TEST GESTION CLIENT ==========\n");

        Agence agence = new Agence("AG001", LocalDate.now());
        agenceRepository.save(agence);

        Conseiller conseiller = new Conseiller("Dupont", "Jean", agence);
        conseillerRepository.save(conseiller);

        System.out.println("1. CRÉATION d'un client");
        Client client = new Client("Martin", "Pierre", "123 Rue de Paris", "75001", "Paris", "0612345678", conseiller);
        Client savedClient = conseillerService.createClient(client);
        System.out.println("   Client créé: " + savedClient.getNom() + " " + savedClient.getPrenom());
        System.out.println("   ID: " + savedClient.getId());

        System.out.println("\n2. LECTURE des informations du client");
        Client readClient = conseillerService.getClientOrThrow(savedClient.getId());
        System.out.println("   Nom: " + readClient.getNom());
        System.out.println("   Prénom: " + readClient.getPrenom());
        System.out.println("   Adresse: " + readClient.getAdresse());
        System.out.println("   Code Postal: " + readClient.getCodePostal());
        System.out.println("   Ville: " + readClient.getVille());
        System.out.println("   Téléphone: " + readClient.getTelephone());
        System.out.println("   Conseiller: " + readClient.getConseiller().getNom());

        System.out.println("\n3. MODIFICATION du client");
        readClient.setAdresse("456 Avenue des Champs");
        readClient.setVille("Lyon");
        readClient.setCodePostal("69001");
        readClient.setTelephone("0698765432");
        clientRepository.save(readClient);

        Client updatedClient = conseillerService.getClientOrThrow(savedClient.getId());
        System.out.println("   Nouvelle adresse: " + updatedClient.getAdresse());
        System.out.println("   Nouvelle ville: " + updatedClient.getVille());
        System.out.println("   Nouveau code postal: " + updatedClient.getCodePostal());
        System.out.println("   Nouveau téléphone: " + updatedClient.getTelephone());

        System.out.println("\n4. SUPPRESSION du client");
        Long clientId = savedClient.getId();
        conseillerService.deleteById(clientId);
        System.out.println("   Client ID " + clientId + " supprimé");

        boolean exists = clientRepository.existsById(clientId);
        System.out.println("   Client existe encore? " + exists);

        System.out.println("\n========== FIN DES TESTS ==========\n");
    }

    private void testVirement() {
        System.out.println("\n========== TEST VIREMENT COMPTE À COMPTE ==========\n");

        Agence agence = agenceRepository.findById("AG001").orElse(agenceRepository.save(new Agence("AG002", LocalDate.now())));

        Conseiller conseiller = new Conseiller("Bernard", "Marie", agence);
        conseillerRepository.save(conseiller);

        Client client1 = new Client("Durand", "Alice", "10 Rue A", "75002", "Paris", "0611111111", conseiller);
        Client client2 = new Client("Leroy", "Bob", "20 Rue B", "75003", "Paris", "0622222222", conseiller);
        clientRepository.save(client1);
        clientRepository.save(client2);

        CompteCourant compte1 = new CompteCourant("CC001", 5000.0, client1);
        CompteCourant compte2 = new CompteCourant("CC002", 1000.0, client2);
        compteRepository.save(compte1);
        compteRepository.save(compte2);

        System.out.println("Avant virement:");
        System.out.println("   - Compte " + compte1.getNumero() + " (Alice): " + compte1.getSolde() + " €");
        System.out.println("   - Compte " + compte2.getNumero() + " (Bob): " + compte2.getSolde() + " €");

        conseillerService.virementCompte(compte1, compte2, 1500.0);

        CompteCourant updatedCompte1 = (CompteCourant) compteRepository.findById(compte1.getId()).orElseThrow();
        CompteCourant updatedCompte2 = (CompteCourant) compteRepository.findById(compte2.getId()).orElseThrow();

        System.out.println("\nAprès virement de 1500 €:");
        System.out.println("   - Compte " + updatedCompte1.getNumero() + " (Alice): " + updatedCompte1.getSolde() + " €");
        System.out.println("   - Compte " + updatedCompte2.getNumero() + " (Bob): " + updatedCompte2.getSolde() + " €");

        System.out.println("\n========== FIN DES TESTS ==========\n");
    }
}
