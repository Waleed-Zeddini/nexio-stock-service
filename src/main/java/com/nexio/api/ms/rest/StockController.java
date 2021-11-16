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
 * @since   2021-11-05 
 */

import com.nexio.api.ms.config.Constants;
import com.nexio.api.ms.domain.Produit;
import com.nexio.api.ms.service.IStockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
 * REST controller for managing {@link com.nexio.api.ms.dto.StockDTO}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Stock management",tags="Stock Microservice")
public class StockController {

    private final Logger log = LoggerFactory.getLogger(StockController.class);
 
    private final IStockService stokService;
    
    public StockController(IStockService stokService) {
        this.stokService = stokService;
    }

    /**
     * {@code POST  /stoks} : Create a new Produit.
     *
     * @param StockDTO the Stock to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Stock, or with status {@code 400 (Bad Request)} if the Stock has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stoks")
    @ApiOperation(value = "Save a new Produit in the Stock")
    public ResponseEntity<Produit> createStock(@Valid @RequestBody Produit stok) throws URISyntaxException {
        log.debug("REST request to save Stock : {}", stok);

        Produit result = stokService.save(stok);
        return ResponseEntity.created(new URI("/api/stoks" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /stoks} : Updates an existing Produit.
     *
     * @param stok the stok to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stok,
     * or with status {@code 400 (Bad Request)} if the stok is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stok couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stoks")
    @ApiOperation(value = "Update an existed Produit in the Stock")
    public ResponseEntity<Produit> updateStock(@Valid @RequestBody Produit stok) throws URISyntaxException {
        log.debug("REST request to update Produit in the Stock : {}", stok);
        Produit result = stokService.save(stok);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * {@code GET  /stoks} : get all Produits in the stok.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stoks in body.
     */
    @GetMapping("/stoks")
    @ApiOperation(value = "Get all Produits in the stoks - paginable")
    public ResponseEntity<List<Produit>> getAllProduits(Pageable pageable) {
        log.debug("REST request to get a page of Produits in the Stock");
        Page<Produit> page = stokService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }
    
    
    @GetMapping("/stoks/all")
    @ApiOperation(value = "Get all Produits in the Stock - list")
    public  List<Produit> getAllProduits() {
        log.debug("REST request to get all Produits in the Stock");
        return stokService.findAll();
    }

    /**
     * {@code GET  /stoks:id} : get Produit from the Stock by "id".
     *
     * @param id the id of the stok to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stok, or with status {@code 404 (Not Found)}.
     */
    
    @GetMapping("/stoks{id}")
    @ApiOperation(value = "Get Produit from the Stock by id")
    public ResponseEntity<Produit> getStock(@PathVariable Long id) {
        log.debug("REST request to get Produit in the Stock : {}", id);
        Optional<Produit> stok = stokService.findOne(id);
        return ResponseEntity.ok().body(stok.get());
    }
    
   
    /**
     * {@code DELETE  /stoks:id} : Produit from the Stock by "id".
     *
     * @param id the id of the stok to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */

    @ApiOperation(value = "Delete Produit from the Stock by id")
    @DeleteMapping("/stoks{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        log.debug("REST request to delete Produit from the Stock by id : {}", id);
        stokService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
