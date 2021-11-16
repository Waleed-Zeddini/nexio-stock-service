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

import com.nexio.api.ms.dto.mapper.CommandeDTO;
import com.nexio.api.ms.dto.mapper.CommandeMapper;
import com.nexio.api.ms.config.Constants;
import com.nexio.api.ms.domain.LigneCommande;
import com.nexio.api.ms.domain.Client;
import com.nexio.api.ms.domain.Commande;
import com.nexio.api.ms.domain.Produit;

import com.nexio.api.ms.repository.ClientRepository;
import com.nexio.api.ms.repository.CommandeRepository;
import com.nexio.api.ms.repository.LigneCommandeRepository;

/**
 * Service Implementation for implementing methods declared in Interface linked to {@link OrderDTO}.
 */
@Service
@Transactional
public class CommandeDtoServiceImpl implements ICommandeDtoService {

    private final Logger log = LoggerFactory.getLogger(CommandeDtoServiceImpl.class);

    private final ClientRepository clientRepository;
    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    
    private final CommandeMapper commandeMapper;
    
    @Autowired
    private CircuitBreakerFactory<?, ?> circuitBreakerFactory;
    
    private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	public CommandeDtoServiceImpl (CommandeRepository commandeRepository, ClientRepository clientRepository,
			LigneCommandeRepository ligneCommandeRepository, CommandeMapper commandeMapper) {
		this.commandeRepository = commandeRepository;
		this.clientRepository = clientRepository;
		this.ligneCommandeRepository = ligneCommandeRepository;
		
		this.commandeMapper = commandeMapper;

	}

	 /**
     * Create an order.
     *
     * @param order the entity to save.
     * @return the persisted entity.
     */
	
 
	 
	    /**
	     * Save a order.
	     *
	     * @param order the entity to save.
	     * @return the persisted entity.
	     */
			 
	    public Commande save(Commande commande) {
	        log.debug("Request to save CommandeDTO : {}", commande);
	        
	       
 	       
	        if(clientRepository.existsById(commande.getClientId())) {

       
	        commande = commandeRepository.save(commande);
	        
        	for (LigneCommande ligneCde : commande.getLigneCommandes()) {
        		ligneCde.setCommande(commande);      		
        		ligneCde = ligneCommandeRepository.save(ligneCde);
			}
	        	
 	        

	    } 
	        return commande;
	    }

    /**
     * Get all the orders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Commande> findAll(Pageable pageable) {
        log.debug("Request to get all Orders");
   
        Page<Commande> commandes = commandeRepository.findAll(pageable);
        
         	
        	for (Commande commande : commandes) {
        		
        		if (commande != null) {
        			CommandeDTO orderDTO = commandeMapper.orderToCommandeDTO(commande);
        			
    	            Set<LigneCommande> lignesCde = orderDTO.getCarnets().stream()
    	                .map(ligneCommandeRepository::findById)
    	                .filter(Optional::isPresent)
    	                .map(Optional::get)
    	                .collect(Collectors.toSet());
    	            
    	            for (Iterator<LigneCommande> iterator = lignesCde.iterator(); iterator.hasNext();) {
   						LigneCommande carnetCommande = (LigneCommande) iterator.next();
   			        	carnetCommande.setProduit((getProduitByCde(carnetCommande.getProduitId())));
	   		   }
    	            
    	            commande.setLigneCommandes(lignesCde);
    	        }
			}
        	
      
        	Page<Commande> pageOrders = new PageImpl<Commande>(commandes.getContent(), pageable, Integer.valueOf(commandes.getSize()).longValue());
        
        return pageOrders;
    }


    @Transactional(readOnly = true)
    public List<Commande> findAll() {
        log.debug("Request to get all Orders");
   
        List<Commande> commandes = commandeRepository.findAll();
        
         	
        	for (Commande commande : commandes) {
        		
        		if (commande != null) {
        			CommandeDTO orderDTO = commandeMapper.orderToCommandeDTO(commande);
        			
    	            Set<LigneCommande> lignesCde = orderDTO.getCarnets().stream()
    	                .map(ligneCommandeRepository::findById)
    	                .filter(Optional::isPresent)
    	                .map(Optional::get)
    	                .collect(Collectors.toSet());
    	            
    	           	
    	            for (Iterator<LigneCommande> iterator = lignesCde.iterator(); iterator.hasNext();) {
    	   						LigneCommande carnetCommande = (LigneCommande) iterator.next();
    	   			        	carnetCommande.setProduit((getProduitByCde(carnetCommande.getProduitId())));
    	   		   }
    	            
     	            commande.setLigneCommandes(lignesCde);
    	        }
			}
         return commandes;
    }

    public Produit getProduitByCde(Long produitId){
    	Produit produit = circuitBreakerFactory.create("produit-details").run(()->{
                    ResponseEntity<Produit> produitEntity = restTemplate.exchange(Constants.PRODUIT_API_URL + produitId, HttpMethod.GET,null,new ParameterizedTypeReference<Produit>(){});
                    return produitEntity.getBody();
                }, throwable -> new Produit()
        );
        return produit;
    }

    /**
     * Get one order by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Commande> findOne(Long id) {
        log.debug("Request to get CommandeDTO : {}", id);
         
        Commande commande = commandeRepository.getById(id);
 	    
        if(commande!=null) {
		
        CommandeDTO orderDTO = commandeMapper.orderToCommandeDTO(commande);
		
        Set<LigneCommande> lignesCde = orderDTO.getCarnets().stream()
            .map(ligneCommandeRepository::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
        
        for (Iterator<LigneCommande> iterator = lignesCde.iterator(); iterator.hasNext();) {
			LigneCommande carnetCommande = (LigneCommande) iterator.next();
	        	carnetCommande.setProduit((getProduitByCde(carnetCommande.getProduitId())));
		   }
        
         commande.setLigneCommandes(lignesCde);
        }  
       
        
        return Optional.ofNullable(commande) ;
    }
    
  

    /**
     * Delete the order by id: Commande and its children : LigneCommande
     * 
     * @param id the id of the entity.
     * 
     * THE SECURE MODE FOR DDELETING IS TO ENSURE 
     * OF EXISTENCE LIST OF CHILDREN BY FETCING THEM
     * AFTER THAT DELETE EVERY EXISTED ONE
     */
    public void delete(Long id) {
        log.debug("Request to delete CommandeDTO : {}", id);
        
        Commande commande = new Commande();
        
        if(commandeRepository.existsById(id))
        {
        commande = commandeRepository.getById(id);
        
		CommandeDTO orderDTO = commandeMapper.orderToCommandeDTO(commande);
			
            Set<LigneCommande> lignes = orderDTO.getCarnets().stream()
                .map(ligneCommandeRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            
            for (Iterator<LigneCommande> iterator = lignes.iterator(); iterator.hasNext();) {
					LigneCommande carnetCommande = (LigneCommande) iterator.next();
					ligneCommandeRepository.deleteById(carnetCommande.getId());
		   }
            
            commandeRepository.deleteById(id);
        }
    }
    

}