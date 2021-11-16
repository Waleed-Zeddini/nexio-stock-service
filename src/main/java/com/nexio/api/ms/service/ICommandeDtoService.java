package com.nexio.api.ms.service;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nexio.api.ms.domain.Commande;

public interface ICommandeDtoService {

	Commande save(Commande commande);

	Page<Commande> findAll(Pageable pageable);
	
	List<Commande> findAll();

	Optional<Commande> findOne(Long id);

	void delete(Long id);

}
