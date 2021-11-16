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
import com.nexio.api.ms.domain.Commande;
import com.nexio.api.ms.service.IClientService;
import com.nexio.api.ms.service.ICommandeDtoService;
import com.nexio.api.ms.service.CommandeDtoServiceImpl;

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
 * REST controller for managing {@link com.nexio.api.ms.dto.OrderDTO}.
 */
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "*")
////@CrossOrigin(origins = "http://localhost:4200")
//@Api(value="Order management",tags="Order Microservice")
public class CommandeController {

    private final Logger log = LoggerFactory.getLogger(CommandeController.class);
 
    private final ICommandeDtoService orderService;
    
    public CommandeController(ICommandeDtoService orderService) {
        this.orderService = orderService;
    }

    /**
     * {@code POST  /orders} : Create a new Order.
     *
     * @param OrderDTO the Order to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Order, or with status {@code 400 (Bad Request)} if the Order has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orders")
    @ApiOperation(value = "Save a new Order")
    public ResponseEntity<Commande> createOrder(@Valid @RequestBody Commande order) throws URISyntaxException {
        log.debug("REST request to save Order : {}", order);

        Commande result = orderService.save(order);
        return ResponseEntity.created(new URI("/api/orders/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /orders} : Updates an existing order.
     *
     * @param order the order to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated order,
     * or with status {@code 400 (Bad Request)} if the order is not valid,
     * or with status {@code 500 (Internal Server Error)} if the order couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/orders")
    @ApiOperation(value = "Update an existed Order")
    public ResponseEntity<Commande> updateOrder(@Valid @RequestBody Commande order) throws URISyntaxException {
        log.debug("REST request to update Order : {}", order);
        Commande result = orderService.save(order);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/orders")
    @ApiOperation(value = "Get all the orders - paginable")
    public ResponseEntity<List<Commande>> getAllOrders(Pageable pageable) {
        log.debug("REST request to get a page of Orders");
        Page<Commande> page = orderService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }
    
    
    @GetMapping("/orders/all")
    @ApiOperation(value = "Get all the orders - list")
    public  List<Commande> getAllOrders() {
        log.debug("REST request to get all Orders");
        return orderService.findAll();
    }

    /**
     * {@code GET  /orders/:id} : get the "id" order.
     *
     * @param id the id of the order to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the order, or with status {@code 404 (Not Found)}.
     */
    
    @GetMapping("/orders/{id}")
    @ApiOperation(value = "Get Order by id")
    public ResponseEntity<Commande> getOrder(@PathVariable Long id) {
        log.debug("REST request to get Order : {}", id);
        Optional<Commande> order = orderService.findOne(id);
        return ResponseEntity.ok().body(order.get());
    }
    
   
    /**
     * {@code DELETE  /orders/:id} : delete the "id" order.
     *
     * @param id the id of the order to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */

    @ApiOperation(value = "Delete Order by id")
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.debug("REST request to delete Order : {}", id);
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
