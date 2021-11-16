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
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nexio.api.ms.domain.Commande;
import com.nexio.api.ms.domain.LigneCommande;
/**
 * Interface for managing methods linked to {@link Commande}.
 */
public interface ICommandeService {

	
	Commande save(Commande produit);
	 
	Page<Commande> findAll(Pageable pageable);
	
	Optional<Commande> findOne(Long id);
	
	void delete(Long id);

	List<LigneCommande> getLigneByCommandeId(Long commandeId);

   
}
