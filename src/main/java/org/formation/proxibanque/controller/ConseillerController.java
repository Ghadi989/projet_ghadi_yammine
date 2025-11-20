package org.formation.proxibanque.controller;

import org.formation.proxibanque.model.Client;
import org.formation.proxibanque.model.Compte;
import org.formation.proxibanque.request.VirementRequest;
import org.formation.proxibanque.service.ConseillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConseillerController {

    @Autowired
    private ConseillerService conseillerService;

    @PostMapping("clients")
    public Client createClient(@RequestBody Client client) {
        return conseillerService.createClient(client);
    }

    @PutMapping("clients/{id}")
    public Client updateClient(@PathVariable long id, @RequestBody Client client) {
        return conseillerService.updateClient(id, client);
    }

    @GetMapping("clients/{id}")
    public Client getClient(@PathVariable long id) {
        return conseillerService.getClientOrThrow(id);
    }

    @DeleteMapping("clients/{id}")
    public void deleteClient(@PathVariable long id) {
        conseillerService.deleteById(id);
    }

    @PostMapping("clients/virement/")
    public void virementCompte(@RequestBody VirementRequest virementRequest) {

    }
}

