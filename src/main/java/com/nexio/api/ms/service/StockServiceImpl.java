package com.nexio.api.ms.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import com.nexio.api.ms.config.Constants;
import com.nexio.api.ms.domain.Categorie;
import com.nexio.api.ms.domain.Produit;
import com.nexio.api.ms.repository.CategorieRepository;
import com.nexio.api.ms.repository.ProduitRepository;


/**
 * Service Implementation for implementing methods declared in Interface linked to {@link InventaryDTO}.
 */
@Service
@Transactional
public class StockServiceImpl implements IStockService {

    private final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    
    
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
    
    private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	public StockServiceImpl (ProduitRepository produitRepository, CategorieRepository categorieRepository) {
		this.produitRepository = produitRepository;
		this.categorieRepository = categorieRepository;
		

	}

	 /**
     * Create an inventary.
     *
     * @param inventary the entity to save.
     * @return the persisted entity.
     */
	
 
	 
	    /**
	     * Save a inventary.
	     *
	     * @param inventary the entity to save.
	     * @return the persisted entity.
	     */
			 
	    public Produit save(Produit produit) {
	        log.debug("Request to save ProduitDTO : {}", produit);
	        
	        if(produit!=null) {
	        	
	        setCategorieFakeInventary(produit); // NOTA : fake method just for test without (Frontend), in Produit to avoid exception
	        produit = produitRepository.save(produit);
	        } 
	        
	        return produit;
	    }

    /**
     * Get all the inventarys.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Produit> findAll(Pageable pageable) {
        log.debug("Request to get all Inventarys");
   
        Page<Produit> produits = produitRepository.findAll(pageable);
        
         	
        	for (Produit produit : produits) {
        		
        		if (produit != null) {
        			
        			produit.setCategorie(categorieRepository.getById(produit.getCategorieId()));
    	        }
			}
        	
      
        	Page<Produit> pageInventarys = new PageImpl<Produit>(produits.getContent(), pageable, Integer.valueOf(produits.getSize()).longValue());
        
        return pageInventarys;
    }


    @Transactional(readOnly = true)
    public List<Produit> findAll() {
        log.debug("Request to get all Inventarys");
   
        List<Produit> produits = produitRepository.findAll();
        
         	
		for (Produit produit : produits) {
			if (produit != null) {
				produit.setCategorie(categorieRepository.getById(produit.getCategorieId()));
			}
		}
         return produits;
    }

   

    /**
     * Get one inventary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Produit> findOne(Long id) {
        log.debug("Request to get ProduitDTO : {}", id);
         
        Produit produit = new Produit();
      
        if(produitRepository.existsById(id))
        {
        produit = produitRepository.getById(id);
    	if (produit != null) {
			produit.setCategorie(categorieRepository.getById(produit.getCategorieId()));
		}
	       
        } 
        
        return Optional.ofNullable(produit) ;
    }
    
  

    /**
     * Delete the inventary by id: Produit and its children : CarnetProduit
     * 
     * @param id the id of the entity.
     * 
     * THE SECURE MODE FOR DDELETING IS TO ENSURE 
     * OF EXISTENCE LIST OF CHILDREN BY FETCING THEM
     * AFTER THAT DELETE EVERY EXISTED ONE
     */
    public void delete(Long id) {
        log.debug("Request to delete ProduitDTO : {}", id);
        
        if(produitRepository.existsById(id))
        {
        produitRepository.deleteById(id);
        }
    }
    
    /**
     * fake Data for client and Etat
     * in inventary to insert Inventary in DB
     * @return
     */
    Produit setCategorieFakeInventary(Produit produit) {
    	Categorie categorie = new Categorie();
    	categorie.setId(new Long(1)); // Categorie exists in DB
		produit.setCategorie(categorie);
		return produit;
	}
}