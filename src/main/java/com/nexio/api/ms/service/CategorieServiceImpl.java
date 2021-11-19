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
 * @since   2021-11-05 
 */

import com.nexio.api.ms.domain.Categorie;
import com.nexio.api.ms.domain.Produit;
import com.nexio.api.ms.repository.CategorieRepository;
import com.nexio.api.ms.repository.ProduitRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for implementing methods declared in Interface linked to  {@link Categorie}.
 */
@Service
@Transactional
public class CategorieServiceImpl implements ICategorieService {

    private final Logger log = LoggerFactory.getLogger(CategorieServiceImpl.class);

    private final CategorieRepository categorieRepository;
    private final ProduitRepository produitRepository;

	
    /**
	 * Injection par constructeur est meilleure
	 * que Autowired
	 */
    public CategorieServiceImpl(CategorieRepository categorieRepository,
    		ProduitRepository produitRepository) {
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
    }

    /**
     * Save a categorie.
     *
     * @param categorie the entity to save.
     * @return the persisted entity.
     */
    public Categorie save(Categorie categorie) {
        log.debug("Request to save Categorie : {}", categorie);
        return categorieRepository.save(categorie);
    }

    /**
     * Get all the categories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Categorie> findAll(Pageable pageable) {
        log.debug("Request to get all Categories");
        return categorieRepository.findAll(pageable);
    }


    /**
     * Get one categorie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Categorie> findOne(Long id) {
        log.debug("Request to get Categorie : {}", id);
        return categorieRepository.findById(id);
    }

    /**
     * Delete the categorie by id.
     *
     * @param id the id of the entity.
     */
	public void delete(Long id) {
		log.debug("Request to delete Categorie : {}", id);

		List<Produit> produitList = getByCategorieId(id);

		for (Produit produit : produitList) {
			if(produit.getId()!=null)
			produitRepository.deleteById(produit.getId());
		}

		categorieRepository.deleteById(id);
	}

	@Override
	public List<Produit> getByCategorieId(Long categorieId) {
		return produitRepository.findByCategorieId(categorieId);
	}

	@Override
	public List<Categorie> findAll() {
		return categorieRepository.findAll();
	}
}
