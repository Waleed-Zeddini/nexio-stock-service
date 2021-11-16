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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "produit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="Produit",description="Produit géré par le connerçant (stock, inventaire...)")

public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "code", length = 40, nullable = false)
    private String code;

    @NotNull
    @Size(max = 30)
    @Column(name = "marque", length = 30, nullable = false)
    private String marque;

    @NotNull
    @Size(max = 40)
    @Column(name = "modele", length = 40, nullable = false)
    private String modele;

    @NotNull
    @Size(max = 100)
    @Column(name = "caracteristiques", length = 100, nullable = false)
    private String caracteristiques;

    @NotNull
    @Column(name = "prix_unitaire", precision = 21, scale = 2, nullable = false)
    private BigDecimal prixUnitaire;

    @NotNull
    @Column(name = "quantite", nullable = false)
    private Long quantite;

    
	@Column(name = "categorie_id",  nullable = false)
	private Long categorieId;
	
	@Transient
	@JsonIgnore
    private Categorie categorie;
	
    /**
     * In the case of Monolithic Application with 
     * a relational DB containing all entities of the Application, 
     * we can define OneToMany and  ManyToMany relations between 
     * persistent entities. in MS, it's recommended to discuss and implement
     * in Service Classes
     */  

//	@JoinColumn(name = "categorie_id",  nullable = false)
//	private Long categorieId;



	


   
}
