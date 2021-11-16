package com.nexio.api.ms.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nexio.api.ms.domain.Commande;
import com.nexio.api.ms.service.ICommandeService;

import io.swagger.annotations.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.nexio.api.domain.Commande}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Commande management",tags="REST - Commande",description ="Commande management")
//@ApiIgnore
public class CommandeResource {

    private final Logger log = LoggerFactory.getLogger(CommandeResource.class);

 

    private final ICommandeService commandeService;

    public CommandeResource(ICommandeService commandeService) {
        this.commandeService = commandeService;
    }

    /**
     * {@code POST  /commandes} : Create a new commande.
     *
     * @param commande the commande to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commande, or with status {@code 400 (Bad Request)} if the commande has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commandes")
    public ResponseEntity<Commande> createCommande(@Valid @RequestBody Commande commande) throws URISyntaxException {
        log.debug("REST request to save Commande : {}", commande);
   
        Commande result = commandeService.save(commande);
        return ResponseEntity.created(new URI("/api/commandes/" + result.getId()))
                .body(result);
        }

    /**
     * {@code PUT  /commandes} : Updates an existing commande.
     *
     * @param commande the commande to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commande,
     * or with status {@code 400 (Bad Request)} if the commande is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commande couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commandes")
    public ResponseEntity<Commande> updateCommande(@Valid @RequestBody Commande commande) throws URISyntaxException {
        log.debug("REST request to update Commande : {}", commande);
   
        Commande result = commandeService.save(commande);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code GET  /commandes} : get all the commandes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commandes in body.
     */
    @GetMapping("/commandes")
    public ResponseEntity<List<Commande>> getAllCommandes(Pageable pageable) {
        log.debug("REST request to get a page of Commandes");
        Page<Commande> page = commandeService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /commandes/:id} : get the "id" commande.
     *
     * @param id the id of the commande to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commande, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commandes/{id}")
    public ResponseEntity<Commande> getCommande(@PathVariable Long id) {
        log.debug("REST request to get Commande : {}", id);
        Optional<Commande> commande = commandeService.findOne(id);
        return ResponseEntity.ok().body(commande.get());
    }

    /**
     * {@code DELETE  /commandes/:id} : delete the "id" commande.
     *
     * @param id the id of the commande to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commandes/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        log.debug("REST request to delete Commande : {}", id);
        commandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
