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

import com.nexio.api.ms.domain.Categorie;
import com.nexio.api.ms.service.ICategorieService;

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
 * REST controller for managing {@link com.nexio.domain.Categorie}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Categorie",tags="Catégorie")
//@ApiIgnore
public class CategorieResource {

    private final Logger log = LoggerFactory.getLogger(CategorieResource.class);

 

    private final ICategorieService categorieService;

    public CategorieResource(ICategorieService categorieService) {
        this.categorieService = categorieService;
    }

    /**
     * {@code POST  /categories} : Create a new categorie.
     *
     * @param categorie the categorie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorie, or with status {@code 400 (Bad Request)} if the categorie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categories")
    public ResponseEntity<Categorie> createCategorie(@Valid @RequestBody Categorie categorie) throws URISyntaxException {
        log.debug("REST request to save Categorie : {}", categorie);
   
        Categorie result = categorieService.save(categorie);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /categories} : Updates an existing categorie.
     *
     * @param categorie the categorie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorie,
     * or with status {@code 400 (Bad Request)} if the categorie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categories")
    public ResponseEntity<Categorie> updateCategorie(@Valid @RequestBody Categorie categorie) throws URISyntaxException {
        log.debug("REST request to update Categorie : {}", categorie);

        Categorie result = categorieService.save(categorie);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code GET  /categories} : get all the categories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categories in body.
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Categorie>> getAllCategories(Pageable pageable) {
        log.debug("REST request to get a page of Categories");
        Page<Categorie> page = categorieService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /categories/:id} : get the "id" categorie.
     *
     * @param id the id of the categorie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable Long id) {
        log.debug("REST request to get Categorie : {}", id);
        Optional<Categorie> categorie = categorieService.findOne(id);
        return ResponseEntity.ok().body(categorie.get());
    }

    /**
     * {@code DELETE  /categories/:id} : delete the "id" categorie.
     *
     * @param id the id of the categorie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        log.debug("REST request to delete Categorie : {}", id);
        categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
