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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexio.api.ms.domain.Categorie;

/**
 * Spring Data  repository for the Categorie entity.
 */
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

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
public Page<Categorie> findByCode(String code, Pageable pageable);

public Page<Categorie> findByLibelle(String libelle, Pageable pageable);



 
/**
 * Finders
 */
public List<Categorie>  findByCodeLike(String code);
public List<Categorie>  findByLibelleLike(String libelle);
 

/**
 * Just an example in case we want to appeal
  * to the query (which is not recommended for Spring Data performace)
 */
@Query(
		  " SELECT  c FROM Categorie c "
		    + " WHERE  c.code = ?1 "
		    + " OR  c.libelle Like ?2% "
)	
public List<Categorie> findCatgorieByCodeOrLib(String code, String libelle);





}