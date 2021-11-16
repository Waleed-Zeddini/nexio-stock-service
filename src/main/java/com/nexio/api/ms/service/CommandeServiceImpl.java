package com.nexio.api.ms.service;
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
 * @since   2021-11-07 
 */
import com.nexio.api.ms.domain.Commande;
import com.nexio.api.ms.domain.LigneCommande;
import com.nexio.api.ms.repository.ClientRepository;
import com.nexio.api.ms.repository.CommandeRepository;
import com.nexio.api.ms.repository.LigneCommandeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


/**
 * Service Implementation for implementing methods declared in Interface linked to  {@link Commande}.
 */
@Service
@Transactional
public class CommandeServiceImpl implements ICommandeService {

    private final Logger log = LoggerFactory.getLogger(CommandeServiceImpl.class);

    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ClientRepository clientRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository,
    		LigneCommandeRepository ligneCommandeRepository,
    		ClientRepository clientRepository) {
        this.commandeRepository = commandeRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.clientRepository = clientRepository;  
    }

    /**
     * Save a commande.
     *
     * @param commande the entity to save.
     * @return the persisted entity.
     */
    public Commande save(Commande commande) {
        log.debug("Request to save Commande : {}", commande);
        
        
        if(commande.getClient()!=null) {
        	clientRepository.save(commande.getClient());
        }
        if(!commande.getLigneCommandes().isEmpty()) {
        	
            commande = commandeRepository.save(commande);

        	for (Iterator<LigneCommande> iterator = commande.getLigneCommandes().iterator(); iterator.hasNext();) {
				LigneCommande ligneCommande = (LigneCommande) iterator.next();
				ligneCommande.setCommande(commande);
	        	ligneCommandeRepository.save(ligneCommande);
			}
        }else {
        commande = commandeRepository.save(commande);
        }
        return commande;
    }

    /**
     * Get all the commandes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Commande> findAll(Pageable pageable) {
        log.debug("Request to get all Commandes");
        return commandeRepository.findAll(pageable);
    }


    /**
     * Get one commande by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Commande> findOne(Long id) {
        log.debug("Request to get Commande : {}", id);
        return commandeRepository.findById(id);
    }

    /**
     * Delete the commande by id.
     *
     * @param id the id of the entity.
     */
 
    
	public void delete(Long id) {
		log.debug("Request to delete Categorie : {}", id);

		List<LigneCommande> ligneCommandes = getLigneByCommandeId(id);

		for (LigneCommande lc : ligneCommandes) {
	        commandeRepository.deleteById(lc.getCommandeId());
		}

		commandeRepository.deleteById(id);
	}

	@Override
	public List<LigneCommande> getLigneByCommandeId(Long commandeId) {
		return ligneCommandeRepository.findByCommandeId(commandeId);
	}
}
