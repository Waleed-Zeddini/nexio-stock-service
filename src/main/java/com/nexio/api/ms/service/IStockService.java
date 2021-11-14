package com.nexio.api.ms.service;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nexio.api.ms.domain.Produit;

public interface IStockService {

	Produit save(Produit produit);

	Page<Produit> findAll(Pageable pageable);
	
	List<Produit> findAll();

	Optional<Produit> findOne(Long id);

	void delete(Long id);

}
