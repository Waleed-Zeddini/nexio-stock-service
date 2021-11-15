package com.nexio.api.ms.domain;
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "categorie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="Categorie",description="Catégorie de produit")

public class Categorie implements Serializable {

	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	    @SequenceGenerator(name = "sequenceGenerator")
	    private Long id;

	    @NotNull
	    @Column(name = "code", nullable = false)
	    private String code;

	    @NotNull
	    @Column(name = "libelle", nullable = false)
	    private String libelle;

	    
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorieId", orphanRemoval = true)
	    @JsonIgnoreProperties(value = "produits", allowSetters = true)
	    private Set<Produit> produits = new HashSet<>();

	}
