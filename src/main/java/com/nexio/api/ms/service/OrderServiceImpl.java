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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import com.nexio.api.ms.dto.mapper.CommandeMapper;
import com.nexio.api.ms.openfeign.client.StockSericeClient;
import com.nexio.api.ms.config.Constants;
import com.nexio.api.ms.domain.Commande;
import com.nexio.api.ms.domain.LigneCommande;
import com.nexio.api.ms.dto.OrderDTO;
import com.nexio.api.ms.domain.Produit;

import com.nexio.api.ms.repository.ClientRepository;
import com.nexio.api.ms.repository.CommandeRepository;
import com.nexio.api.ms.repository.LigneCommandeRepository;

/**
 * Service Implementation for implementing methods declared in Interface linked to {@link OrderDTO}.
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    private final Logger log = LoggerFactory.getLogger(CommandeDtoServiceImpl.class);

    private final ClientRepository clientRepository;
    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    
    private final StockSericeClient stockSericeClient;
    
    /**
     * Decomment if we would like to 
     * use RestTemplate instaed of OpenFeign
     * @param stockSericeClient 
     */
//    @Autowired
//    private CircuitBreakerFactory<?, ?> circuitBreakerFactory;
//    
//    private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	public OrderServiceImpl (CommandeRepository commandeRepository, ClientRepository clientRepository,
			LigneCommandeRepository ligneCommandeRepository, CommandeMapper commandeMapper, StockSericeClient stockSericeClient) {
		this.commandeRepository = commandeRepository;
		this.clientRepository = clientRepository;
		this.ligneCommandeRepository = ligneCommandeRepository;
		this.stockSericeClient = stockSericeClient;
		

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
			 
	    public OrderDTO save(OrderDTO order) {
	        log.debug("Request to save Order : {}", order);

			if (clientRepository.existsById(order.getCommande().getClientId())) {
				
				order.setCommande(commandeRepository.save(order.getCommande()));
				
				for (LigneCommande ligneCde : order.getLigneCommande()) {
					ligneCde.setCommande(order.getCommande());
					ligneCde = ligneCommandeRepository.save(ligneCde);
				}
			}
			return order;
		}

    /**
     * Get all the orders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Orders");
   
        List<Commande> commandes = commandeRepository.findAll();
        List<OrderDTO> orders = new ArrayList<OrderDTO>();
        		
        	for (Commande commande : commandes) {
        		
        		List<LigneCommande> lignesCde = new ArrayList<LigneCommande>();
        		OrderDTO order = new OrderDTO(); 
        		
        		order.setCommande(commande);
        		
        		lignesCde = ligneCommandeRepository.findByCommandeId(order.getCommande().getId());
        		
        		for (Iterator<LigneCommande> iterator = lignesCde.iterator(); iterator.hasNext();) {
  						LigneCommande oneLigneCde = (LigneCommande) iterator.next();
  						
  						/**
  		        		 * getProduitByCde : Invoking API Rest of Produit by Rest template
  		        		 * We use other exemple with Feign Client
  		        		 */
  			        	oneLigneCde.setProduit((getProduitByCde(oneLigneCde.getProduitId())));
        		}
        		   
        		order.setLigneCommande(lignesCde);
        		order.setClient(clientRepository.getById(order.getCommande().getClientId())); 
        		orders.add(order);

    	        }
        	
      
        	Page<OrderDTO> pageOrders = new PageImpl<OrderDTO>(orders, pageable, Integer.valueOf(orders.size()).longValue());
        
        return pageOrders;
    }


    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        log.debug("Request to get all Orders");
   
        List<Commande> commandes = commandeRepository.findAll();
        List<OrderDTO> orders = new ArrayList<OrderDTO>();
        List<LigneCommande> orderItems = new ArrayList<LigneCommande>();
        OrderDTO order = new OrderDTO(); 

        	for (Commande commande : commandes) {
        		
        		List<LigneCommande> lignesCde = new ArrayList<LigneCommande>();
        		
        		order.setCommande(commande);
        		
        		lignesCde = ligneCommandeRepository.findByCommandeId(order.getCommande().getId());
        		
        		
        		for (Iterator<LigneCommande> iterator = lignesCde.iterator(); iterator.hasNext();) {
  						LigneCommande oneLigneCde = (LigneCommande) iterator.next();
  						
  						/**
  		        		 * getProduitByCde : Invoking API Rest of Produit by Rest template
  		        		 * We use other exemple with Feign Client
  		        		 */
  			        	oneLigneCde.setProduit((getProduitByCde(oneLigneCde.getProduitId())));
  			        	
  			        	orderItems.add(oneLigneCde);
        		}
        		   
        		order.setLigneCommande(orderItems);
        		order.setClient(clientRepository.getById(order.getCommande().getClientId())); 
        		orders.add(order);

    	        }

         return orders;
    }

    /**
     * Consumming API Rest of Produit with Rest Template
     * It's more easier to use Open feign specially with
     * security configuration and Oauth2
     * @param produitId
     * @return Produit
     * 
     */
    public Produit getProduitByCde(Long produitId){
    	return  stockSericeClient.getProduitById(produitId);
    /*
     * Decomment for using RestTemplate instad of Feign 	
     */
//    	Produit produit = circuitBreakerFactory.create("produit-details").run(()->{
//                    ResponseEntity<Produit> produitEntity = restTemplate.exchange(Constants.PRODUIT_API_URL + produitId, HttpMethod.GET,null,new ParameterizedTypeReference<Produit>(){});
//                    return produitEntity.getBody();
//                }, throwable -> new Produit()
//        );
//        return produit;
    }

    /**
     * Get one order by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderDTO> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        
        OrderDTO order = new OrderDTO();
        
        if(commandeRepository.existsById(id))
        {
        
        	
        order.setCommande(commandeRepository.getById(id));  
        order.setLigneCommande(ligneCommandeRepository.findByCommandeId(order.getCommande().getId()));
        
        order.setClient(clientRepository.getById(order.getCommande().getClientId()));  
        }
       
        
        return Optional.ofNullable(order) ;
    }
    
  

    /**
     * Delete the order by id: Order and its children : LigneCommande
     * 
     * @param id the id of the entity.
     * 
     * THE SECURE MODE FOR DDELETING IS TO ENSURE 
     * OF EXISTENCE LIST OF CHILDREN BY FETCING THEM
     * AFTER THAT DELETE EVERY EXISTED ONE
     */
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        
        OrderDTO order = new OrderDTO();
        
        if(commandeRepository.existsById(id))
        {
        order.setCommande(commandeRepository.getById(id));
        
        List<LigneCommande> lignes = ligneCommandeRepository.findByCommandeId(order.getCommande().getId());
            
        for (Iterator<LigneCommande> iterator = lignes.iterator(); iterator.hasNext();) {
				LigneCommande lCommande = (LigneCommande) iterator.next();
				ligneCommandeRepository.deleteById(lCommande.getId());
	   }
            
       commandeRepository.deleteById(id);
        }
    }
    

}