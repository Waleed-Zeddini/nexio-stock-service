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

import com.nexio.api.ms.domain.Produit;
import com.nexio.api.ms.service.IProduitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * REST controller for managing {@link com.nexio.api.ms.domain.Produit}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Produit",tags="Produit")
//@ApiIgnore
public class ProduitResource {

    private final Logger log = LoggerFactory.getLogger(ProduitResource.class);

    private final IProduitService produitService;

    public ProduitResource(IProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * {@code POST  /produits} : Create a new produit.
     *
     * @param produit the produit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new produit, or with status {@code 400 (Bad Request)} if the produit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/produits")
    public ResponseEntity<Produit> createProduit(@Valid @RequestBody Produit produit) throws URISyntaxException {
        log.debug("REST request to save Produit : {}", produit);
 
        Produit result = produitService.save(produit);
        return ResponseEntity.created(new URI("/api/produits/" + result.getId()))
                .body(result);
        }
    
    @PostMapping("/produits/save/many")
    public List<Produit> createManyProduit(List<Produit> produit) throws URISyntaxException {
        log.debug("REST request to save many Produit : {}");
 
        produit = produitService.saveMany(produit);
        
        return produit;
        }

    @GetMapping("/produits/all")
    @ApiOperation(value = "Get all Produits in the Stock - list")
    public  List<Produit> getAllProduits() {
        log.debug("REST request to get all Produits in the Stock");
        return produitService.findAll();
    }
    /**
     * {@code PUT  /produits} : Updates an existing produit.
     *
     * @param produit the produit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produit,
     * or with status {@code 400 (Bad Request)} if the produit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the produit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/produits")
    public ResponseEntity<Produit> updateProduit(@Valid @RequestBody Produit produit) throws URISyntaxException {
        log.debug("REST request to update Produit : {}", produit);
 
        Produit result = produitService.save(produit);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code GET  /produits} : get all the produits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of produits in body.
     */
    @GetMapping("/produits")
    public ResponseEntity<List<Produit>> getAllProduits(Pageable pageable) {
        log.debug("REST request to get a page of Produits");
        Page<Produit> page = produitService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());

    }

    /**
     * {@code GET  /produits/:id} : get the "id" produit.
     *
     * @param id the id of the produit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the produit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/produits/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
        log.debug("REST request to get Produit : {}", id);
        Optional<Produit> produit = produitService.findOne(id);
        return ResponseEntity.ok().body(produit.get());
    }

    /**
     * {@code DELETE  /produits/:id} : delete the "id" produit.
     *
     * @param id the id of the produit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        log.debug("REST request to delete Produit : {}", id);
        produitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
