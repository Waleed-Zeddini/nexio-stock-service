package com.nexio.api.ms.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nexio.api.ms.domain.Produit;

public interface IStockService {

	Produit save(Produit produit);
	
	List<Produit> saveMany(List<Produit> produitList);

	Page<Produit> findAll(Pageable pageable);
	
	List<Produit> findAll();

	Optional<Produit> findOne(Long id);
	
	Produit getProditById(Long id);

	void delete(Long id);
	
	void deleteMany(List<Produit> produits) ;
	
	 List<Produit> getByMarqueLike(String marque);

	 List<Produit> getByModeleLike(String modele);

	 List<Produit> getByCaracteristiquesLike( String caracteristique);

	 List<Produit>  getByCategorieId(Long categorieId);

	 List<Produit>  getByQuantiteGreaterThan(Long qte);
	 List<Produit>  getByQuantiteLessThan(Long qte);

	 List<Produit> getByPrixUnitaireGreaterThan(BigDecimal puProduit);
	 List<Produit> getByPrixUnitaireLessThan(BigDecimal puProduit);

	List<Produit> getAllProduitsByPrixTBetween(BigDecimal prixMin, BigDecimal prixMax);

	List<Produit> getAllProduitsByPQteBetween(BigDecimal qteMin, BigDecimal qteMax);

}
