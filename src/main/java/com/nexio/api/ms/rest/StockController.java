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
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
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
@Api(value="Microservice - Stock",tags="Microservice - Stock",description="The main Microservice")
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
    @PostMapping("/stocks")
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
    @PutMapping("/stocks")
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
    @GetMapping("/stocks")
    @ApiOperation(value = "Get all Produits in the stoks - paginable")
    public ResponseEntity<List<Produit>> getAllProduits(Pageable pageable) {
        log.debug("REST request to get a page of Produits in the Stock");
        Page<Produit> page = stokService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }
    
    
    @GetMapping("/stocks/all")
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
    
    @GetMapping("/stocks{id}")
    @ApiOperation(value = "Get Produit from the Stock by id")
    public ResponseEntity<Produit> getStock(
    		@ApiParam(
		    	    name =  "id",
		    	    value = "id",
		    	    example = "1",
		    	    required = true)    		
    		@PathVariable Long id) {
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
    @DeleteMapping("/stocks{id}")
    public ResponseEntity<Void> deleteStock(
    		@ApiParam(
		    	    name =  "id",
		    	    value = "id",
		    	    example = "1",
		    	    required = true)
    		
    		@PathVariable Long id) {
        log.debug("REST request to delete Produit from the Stock by id : {}", id);
        stokService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * {@code GET  /stoks} : get all the stoks by Prix  isGreaterThan.
     *
	 * @param prixTotal
     * @return the List of Stoks with status {@code 200 (OK)} and the list of stoks in body.
     */ 
    @GetMapping("/stocks/all-produits/prix-greater-than")
    @ApiOperation(value = "Get all the produits that price is greater than the price entred")
	public List<Produit> getProduitsByByPrixGreaterThan(
		    @ApiParam(
		    	    name =  "puProduit",
		    	    value = "Prix Unitaire",
		    	    example = "100",
		    	    required = true)
			BigDecimal puProduit) {
    	
    log.debug("REST request to get all the produits that its price greater than the price entred");
    
  	return stokService.getByPrixUnitaireGreaterThan(puProduit);
	
	}
    
    /**
     * {@code GET  /stoks} : get all the produits by Prixless.
     *
	 * @param prixTotal
     * @return the List of Stoks with status {@code 200 (OK)} and the list of produits in body.
     */ 
    @GetMapping("/stocks/all-produits/prix-less-than")
    @ApiOperation(value = "Get all the produits that price is less than the price entred")
	public List<Produit> getProduitsByPrixTotalLess(
		    @ApiParam(
		    	    name =  "puProduit",
		    	    value = "Prix Unitaire",
		    	    example = "100",
		    	    required = true)
			BigDecimal puProduit) {
    	
    log.debug("REST request to get all the produits that its price less than the price entred");
    
  	return stokService.getByPrixUnitaireLessThan(puProduit);
	
	}
  
    /**
     * {@code GET  /produits} : get all the produits by Prix is between Min and Max.
     *
	 * @param prixMin
	 * @param prixMax
     * @return the List of Stoks with status {@code 200 (OK)} and the list of produits in body.
     */ 
    @GetMapping("/stocks/all-produits/prix-between")
    @ApiOperation(value = "Get all the produits that price is between Min and Max")
	public List<Produit> getProduitsByByPrixGreaterThan(
			@ApiParam(
		    	    name =  "prixMin",
		    	    value = "Prix Min",
		    	    example = "10",
		    	    required = true)
			BigDecimal prixMin, 
			
			@ApiParam(
		    	    name =  "prixMax",
		    	    value = "Prix Max",
		    	    type= "number",
		    	    example = "100000",
		    	    required = true)
			BigDecimal prixMax) {
    	
    log.debug("REST request to get all the produits that its price is between Min and Max");
    
  	return stokService.getAllProduitsByPrixTBetween(prixMin, prixMax);
	
	}

    
    /**
     * {@code GET  /produits} : get all the produits that marque like entred value.
     *
	 * @param marque
     * @return the List of Stoks with status {@code 200 (OK)} and the list of produits in body.
     */ 
    @GetMapping("/stocks/all-produits/marque-like")
    @ApiOperation(value = "Get all the produits that marque like ebtred value")
	public List<Produit> getProduitsByMarqueLike(
			@ApiParam(
		    	    name =  "marque",
		    	    value = "marque",
		    	    example = "HP or Dell",
		    	    required = true)
			String marque) {
    	
    log.debug("REST request to get all the produits that marque like entred value");
    
  	return stokService.getByMarqueLike(marque);
	
	}
    
    /**
     * {@code GET  /produits} : get all the that modele like entred value.
     *
	 * @param modele
     * @return the List of Stoks with status {@code 200 (OK)} and the list of produits in body.
     */ 
    @GetMapping("/stocks/all-produits/modele-like")
    @ApiOperation(value = "Get all the produits that modele like entred value")
	public List<Produit> getProduitsByModeleLike(
			@ApiParam(
		    	    name =  "modele",
		    	    value = "modele",
		    	    example = "ProDesk or Latitude",
		    	    required = true)
			String modele) {
    	
    log.debug("REST request to get all the produits that  modele like entred value");
    
  	return stokService.getByModeleLike(modele);
	
	}
    
    /**
     * {@code GET  /produits} : get all the that caracteristique like entred value.
     *
	 * @param caracteristique
     * @return the List of Stoks with status {@code 200 (OK)} and the list of produits in body.
     */ 
    @GetMapping("/stocks/all-produits/caracteristics-like")
    @ApiOperation(value = "Get all the produits that caracteristiques like entred value")
	public List<Produit> getProduitsByCaracteristicLike(
			@ApiParam(
		    	    name =  "caracteristiques",
		    	    value = "caracteristiques",
		    	    example = "I9 or 32 GO RAM or 5 Tera HD",
		    	    required = true)
			String caracteristiques) {
    	
    log.debug("REST request to get all the produits that  caracteristiques like entred value");
    
  	return stokService.getByCaracteristiquesLike(caracteristiques);
	
	}
    
    
    /**
     * {@code GET  /stoks} : get all the stoks by Quantite GreaterThan.
     *
	 * @param prixTotal
     * @return the List of Stoks with status {@code 200 (OK)} and the list of stoks in body.
     */ 
    @GetMapping("/stocks/all-produits/qte-greater-than")
    @ApiOperation(value = "Get all the produits that Quantite is greater than the Quantite entred")
	public List<Produit> getProduitsByByQuantiteGreaterThan(
		    @ApiParam(
		    	    name =  "qte",
		    	    value = "qte",
		    	    example = "100",
		    	    required = true)
			Long qte) {
    	
    log.debug("REST request to get all the produits that its price greater than the price entred");
    
  	return stokService.getByQuantiteGreaterThan(qte);
	
	}
    
    /**
     * {@code GET  /stoks} : get all the produits by qte less.
     *
	 * @param prixTotal
     * @return the List of Stoks with status {@code 200 (OK)} and the list of produits in body.
     */ 
    @GetMapping("/stocks/all-produits/qte-less-than")
    @ApiOperation(value = "Get all the produits that qte is less than the qte entred")
	public List<Produit> getProduitsByQteLess(
		    @ApiParam(
		    	    name =  "qte",
		    	    value = "qte",
		    	    example = "100",
		    	    required = true)
			Long qte) {
    	
    log.debug("REST request to get all the produits that its price less than the qte entred");
    
  	return stokService.getByQuantiteLessThan(qte);
	
	}
  
    /**
     * {@code GET  /produits} : get all the produits by Prix  is between Min and Max.
     *
	 * @param prixMin
	 * @param prixMax
     * @return the List of Stoks with status {@code 200 (OK)} and the list of produits in body.
     */ 
    @GetMapping("/stocks/all-produits/qte-between")
    @ApiOperation(value = "Get all the produits that qte is between Min and Max")
	public List<Produit> getProduitsByQteGreaterThan(
			@ApiParam(
		    	    name =  "qteMin",
		    	    value = "qte Min",
		    	    example = "10",
		    	    required = true)
			BigDecimal qteMin, 
			
			@ApiParam(
		    	    name =  "qteMax",
		    	    value = "qte Max",
		    	    type= "number",
		    	    example = "100000",
		    	    required = true)
			BigDecimal qteMax) {
    	
    log.debug("REST request to get all the produits that its qte is between Min and Max");
    
  	return stokService.getAllProduitsByPQteBetween(qteMin, qteMax);
	
	}

    
}
