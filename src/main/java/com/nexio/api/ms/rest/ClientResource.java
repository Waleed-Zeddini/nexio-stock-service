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

import com.nexio.api.ms.domain.Client;
import com.nexio.api.ms.service.IClientService;

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
 * REST controller for managing {@link com.nexio.api.ms.domain.Client}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Client management",tags="REST - Client",description ="Client management")
//@ApiIgnore
public class ClientResource {

    private final Logger log = LoggerFactory.getLogger(ClientResource.class);



    private final IClientService clientService;

    public ClientResource(IClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * {@code POST  /clients} : Create a new client.
     *
     * @param client the client to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new client, or with status {@code 400 (Bad Request)} if the client has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) throws URISyntaxException {
        log.debug("REST request to save Client : {}", client);
        Client result = clientService.save(client);
        return ResponseEntity.created(new URI("/api/clients/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /clients} : Updates an existing client.
     *
     * @param client the client to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated client,
     * or with status {@code 400 (Bad Request)} if the client is not valid,
     * or with status {@code 500 (Internal Server Error)} if the client couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/clients")
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client client) throws URISyntaxException {
        log.debug("REST request to update Client : {}", client);
 
        Client result = clientService.save(client);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * {@code GET  /clients} : get all the clients.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clients in body.
     */
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients(Pageable pageable) {
        log.debug("REST request to get a page of Clients");
        Page<Client> page = clientService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /clients/:id} : get the "id" client.
     *
     * @param id the id of the client to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the client, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        log.debug("REST request to get Client : {}", id);
        Optional<Client> client = clientService.findOne(id);
        return ResponseEntity.ok().body(client.get());

    }

    /**
     * {@code DELETE  /clients/:id} : delete the "id" client.
     *
     * @param id the id of the client to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.debug("REST request to delete Client : {}", id);
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
