package com.nexio.api.ms.repository;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexio.api.ms.domain.Commande;
import com.nexio.api.ms.domain.LigneCommande;

/**
 * Spring Data  repository for the Commande entity.
 */
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
	/**
	 * 
	 * NOTA:  
		* Remember that Spring Data systematically manages classic CRUDs without having
		* to implement them such as save, update, delete find All and find by ID.
		* However, we can overload them as we wish (if needed)
		* Query, Query Native
	 */
	
/**
 * Queries (Methods) relating to the main Business Rules (search)
 */
	

public List<Commande> findByDateBetween(LocalDate dateDebut, LocalDate dateFin);

public List<Commande> findByEtatAndDateBetween(Long etatCommande,LocalDate dateDebut, LocalDate dateFin);

public List<Commande> findByPrixTotalAndDateBetween(BigDecimal prixTotal, LocalDate dateDebut, LocalDate dateFin);

public List<Commande>  findByPrixTotalLessThan(BigDecimal prixTotal);

public List<Commande>  findByPrixTotalGreaterThan(BigDecimal prixTotal);



 
/**
 * Finders
 */

public List<Commande>  findByPrixTotal(BigDecimal prixTotal);
public List<Commande>  findByDate(LocalDate dateCommande);
public List<Commande>  findByEtat(Long etatCommande);

/**
 * Just an example in case we want to appeal
  * to the query (which is not recommended for Spring Data performace)
 */
@Query(
		  " SELECT  c FROM Commande c "
		    + " WHERE  c.etat = ?1 "
		    + " AND  c.prixTotal > ?2 "
		    + " AND  c.date < ?3 "
)	
public List<Commande> findByEtatCdeAndPrixTotDateInf(Long etatCommande, BigDecimal prixTotal, LocalDate dateCommande);






}