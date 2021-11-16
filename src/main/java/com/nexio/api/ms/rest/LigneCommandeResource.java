package com.nexio.api.ms.rest;

/*
 * Copyright 2021 Zeddini, as indicated by the @author tags.
 *
 * Licensed under the zeddini License; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.zeddini.com/licenses/LICENSE-2.0
 *
 * @author  Zeddini Walid
 * @version 1.0.0
 * @since   2021-11-08 
 */

import com.nexio.api.ms.domain.LigneCommande;
import com.nexio.api.ms.service.ILigneCommandeService;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * REST controller for managing {@link com.nexio.api.ms.domain.LigneCommande}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Order items management",tags="REST - LigneCommande" ,description ="LigneCommande management")
//@ApiIgnore
public class LigneCommandeResource {

    private final Logger log = LoggerFactory.getLogger(LigneCommandeResource.class);



    private final ILigneCommandeService ligneCommandeService;

    public LigneCommandeResource(ILigneCommandeService ligneCommandeService) {
        this.ligneCommandeService = ligneCommandeService;
    }

    /**
     * {@code POST  /lignes-commande} : Create a new ligneCommande.
     *
     * @param ligneCommande the ligneCommande to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ligneCommande, or with status {@code 400 (Bad Request)} if the ligneCommande has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lignes-commande")
    public ResponseEntity<LigneCommande> createCarnetCommande(@Valid @RequestBody LigneCommande ligneCommande) throws URISyntaxException {
        log.debug("REST request to save LigneCommande : {}", ligneCommande);
        LigneCommande result = ligneCommandeService.save(ligneCommande);
        return ResponseEntity.created(new URI("/api/lignes-commande/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /lignes-commande} : Updates an existing ligneCommande.
     *
     * @param ligneCommande the ligneCommande to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ligneCommande,
     * or with status {@code 400 (Bad Request)} if the ligneCommande is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ligneCommande couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lignes-commande")
    public ResponseEntity<LigneCommande> updateCarnetCommande(@Valid @RequestBody LigneCommande ligneCommande) throws URISyntaxException {
        log.debug("REST request to update LigneCommande : {}", ligneCommande);

        LigneCommande result = ligneCommandeService.save(ligneCommande);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * {@code GET  /lignes-commande} : get all the ligneCommandes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ligneCommandes in body.
     */
    @GetMapping("/lignes-commande")
    public ResponseEntity<List<LigneCommande>> getAllCarnetCommandes(Pageable pageable) {
        log.debug("REST request to get a page of LigneCommandes");
        Page<LigneCommande> page = ligneCommandeService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /lignes-commande/:id} : get the "id" ligneCommande.
     *
     * @param id the id of the ligneCommande to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ligneCommande, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lignes-commande/{id}")
    public ResponseEntity<LigneCommande> getCarnetCommande(@PathVariable Long id) {
        log.debug("REST request to get LigneCommande : {}", id);
        Optional<LigneCommande> ligneCommande = ligneCommandeService.findOne(id);
        return ResponseEntity.ok().body(ligneCommande.get());

    }

    /**
     * {@code DELETE  /lignes-commande/:id} : delete the "id" ligneCommande.
     *
     * @param id the id of the ligneCommande to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lignes-commande/{id}")
    public ResponseEntity<Void> deleteCarnetCommande(@PathVariable Long id) {
        log.debug("REST request to delete LigneCommande : {}", id);
        ligneCommandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
